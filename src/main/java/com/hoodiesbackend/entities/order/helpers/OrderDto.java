package com.hoodiesbackend.entities.order.helpers;

import com.hoodiesbackend.entities.order.OrderItem.helpers.OrderItemDto;
import com.hoodiesbackend.entities.user.address.helpers.AddressDto;
import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private StatusOrder statusOrder;

    private Double totalPrice;

    private String comments;

    private LocalDateTime createdAt;

    private LocalDateTime finalizedAt;

    private UserGetDto userDto;

    private AddressDto addressDto;

    private List<OrderItemDto> orderItems;
}
