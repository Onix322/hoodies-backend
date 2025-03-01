package com.hoodiesbackend.services.product.review;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.product.review.Review;
import com.hoodiesbackend.entities.product.review.helpers.ReviewDto;
import com.hoodiesbackend.entities.product.review.helpers.ReviewMapper;
import com.hoodiesbackend.repositories.product.ReviewRepository;
import com.hoodiesbackend.services.product.ProductService;
import com.hoodiesbackend.services.product.review.helpers.AverageReviews;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    public ReviewService(ReviewRepository reviewRepository, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    public ReviewDto create(Review review) {

        Product product = productService.read(review.getProduct().getId());
        product.setNumberReviews(product.getNumberReviews() + 1);

        Review reviewSaved = reviewRepository.save(review);

        product.setRating(AverageReviews.calculateAverageForAllReviews(product, reviewRepository));

        productService.update(product);
        return ReviewMapper.toDto(reviewSaved);
    }

    public List<ReviewDto> getAll() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewMapper::toDto)
                .toList();
    }
}
