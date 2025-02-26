package com.hoodiesbackend.services.order;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.cart.CartItem.CartItem;
import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.order.OrderItem.OrderItem;
import com.hoodiesbackend.entities.order.OrderItem.helpers.OrderItemDto;
import com.hoodiesbackend.entities.order.OrderItem.helpers.OrderItemMapper;
import com.hoodiesbackend.entities.order.helpers.OrderDetails;
import com.hoodiesbackend.entities.order.helpers.OrderDto;
import com.hoodiesbackend.entities.order.helpers.OrderMapper;
import com.hoodiesbackend.entities.order.helpers.StatusOrder;
import com.hoodiesbackend.entities.user.address.Address;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.order.OrderRepository;
import com.hoodiesbackend.services.cart.cartItem.CartItemService;
import com.hoodiesbackend.services.order.orderItem.OrderItemService;
import com.hoodiesbackend.services.user.address.AddressService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final CartItemService cartItemService;
    private final AddressService addressService;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemService orderItemService,
            CartItemService cartItemService, AddressService addressService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.cartItemService = cartItemService;
        this.addressService = addressService;
    }

    public OrderDto create(OrderDetails orderDetails) {

        Cart cart = orderDetails.getCart();
        Address address = orderDetails.getAddress();

        List<CartItem> itemsList = cart.getProducts().stream()
                .map(cartItem -> cartItemService.findItemForCart(cart.getId(), cartItem.getId()))
                .toList();

        cart.setProducts(itemsList);

        Order order = orderRepository.save(OrderMapper.toOrder(orderDetails));
        order.setAddress(address);

        List<OrderItemDto> orderItems = cart.getProducts()
                .stream()
                .map(cartItem -> OrderItemMapper.toOrderItem(cartItem, order))
                .toList()
                .stream()
                .map(orderItemService::create)
                .map(OrderItemMapper::toDto)
                .toList();

        OrderDto orderDto = OrderMapper.toDto(orderRepository.save(order));
        orderDto.setOrderItems(orderItems);
        return orderDto;
    }

    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream()
                .peek(order -> order.setOrderItems(orderItemService.findByOrderId(order.getId())))
                .map(OrderMapper::toDto)
                .toList();
    }

    public List<OrderDto> getFor(Long userId) {
        return orderRepository.findAllByUserId(userId).stream()
                .peek(order -> order.setOrderItems(orderItemService.findByOrderId(order.getId())))
                .map(OrderMapper::toDto)
                .toList();
    }

    public OrderDto getOneFor(Long userId, Long orderId) {
        Order order = orderRepository.findOrderByUserIdAndId(userId, orderId)
                .orElseThrow(() -> new NotFoundException("Order not found!"));

        List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);

        order.setOrderItems(orderItems);

        return OrderMapper.toDto(order);
    }

    @Transactional
    public OrderDto delete(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found!"));

        order.setStatusOrder(StatusOrder.CANCELED);

        orderRepository.save(order);

        return OrderMapper.toDto(order);
    }
}
