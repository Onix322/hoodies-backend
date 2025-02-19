package com.hoodiesbackend.entities.order.helpers;

import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.product.helpers.ProductDto;
import com.hoodiesbackend.entities.product.helpers.ProductMapper;
import com.hoodiesbackend.entities.productCart.ProductCart;
import com.hoodiesbackend.entities.productCart.helpers.ProductCartDto;
import com.hoodiesbackend.entities.productCart.helpers.ProductCartMapper;
import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import com.hoodiesbackend.entities.user.helpers.UserMapper;

import java.util.List;

public class OrderMapper {

    public static OrderDto toDto(Order order){

        UserGetDto userDto = UserMapper.toUserGetDto(order.getUser());

        List<ProductCartDto> productsDto = order.getProducts()
                .stream()
                .map(ProductCartMapper::toDto)
                .toList();

        return new OrderDto.OrderBuilder(
                order.getId(),
                order.getStatus(),
                order.getTotalPrice(),
                userDto,
                productsDto
        ).build();
    }
}
