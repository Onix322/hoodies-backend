package com.hoodiesbackend.entities.productCart.helpers;

import com.hoodiesbackend.entities.product.helpers.ProductDto;

public class ProductCartDto {

    private Integer quantity;
    private ProductDto productDto;

    public ProductCartDto(Integer quantity, ProductDto productDto) {
        this.quantity = quantity;
        this.productDto = productDto;
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
