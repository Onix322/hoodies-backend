package com.hoodiesbackend.services.order;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.cart.CartItem.CartItem;
import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.order.OrderItem.OrderItem;
import com.hoodiesbackend.entities.order.OrderItem.helpers.OrderItemDto;
import com.hoodiesbackend.entities.order.OrderItem.helpers.OrderItemMapper;
import com.hoodiesbackend.entities.order.helpers.*;
import com.hoodiesbackend.entities.user.address.Address;
import com.hoodiesbackend.exceptions.BadRequestException;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.order.OrderRepository;
import com.hoodiesbackend.services.cart.cartItem.CartItemService;
import com.hoodiesbackend.services.order.orderItem.OrderItemService;
import jakarta.transaction.Transactional;
import lombok.NonNull;
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

    public OrderDto update(Order order) {
        if (order.getId() < 1) throw new BadRequestException("Order should be > 1!");

        return OrderMapper.toDto(orderRepository.save(order));
    }

    public List<OrderDto> getFor(Long userId) {

        if (userId < 1) throw new BadRequestException("User id should be > 0");

        return orderRepository.findAllByUserId(userId).stream()
                .peek(order -> order.setOrderItems(orderItemService.findByOrderId(order.getId())))
                .map(OrderMapper::toDto)
                .toList();
    }

    public OrderDto getOneFor(Long userId, Long orderId) {

        if (userId < 1 || orderId < 1) throw new BadRequestException("User id or order id should be > 0");

        Order order = orderRepository.findOrderByUserIdAndId(userId, orderId)
                .orElseThrow(() -> new NotFoundException("Order not found!"));

        List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);

        order.setOrderItems(orderItems);

        return OrderMapper.toDto(order);
    }

    @Transactional
    public OrderDto delete(Long orderId) {

        if (orderId < 1) throw new BadRequestException("Order id should be > 0");

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found!"));

        order.setStatusOrder(StatusOrder.CANCELED);

        this.update(order);

        return OrderMapper.toDto(order);
    }

    public OrderDto changeStatus(@NonNull ChangeStatusOrder changeStatusOrder) {

        if (changeStatusOrder.getOrderId() < 1 || changeStatusOrder.getStatusOrder() == null) {
            throw new BadRequestException("Order id should not be 0, respective null");
        }

        Order order = orderRepository.findById(changeStatusOrder.getOrderId())
                .orElseThrow(() -> new NotFoundException("Order not found!"));

        order.setStatusOrder(changeStatusOrder.getStatusOrder());

        return this.update(order);
    }
}
