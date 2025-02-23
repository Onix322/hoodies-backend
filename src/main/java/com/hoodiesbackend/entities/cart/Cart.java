package com.hoodiesbackend.entities.cart;

import com.hoodiesbackend.entities.cart.CartItem.CartItem;
import com.hoodiesbackend.entities.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> products;

    public Cart() {
    }

    public Cart(Long id, Double totalPrice, User user, List<CartItem> products) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.user = user;
        this.products = products;
    }
}
