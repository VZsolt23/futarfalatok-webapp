package hu.nye.futarfalatok.repository;

import hu.nye.futarfalatok.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
