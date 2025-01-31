package com.hoodiesbackend.entities.dtos;

import com.hoodiesbackend.entities.product.dtos.productDto.ProductDto;
import com.hoodiesbackend.entities.user.dtos.UserGetDto;

import java.util.List;

public class CartDto {

    private Long id;

    private UserGetDto user;

    private List<ProductDto> products;

    public CartDto(CartDtoBuilder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.products = builder.products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserGetDto getUser() {
        return user;
    }

    public void setUser(UserGetDto user) {
        this.user = user;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    static class CartDtoBuilder {
        private Long id;

        private UserGetDto user;

        private List<ProductDto> products;

        public CartDtoBuilder(Long id, UserGetDto user, List<ProductDto> products) {
            this.id = id;
            this.user = user;
            this.products = products;
        }

        public CartDtoBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public CartDtoBuilder setUser(UserGetDto user) {
            this.user = user;
            return this;
        }

        public CartDtoBuilder setProducts(List<ProductDto> products) {
            this.products = products;
            return this;
        }

        public CartDto build() {
            return new CartDto(this);
        }
    }
}
