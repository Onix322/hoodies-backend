package com.hoodiesbackend.entities.cart;

import com.hoodiesbackend.entities.productCart.ProductCart;
import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true, nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductCart> products;

    public List<ProductCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCart> products) {
        this.products = products;
    }

    public void addProduct(ProductCart product) {
        products.add(product);
    }

    public void removeProduct(ProductCart product) {
        products.remove(product);
    }

    public void removeProduct(Product product) {
        products.stream()
                .filter(cp -> cp.getProduct() == product)
                .forEach(cp -> products.remove(cp));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
