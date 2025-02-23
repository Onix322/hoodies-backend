package com.hoodiesbackend.entities.order.OrderItem.helpers;

import com.hoodiesbackend.entities.product.helpers.ProductDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {

    private Long id;

    private Double initialPrice;

    private Integer quantity;

    private ProductDto product;
}
