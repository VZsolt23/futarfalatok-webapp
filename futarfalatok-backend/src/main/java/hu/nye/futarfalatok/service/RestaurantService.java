package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.DishDTO;
import hu.nye.futarfalatok.dto.RestaurantDTO;
import hu.nye.futarfalatok.dto.RestaurantRequestDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RestaurantService {

    List<RestaurantDTO> findAllRestaurants();

    Optional<RestaurantDTO> findRestaurantById(Long id);

    Optional<Set<DishDTO>> getDishesByRestaurantId(Long restaurantId);

    RestaurantDTO createRestaurant(RestaurantRequestDTO restaurantRequestDTO);

    RestaurantDTO updateRestaurant(RestaurantRequestDTO restaurantRequestDTO);

    void deleteRestaurant(Long id);
}
