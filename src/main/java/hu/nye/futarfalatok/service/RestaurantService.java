package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.RestaurantDTO;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    List<RestaurantDTO> findAllRestaurants();

    Optional<RestaurantDTO> findRestaurantById(Long id);

    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO);

    void deleteRestaurant(Long id);
}
