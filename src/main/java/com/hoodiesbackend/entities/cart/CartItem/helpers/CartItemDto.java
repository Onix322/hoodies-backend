package com.hoodiesbackend.entities.cart.CartItem.helpers;

import com.hoodiesbackend.entities.product.helpers.ProductDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDto {

    private Long id;

    private Integer quantity;

    private ProductDto product;

}
