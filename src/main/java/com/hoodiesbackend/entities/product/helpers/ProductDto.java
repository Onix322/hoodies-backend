package com.hoodiesbackend.entities.product.helpers;

public class ProductDto {
    private Long id;
    private String title;
    private Integer rating;
    private Size size;
    private Double price;
    private ProductImage productImage;
    private Integer numberReviews;
    private Boolean availableForPurchase;

    private ProductDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.rating = builder.rating;
        this.price = builder.price;
        this.size = builder.size;
        this.productImage = builder.productImage;
        this.availableForPurchase = builder.availableForPurchase;
        this.numberReviews = builder.numberReviews;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Integer getNumberReviews() {
        return numberReviews;
    }

    public void setNumberReviews(Integer numberReviews) {
        this.numberReviews = numberReviews;
    }

    public Boolean getAvailableForPurchase() {
        return availableForPurchase;
    }

    public void setAvailableForPurchase(Boolean availableForPurchase) {
        this.availableForPurchase = availableForPurchase;
    }

    static class Builder{
        private Long id = 0L;
        private String title = "";
        private Integer rating = 0;
        private Size size = Size.S;
        private Double price = 0.0;
        private ProductImage productImage = new ProductImage();
        private Integer numberReviews = 0;
        private Boolean availableForPurchase = true;

        public Builder() {}

        public Builder setNumberReviews(Integer numberReviews) {
            this.numberReviews = numberReviews;
            return this;
        }

        public Builder setAvailableForPurchase(Boolean availableForPurchase) {
            this.availableForPurchase = availableForPurchase;
            return this;
        }

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

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public ProductDto build(){
            return new ProductDto(this);
        }
    }
}
