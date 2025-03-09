package com.hoodiesbackend.controllers.product;

import com.hoodiesbackend.entities.product.review.Review;
import com.hoodiesbackend.entities.product.review.helpers.ReviewModifierHelper;
import com.hoodiesbackend.response.Response;
import com.hoodiesbackend.response.ResponseHandler;
import com.hoodiesbackend.services.product.review.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/post")
    public ResponseEntity<Response> create(@RequestBody Review body) {
        return ResponseHandler.ok(reviewService.create(body));
    }

    @GetMapping("/get")
    public ResponseEntity<Response> getAll() {
        return ResponseHandler.ok(reviewService.getAll());
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<Response> getAllFor(@PathVariable Long productId) {
        return ResponseHandler.ok(reviewService.getAllFor(productId));
    }

    @GetMapping("/get/review/{reviewId}")
    public ResponseEntity<Response> getReview(@PathVariable Long reviewId) {
        return ResponseHandler.ok(reviewService.getReview(reviewId));
    }

    @PutMapping("/put")
    public ResponseEntity<Response> update(@RequestBody ReviewModifierHelper body) {
        return ResponseHandler.ok(reviewService.update(body));
    }
}
