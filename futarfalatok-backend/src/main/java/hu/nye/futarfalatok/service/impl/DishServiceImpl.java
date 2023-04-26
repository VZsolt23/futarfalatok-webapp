package hu.nye.futarfalatok.service.impl;

import hu.nye.futarfalatok.dto.DishDTO;
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
    public DishDTO createDish(DishDTO dishDTO) {
        Dish dish = modelMapper.map(dishDTO, Dish.class);
        Dish savedDish = dishRepository.save(dish);

        return modelMapper.map(savedDish, DishDTO.class);
    }

    @Override
    public DishDTO updateDish(DishDTO dishDTO) {
        Long id = dishDTO.getId();
        Optional<Dish> dish = dishRepository.findById(id);

        if (dish.isEmpty()) {
            throw new DishNotFound("Given dish is not found: " + id);
        }

        Dish updateDish = modelMapper.map(dishDTO, Dish.class);
        Dish savedDish = dishRepository.save(updateDish);

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
