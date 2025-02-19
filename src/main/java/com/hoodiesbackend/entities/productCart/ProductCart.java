package com.hoodiesbackend.entities.productCart;

import com.hoodiesbackend.entities.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product_cart")
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantity")
    @Min(value = 1)
    private Integer quantity;

    @OneToOne
    @NotNull
    @JoinColumn(unique = true)
    private Product product;

    public ProductCart(Product product) {
        this.product = product;
    }

    public ProductCart() {

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
