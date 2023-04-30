package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.DishDTO;
import hu.nye.futarfalatok.dto.DishRequestDTO;

import java.util.List;
import java.util.Optional;

public interface DishService {

    List<DishDTO> findAllDishes();

    Optional<DishDTO> findDishById(Long id);

    DishDTO createDish(DishRequestDTO dishRequestDTO);

    DishDTO updateDish(DishRequestDTO dishRequestDTO);

    void deleteDish(Long id);
}
