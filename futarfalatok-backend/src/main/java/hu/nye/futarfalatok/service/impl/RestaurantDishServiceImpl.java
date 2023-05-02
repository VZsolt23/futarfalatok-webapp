package hu.nye.futarfalatok.service.impl;

import hu.nye.futarfalatok.dto.RestaurantDishDTO;
import hu.nye.futarfalatok.entity.Dish;
import hu.nye.futarfalatok.entity.Restaurant;
import hu.nye.futarfalatok.entity.RestaurantDish;
import hu.nye.futarfalatok.exception.DishNotFound;
import hu.nye.futarfalatok.exception.RestaurantNotFound;
import hu.nye.futarfalatok.repository.DishRepository;
import hu.nye.futarfalatok.repository.RestaurantDishRepository;
import hu.nye.futarfalatok.repository.RestaurantRepository;
import hu.nye.futarfalatok.service.RestaurantDishService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantDishServiceImpl implements RestaurantDishService {

    private final RestaurantDishRepository repository;

    private final RestaurantRepository restaurantRepository;

    private final DishRepository dishRepository;

    private final ModelMapper modelMapper;

    public RestaurantDishServiceImpl(RestaurantDishRepository repository, RestaurantRepository restaurantRepository, DishRepository dishRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RestaurantDishDTO> findAllDishesInRestaurant() {
        return repository.findAll()
                .stream()
                .map(restaurantDish -> modelMapper.map(restaurantDish, RestaurantDishDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RestaurantDishDTO> findById(Long id) {
        return repository.findById(id)
                .map(restaurantDish -> modelMapper.map(restaurantDish, RestaurantDishDTO.class));
    }

    @Override
    public RestaurantDishDTO assignDishToRestaurant(Long restaurantId, Long dishId, int price) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new DishNotFound("Dish not found!"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFound("Restaurant not found!"));

        RestaurantDish restaurantDish = new RestaurantDish();
        restaurantDish.setRest(restaurant);
        restaurantDish.setFood(dish);
        restaurantDish.setPrice(price);

        RestaurantDish savedRestaurantDish = repository.save(restaurantDish);

        return modelMapper.map(savedRestaurantDish, RestaurantDishDTO.class);
    }

    @Override
    public RestaurantDishDTO updateDishInRestaurant(RestaurantDishDTO restaurantDishDTO) {
        Long id = restaurantDishDTO.getId();

        Optional<RestaurantDish> restaurantDish = repository.findById(id);

        if (restaurantDish.isEmpty()) {
            throw new RestaurantNotFound("Not found this: " + id);
        }

        RestaurantDish updateRD = modelMapper.map(restaurantDishDTO, RestaurantDish.class);
        RestaurantDish savedRD = repository.save(updateRD);

        return modelMapper.map(savedRD, RestaurantDishDTO.class);
    }

    @Override
    public void deleteDishFromRestaurant(Long id) {
        Optional<RestaurantDish> restaurantDish = repository.findById(id);

        if (restaurantDish.isPresent()) {
            repository.delete(restaurantDish.get());
        } else {
            throw new RestaurantNotFound("Not found this:" + id);
        }
    }

}
