package com.hoodiesbackend.entities.cart.helpers;

import lombok.Data;

import java.util.List;

@Data
public class SelectedCartItems {
    private Long cartId;
    private List<Long> cartItemsIds;
}
