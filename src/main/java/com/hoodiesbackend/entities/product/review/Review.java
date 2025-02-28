package com.hoodiesbackend.entities.product.review;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "review")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "message", nullable = false)
    @NotNull(message = "Message is mandatory!")
    private String message;

    @Column(name = "score", nullable = false)
    @NotNull(message = "Score is mandatory!")
    @Min(value = 1, message = "Minimum score 1!")
    @Max(value = 5, message = "Maximum score 5!")
    private Integer score;

    @ManyToOne
    @NotNull(message = "Product mandatory!")
    private Product product;

    @ManyToOne
    @NotNull(message = "User mandatory!")
    private User user;
}
