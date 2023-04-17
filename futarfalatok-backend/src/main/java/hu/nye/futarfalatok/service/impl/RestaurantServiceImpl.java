package hu.nye.futarfalatok.service.impl;

import hu.nye.futarfalatok.dto.RestaurantDTO;
import hu.nye.futarfalatok.entity.Restaurant;
import hu.nye.futarfalatok.exception.RestaurantNotFound;
import hu.nye.futarfalatok.repository.RestaurantRepository;
import hu.nye.futarfalatok.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;
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
