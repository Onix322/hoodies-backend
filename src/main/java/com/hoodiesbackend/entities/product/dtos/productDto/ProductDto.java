package com.hoodiesbackend.entities.product.dtos.productDto;


import com.hoodiesbackend.entities.product.ProductImage;
import com.hoodiesbackend.entities.product.Size;

public class ProductDto {
    private Long id;
    private String title;
    private Integer rating;
    private Size size;
    private ProductImage productImage;

    private ProductDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.rating = builder.rating;
        this.size = builder.size;
        this.productImage = builder.productImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    static class Builder{
        private Long id = 0L;
        private String title = "";
        private Integer rating = 0;
        private Size size = Size.S;
        private ProductImage productImage = new ProductImage();

        public Builder() {}

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder setSize(Size size) {
            this.size = size;
            return this;
        }

        public Builder setProductImage(ProductImage productImage) {
            this.productImage = productImage;
            return this;
        }

        public ProductDto build(){
            return new ProductDto(this);
        }
    }
}
