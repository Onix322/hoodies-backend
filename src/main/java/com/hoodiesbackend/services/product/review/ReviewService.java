package com.hoodiesbackend.services.product.review;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.product.review.Review;
import com.hoodiesbackend.entities.product.review.helpers.ReviewDto;
import com.hoodiesbackend.entities.product.review.helpers.ReviewMapper;
import com.hoodiesbackend.entities.product.review.helpers.ReviewModifierHelper;
import com.hoodiesbackend.exceptions.NotFoundException;
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

    public Review create(Review review) {

        Product product = productService.read(review.getProduct().getId());
        product.setNumberReviews(product.getNumberReviews() + 1);

        Review reviewSaved = reviewRepository.save(review);

        product.setRating(AverageReviews.calculateAverageForAllReviews(product, reviewRepository));

        productService.update(product);
        return reviewSaved;
    }

    public ReviewDto update(ReviewModifierHelper reviewModifierHelper){
        Review review = reviewRepository.findById(reviewModifierHelper.getReviewId())
                .orElseThrow(() -> new NotFoundException("Review not found!"));

        review.setMessage(reviewModifierHelper.getMessage());
        review.setScore(reviewModifierHelper.getScore());

        return ReviewMapper.toDto(reviewRepository.save(review));
    }

    public List<ReviewDto> getAll() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewMapper::toDto)
                .toList();
    }

    public List<ReviewDto> getAllFor(Long productId) {
        return reviewRepository.findAllByProductId(productId)
                .stream()
                .map(ReviewMapper::toDto)
                .toList();
    }

    public ReviewDto getReview(Long reviewId){

        Review review = this.reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException("Review not found!"));

        Product product = this.productService.read(review.getProduct().getId());

        review.setProduct(product);

        return ReviewMapper.toDto(review);
    }
}
