package com.hoodiesbackend.entities.order.helpers;

import com.hoodiesbackend.entities.order.OrderItem.helpers.OrderItemDto;
import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private StatusOrder statusOrder;

    private Double totalPrice;

    private UserGetDto userDto;

    private List<OrderItemDto> orderItems;
}
