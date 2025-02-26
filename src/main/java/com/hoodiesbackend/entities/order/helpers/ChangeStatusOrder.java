package com.hoodiesbackend.entities.order.helpers;

import lombok.Data;

@Data
public class ChangeStatusOrder {
    private Long orderId;
    private StatusOrder statusOrder;
}
