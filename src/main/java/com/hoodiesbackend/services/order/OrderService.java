package com.hoodiesbackend.services.order;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.cart.CartItem.CartItem;
import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.order.OrderItem.helpers.OrderItemDto;
import com.hoodiesbackend.entities.order.OrderItem.helpers.OrderItemMapper;
import com.hoodiesbackend.entities.order.helpers.OrderDto;
import com.hoodiesbackend.entities.order.helpers.OrderMapper;
import com.hoodiesbackend.entities.order.helpers.StatusOrder;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.order.OrderRepository;
import com.hoodiesbackend.services.cart.cartItem.CartItemService;
import com.hoodiesbackend.services.order.orderItem.OrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final CartItemService cartItemService;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemService orderItemService,
            CartItemService cartItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.cartItemService = cartItemService;
    }

    public OrderDto create(Cart cart) {

        List<CartItem> itemsList = cart.getProducts().stream()
                .map(cartItem -> cartItemService.findItemForCart(cart.getId(), cartItem.getId()))
                .toList();

        cart.setProducts(itemsList);

        Order order = orderRepository.save(OrderMapper.toOrder(cart));

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

    @Transactional
    public OrderDto delete(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found!"));

        order.setStatusOrder(StatusOrder.CANCELED);

        orderRepository.save(order);

        return OrderMapper.toDto(order);
    }
}
