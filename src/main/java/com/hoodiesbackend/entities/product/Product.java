package com.hoodiesbackend.entities.product;

import com.hoodiesbackend.entities.product.helpers.ProductColor;
import com.hoodiesbackend.entities.product.helpers.ProductImage;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    @NotBlank(message = "Title mandatory")
    private String title;

    @Column(name = "size", nullable = false)
    @Enumerated(EnumType.STRING)
    private com.hoodiesbackend.entities.product.helpers.Size size;

    @Column(name = "rating", nullable = false)
    @Min(value = 0, message = "Rating should be between 0-5")
    @Max(value = 5, message = "Rating should be between 0-5")
    private Integer rating;

    @Column(name = "description", columnDefinition = "text", nullable = false)
    @NotBlank(message = "You must add a description")
    private String description;

    @Column(name = "product_color", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductColor productColor;

    @Column(name = "price", nullable = false)
    @Min(value = 1, message = "Price should be bigger then 1")
    private Double price;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotEmpty(message = "Must contain at least 1 image link")
    private List<ProductImage> productImages;

    @Override
    public String toString() {

        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", size=" + size +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", productColor=" + productColor +
                ", price=" + price +
                ", productImages=" + productImages +
                '}';
    }
}
