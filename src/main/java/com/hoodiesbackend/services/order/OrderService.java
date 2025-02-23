package com.hoodiesbackend.services.order;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.cart.CartItem.CartItem;
import com.hoodiesbackend.entities.cart.helpers.CartDto;
import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.order.OrderItem.OrderItem;
import com.hoodiesbackend.entities.order.OrderItem.helpers.OrderItemDto;
import com.hoodiesbackend.entities.order.OrderItem.helpers.OrderItemMapper;
import com.hoodiesbackend.entities.order.helpers.OrderDto;
import com.hoodiesbackend.entities.order.helpers.OrderMapper;
import com.hoodiesbackend.repositories.order.OrderRepository;
import com.hoodiesbackend.services.cart.CartService;
import com.hoodiesbackend.services.cart.cartItem.CartItemService;
import com.hoodiesbackend.services.order.orderItem.OrderItemService;
import org.springframework.core.CollectionFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final CartService cartService;
    private final CartItemService cartItemService;

    public OrderService(OrderRepository orderRepository, OrderItemService orderItemService, CartService cartService, CartItemService cartItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    public OrderDto create(Cart cart) {

        List<CartItem> itemsList= cart.getProducts().stream()
                .map(cartItem -> cartItemService.findItemFroCart(cart.getId(), cartItem.getId()))
                .toList();

        cart.setProducts(itemsList);

        System.out.println(cart);

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
}
