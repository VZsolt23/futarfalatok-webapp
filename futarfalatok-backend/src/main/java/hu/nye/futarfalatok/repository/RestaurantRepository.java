package hu.nye.futarfalatok.repository;

import hu.nye.futarfalatok.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
