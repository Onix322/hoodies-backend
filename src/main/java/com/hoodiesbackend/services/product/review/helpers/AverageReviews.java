package com.hoodiesbackend.services.product.review.helpers;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.product.review.Review;
import com.hoodiesbackend.repositories.product.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

public class AverageReviews {

    public static Integer calculateAverageForAllReviews(Product product, ReviewRepository reviewRepository) {
        List<List<Review>> averageRatingForEachScore = new ArrayList<>();
        int totalReviews = 0;
        List<Integer> sumPerScore = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            List<Review> reviews = sortByScore(product, i, reviewRepository);
            averageRatingForEachScore.add(reviews);
            totalReviews += reviews.size();
            sumPerScore.add(reviews.size() * i);
        }

        return sumAll(sumPerScore) / totalReviews;
    }

    public static Integer sumAll(List<Integer> integers){

        Integer sum = 0;
        for(Integer i : integers){
            sum += i;
        }

        return sum;
    }

    public static List<Review> sortByScore(Product product, Integer score, ReviewRepository reviewRepository) {

        return reviewRepository.findAllByProductId(product.getId())
                .stream()
                .filter(review -> review.getScore().equals(score))
                .toList();
    }
}
