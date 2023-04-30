package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.ReviewDTO;
import hu.nye.futarfalatok.dto.ReviewRequestDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<ReviewDTO> findAllReviews();

    Optional<ReviewDTO> findReviewById(Long id);

    ReviewDTO createReview(ReviewRequestDTO reviewRequestDTO);

    ReviewDTO updateReview(ReviewDTO reviewDTO);

    void deleteReview(Long id);
}
