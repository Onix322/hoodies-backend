package com.hoodiesbackend.entities.cart.CartItem;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.product.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cart cart;
}
