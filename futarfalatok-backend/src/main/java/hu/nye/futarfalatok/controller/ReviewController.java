package hu.nye.futarfalatok.controller;

import hu.nye.futarfalatok.dto.ReviewDTO;
import hu.nye.futarfalatok.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> allReviews() {
        return ResponseEntity.ok(reviewService.findAllReviews());
    }

    @PostMapping("/add")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO review = reviewService.createReview(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(review);
    }
}
