package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.DishDTO;
import hu.nye.futarfalatok.dto.RestaurantDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RestaurantService {

    List<RestaurantDTO> findAllRestaurants();

    Optional<RestaurantDTO> findRestaurantById(Long id);

    Optional<Set<DishDTO>> getDishesByRestaurantId(Long restaurantId);

    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO);

    void deleteRestaurant(Long id);
}
