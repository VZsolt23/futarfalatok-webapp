package hu.nye.futarfalatok.repository;

import hu.nye.futarfalatok.entity.RestaurantDish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantDishRepository extends JpaRepository<RestaurantDish, Long> {
}
