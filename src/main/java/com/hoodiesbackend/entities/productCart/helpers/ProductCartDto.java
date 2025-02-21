package com.hoodiesbackend.entities.productCart.helpers;

import com.hoodiesbackend.entities.product.helpers.ProductDto;

public class ProductCartDto {

    private Long id;
    private Integer quantity;
    private ProductDto productDto;

    public ProductCartDto(Long id, Integer quantity, ProductDto productDto) {
        this.id = id;
        this.quantity = quantity;
        this.productDto = productDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }
}
