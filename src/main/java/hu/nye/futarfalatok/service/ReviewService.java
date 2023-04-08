package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.ReviewDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<ReviewDTO> findAllReviews();

    Optional<ReviewDTO> findReviewById(Long id);

    ReviewDTO createReview(ReviewDTO reviewDTO);

    ReviewDTO updateReview(ReviewDTO reviewDTO);

    void deleteReview(Long id);
}
