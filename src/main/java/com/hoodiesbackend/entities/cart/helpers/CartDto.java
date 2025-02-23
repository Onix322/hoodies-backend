package com.hoodiesbackend.entities.cart.helpers;

import com.hoodiesbackend.entities.cart.CartItem.helpers.CartItemDto;
import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartDto {

    private Long id;

    private Double totalPrice;

    private UserGetDto user;

    private List<CartItemDto> products;

}
