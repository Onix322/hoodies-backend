package com.hoodiesbackend.entities.order.helpers;

import com.hoodiesbackend.entities.product.helpers.ProductDto;
import com.hoodiesbackend.entities.productCart.ProductCart;
import com.hoodiesbackend.entities.productCart.helpers.ProductCartDto;
import com.hoodiesbackend.entities.user.helpers.UserGetDto;

import java.util.List;

public class OrderDto {

    private Long id;

    private OrderStatus status;

    private Double totalPrice;

    private UserGetDto user;

    private List<ProductCartDto> products;

    public OrderDto(OrderBuilder builder) {
        this.id = builder.id;
        this.status = builder.status;
        this.totalPrice = builder.totalPrice;
        this.user = builder.user;
        this.products = builder.products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserGetDto getUser() {
        return user;
    }

    public void setUser(UserGetDto user) {
        this.user = user;
    }

    public List<ProductCartDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCartDto> products) {
        this.products = products;
    }

    static class OrderBuilder {
        private Long id;

        private OrderStatus status;

        private Double totalPrice;

        private UserGetDto user;

        private List<ProductCartDto> products;

        public OrderBuilder(Long id, OrderStatus status, Double totalPrice, UserGetDto user, List<ProductCartDto> products) {
            this.id = id;
            this.status = status;
            this.totalPrice = totalPrice;
            this.user = user;
            this.products = products;
        }

        public OrderBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public OrderBuilder setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public OrderBuilder setUser(UserGetDto user) {
            this.user = user;
            return this;
        }

        public OrderBuilder setProducts(List<ProductCartDto> products) {
            this.products = products;
            return this;
        }

        public OrderDto build(){
            return new OrderDto(this);
        }
    }
}
