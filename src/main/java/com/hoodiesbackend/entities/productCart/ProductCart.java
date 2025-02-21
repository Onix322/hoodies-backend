package com.hoodiesbackend.entities.productCart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import javax.swing.*;

@Entity
@Table(name = "product_cart", uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "product_id"}))
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantity")
    @Min(value = 1)
    private Integer quantity;

    @ManyToOne
    @NotNull
    private Product product;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    public ProductCart(Product product, Cart cart) {
        this.product = product;
        this.cart = cart;
    }

    public ProductCart() {

    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity < 0) {
            throw new RuntimeException("Quantity can't be under 0");
        }
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "ProductCart{" + "id=" + id + ", quantity=" + quantity + ", product=" + product + '}';
    }
}
