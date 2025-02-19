package com.hoodiesbackend.entities.productCart.helpers;

import com.hoodiesbackend.entities.product.helpers.ProductMapper;
import com.hoodiesbackend.entities.productCart.ProductCart;

public class ProductCartMapper {

    public static ProductCartDto toDto(ProductCart productCart) {

        return new ProductCartDto(
                productCart.getQuantity(),
                ProductMapper.toDto(productCart.getProduct())
        );
    }
}
