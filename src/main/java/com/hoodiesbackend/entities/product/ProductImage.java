package com.hoodiesbackend.entities.product;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

@Entity
@Table(name = "product_image")
@Transactional
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "image", nullable = false)
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
