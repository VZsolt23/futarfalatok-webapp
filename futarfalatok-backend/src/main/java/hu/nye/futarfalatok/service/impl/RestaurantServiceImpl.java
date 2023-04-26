package hu.nye.futarfalatok.service.impl;

import hu.nye.futarfalatok.dto.DishDTO;
import hu.nye.futarfalatok.dto.RestaurantDTO;
import hu.nye.futarfalatok.entity.Dish;
import hu.nye.futarfalatok.entity.Restaurant;
import hu.nye.futarfalatok.exception.DishNotFound;
import hu.nye.futarfalatok.exception.RestaurantNotFound;
import hu.nye.futarfalatok.repository.DishRepository;
import hu.nye.futarfalatok.repository.RestaurantRepository;
import hu.nye.futarfalatok.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private ModelMapper modelMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RestaurantDTO> findAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RestaurantDTO> findRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class));
    }

    /*@Override
    public RestaurantDTO assignDishToRestaurant(Long restaurantId, Long dishId) {
        Set<Dish> dishSet = null;

        Restaurant restaurantOptional = restaurantRepository.findById(restaurantId).get();
        Dish dishOptional = dishRepository.findById(dishId).get();

        dishSet = restaurantOptional.getDishes();
        dishSet.add(dishOptional);
        restaurantOptional.setDishes(dishSet);

        Restaurant savedRestaurant = restaurantRepository.save(restaurantOptional);

        return modelMapper.map(savedRestaurant, RestaurantDTO.class);
    }*/

    /*@Override
    public RestaurantDTO assignDishToRestaurant(Long restaurantId, Long dishId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFound("Restaurant not found"));
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new DishNotFound("Dish not found"));

        restaurant.getDishes().add(dish);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return modelMapper.map(savedRestaurant, RestaurantDTO.class);
    }*/

    @Override
    public RestaurantDTO assignDishToTheRestaurant(Long restaurantId, Long dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new DishNotFound("Dish not found!"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFound("Restaurant not found!"));

        restaurant.getDishes().add(dish);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return modelMapper.map(savedRestaurant, RestaurantDTO.class);
    }

    @Override
    public Optional<Set<DishDTO>> getDishesByRestaurantId(Long restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);

        if (optionalRestaurant.isPresent()) {
            Set<DishDTO> dishDTOSet = optionalRestaurant.get().getDishes().stream()
                    .map(dish -> modelMapper.map(dish, DishDTO.class))
                    .collect(Collectors.toSet());
            return Optional.of(dishDTOSet);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return modelMapper.map(savedRestaurant, RestaurantDTO.class);
    }

    @Override
    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO) {
        Long id = restaurantDTO.getId();
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

        if (restaurant.isEmpty()) {
            throw new RestaurantNotFound("Given restaurant is not found: " + id);
        }

        Restaurant updateRestaurant = modelMapper.map(restaurantDTO, Restaurant.class);
        Restaurant savedRestaurant = restaurantRepository.save(updateRestaurant);

        return modelMapper.map(savedRestaurant, RestaurantDTO.class);
    }

    @Override
    public void deleteRestaurant(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);

        if (restaurant.isPresent()) {
            restaurantRepository.delete(restaurant.get());
        } else {
            throw new RestaurantNotFound("Given restaurant is not found: " + id);
        }
    }
}
