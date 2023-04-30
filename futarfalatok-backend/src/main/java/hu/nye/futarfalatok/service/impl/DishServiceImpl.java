package hu.nye.futarfalatok.service.impl;

import hu.nye.futarfalatok.dto.DishDTO;
import hu.nye.futarfalatok.dto.DishRequestDTO;
import hu.nye.futarfalatok.entity.Dish;
import hu.nye.futarfalatok.exception.DishNotFound;
import hu.nye.futarfalatok.repository.DishRepository;
import hu.nye.futarfalatok.service.DishService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;

    private ModelMapper modelMapper;

    public DishServiceImpl(DishRepository dishRepository, ModelMapper modelMapper) {
        this.dishRepository = dishRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DishDTO> findAllDishes() {
        return dishRepository.findAll()
                .stream()
                .map(dish -> modelMapper.map(dish, DishDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DishDTO> findDishById(Long id) {
        return dishRepository.findById(id)
                .map(dish -> modelMapper.map(dish, DishDTO.class));
    }

    @Override
    public DishDTO createDish(DishRequestDTO dishRequestDTO) {

        Dish newDish = new Dish();
        newDish.setName(dishRequestDTO.getName());
        newDish.setCourse(dishRequestDTO.getCourse());
        newDish.setCalories(dishRequestDTO.getCalories());
        newDish.setProtein(dishRequestDTO.getProtein());
        newDish.setCarbohydrates(dishRequestDTO.getCarbohydrates());
        newDish.setFat(dishRequestDTO.getFat());

        Dish savedDish = dishRepository.save(newDish);

        return modelMapper.map(savedDish, DishDTO.class);
    }

    @Override
    public DishDTO updateDish(DishRequestDTO dishRequestDTO) {
        Long id = dishRequestDTO.getId();
        Dish dish = dishRepository.findById(id).get();

        dish.setName(dishRequestDTO.getName());
        dish.setCourse(dishRequestDTO.getCourse());
        dish.setCalories(dishRequestDTO.getCalories());
        dish.setProtein(dishRequestDTO.getProtein());
        dish.setCarbohydrates(dishRequestDTO.getCarbohydrates());
        dish.setFat(dishRequestDTO.getFat());

        Dish savedDish = dishRepository.save(dish);

        return modelMapper.map(savedDish, DishDTO.class);
    }

    @Override
    public void deleteDish(Long id) {
        Optional<Dish> dish = dishRepository.findById(id);

        if (dish.isPresent()) {
            dishRepository.delete(dish.get());
        } else {
            throw new DishNotFound("Given dish is not found: " + id);
        }
    }
}
