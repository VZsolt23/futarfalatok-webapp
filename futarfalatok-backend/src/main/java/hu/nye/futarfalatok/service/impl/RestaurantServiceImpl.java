package hu.nye.futarfalatok.service.impl;

import hu.nye.futarfalatok.dto.DishDTO;
import hu.nye.futarfalatok.dto.RestaurantDTO;
import hu.nye.futarfalatok.dto.RestaurantRequestDTO;
import hu.nye.futarfalatok.entity.Restaurant;
import hu.nye.futarfalatok.entity.RestaurantDish;
import hu.nye.futarfalatok.exception.RestaurantNotFound;
import hu.nye.futarfalatok.repository.RestaurantRepository;
import hu.nye.futarfalatok.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final ModelMapper modelMapper;


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

    @Override
    public Optional<Set<DishDTO>> getDishesByRestaurantId(Long restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);

        if (optionalRestaurant.isPresent()) {
            Set<RestaurantDish> restaurantDishSet = optionalRestaurant.get().getRestaurantItems();
            Set<DishDTO> dishDTOSet = restaurantDishSet.stream()
                    .map(restaurantDish -> {
                        DishDTO withPrice = modelMapper.map(restaurantDish.getFood(), DishDTO.class);
                        withPrice.setPriceOfDish(restaurantDish.getPrice());
                        return withPrice;
                    })
                    .collect(Collectors.toSet());

            return Optional.of(dishDTOSet);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public RestaurantDTO createRestaurant(RestaurantRequestDTO restaurantRequestDTO) {

        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setName(restaurantRequestDTO.getName());
        newRestaurant.setRating(restaurantRequestDTO.getRating());
        newRestaurant.setDeliveryFee(restaurantRequestDTO.getDeliveryFee());

        Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);

        return modelMapper.map(savedRestaurant, RestaurantDTO.class);
    }

    @Override
    public RestaurantDTO updateRestaurant(RestaurantRequestDTO restaurantRequestDTO) {
        Long id = restaurantRequestDTO.getId();
        Restaurant restaurant = restaurantRepository.findById(id).get();

        restaurant.setName(restaurantRequestDTO.getName());
        restaurant.setRating(restaurantRequestDTO.getRating());
        restaurant.setDeliveryFee(restaurantRequestDTO.getDeliveryFee());

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

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
