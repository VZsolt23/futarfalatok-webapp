package hu.nye.futarfalatok.controller;

import hu.nye.futarfalatok.dto.ReviewDTO;
import hu.nye.futarfalatok.dto.ReviewRequestDTO;
import hu.nye.futarfalatok.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> allReviews() {
        return ResponseEntity.ok(reviewService.findAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> givenReview(@PathVariable Long id) {
        Optional<ReviewDTO> review = reviewService.findReviewById(id);
        return review.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewDTO review = reviewService.createReview(reviewRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(review);
    }

    @PutMapping()
    public ResponseEntity<ReviewDTO> updateReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO review = reviewService.updateReview(reviewDTO);
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
