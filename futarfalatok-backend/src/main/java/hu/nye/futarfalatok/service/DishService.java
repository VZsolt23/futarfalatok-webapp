package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.DishDTO;

import java.util.List;
import java.util.Optional;

public interface DishService {

    List<DishDTO> findAllDishes();

    Optional<DishDTO> findDishById(Long id);

    DishDTO createDish(DishDTO dishDTO);

    DishDTO updateDish(DishDTO dishDTO);

    void deleteDish(Long id);
}
