package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.RestaurantDishDTO;

import java.util.List;
import java.util.Optional;

public interface RestaurantDishService {

    List<RestaurantDishDTO> findAllDishesInRestaurant();

    Optional<RestaurantDishDTO> findById(Long id);

    RestaurantDishDTO assignDishToRestaurant(Long restaurantId, Long dishId, int price);

    RestaurantDishDTO updateDishInRestaurant(RestaurantDishDTO restaurantDishDTO);

    void deleteDishFromRestaurant(Long id);
}
