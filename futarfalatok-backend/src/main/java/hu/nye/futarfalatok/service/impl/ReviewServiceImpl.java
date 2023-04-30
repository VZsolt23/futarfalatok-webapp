package hu.nye.futarfalatok.service.impl;

import hu.nye.futarfalatok.dto.ReviewDTO;
import hu.nye.futarfalatok.dto.ReviewRequestDTO;
import hu.nye.futarfalatok.entity.Restaurant;
import hu.nye.futarfalatok.entity.Review;
import hu.nye.futarfalatok.entity.User;
import hu.nye.futarfalatok.exception.ReviewNotFound;
import hu.nye.futarfalatok.repository.RestaurantRepository;
import hu.nye.futarfalatok.repository.ReviewRepository;
import hu.nye.futarfalatok.repository.UserRepository;
import hu.nye.futarfalatok.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private ModelMapper modelMapper;

    private UserRepository userRepository;

    private RestaurantRepository restaurantRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper,
                             UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<ReviewDTO> findAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDTO> findReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(review -> modelMapper.map(review, ReviewDTO.class));
    }

    @Override
    public ReviewDTO createReview(ReviewRequestDTO reviewRequestDTO) {
        User user = userRepository.findById(reviewRequestDTO.getUserId()).get();
        Restaurant restaurant = restaurantRepository.findById(reviewRequestDTO.getRestaurantId()).get();

        Review newReview = new Review();
        newReview.setReviewUserId(user);
        newReview.setRestaurantId(restaurant);
        newReview.setBody(reviewRequestDTO.getComment());

        Review savedReview = reviewRepository.save(newReview);

        return modelMapper.map(savedReview, ReviewDTO.class);
    }

    @Override
    public ReviewDTO updateReview(ReviewDTO reviewDTO) {
        Long id = reviewDTO.getId();
        Optional<Review> review = reviewRepository.findById(id);

        if (review.isEmpty()) {
            throw new ReviewNotFound("Given review is not found: " + id);
        }

        Review updateReview = modelMapper.map(reviewDTO, Review.class);
        Review savedReview = reviewRepository.save(updateReview);

        return modelMapper.map(savedReview, ReviewDTO.class);
    }

    @Override
    public void deleteReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);

        if (review.isPresent()) {
            reviewRepository.delete(review.get());
        } else {
            throw new ReviewNotFound("Given review is not found: " + id);
        }
    }
}
