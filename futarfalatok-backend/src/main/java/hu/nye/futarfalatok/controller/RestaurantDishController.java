package hu.nye.futarfalatok.controller;

import hu.nye.futarfalatok.dto.RestaurantDishDTO;
import hu.nye.futarfalatok.service.RestaurantDishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/restaurant-dish")
public class RestaurantDishController {

    private RestaurantDishService restaurantDishService;

    public RestaurantDishController(RestaurantDishService restaurantDishService) {
        this.restaurantDishService = restaurantDishService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDishDTO>> allDishesFromRestaurant() {
        return ResponseEntity.ok(restaurantDishService.findAllDishesInRestaurant());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDishDTO> restaurantDishById(@PathVariable Long id) {
        Optional<RestaurantDishDTO> restaurantDishDTO = restaurantDishService.findById(id);
        return restaurantDishDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{restaurantId}/{dishId}/{price}")
    public ResponseEntity<RestaurantDishDTO> assignDishToRestaurant(
            @PathVariable Long restaurantId,
            @PathVariable Long dishId,
            @PathVariable int price
    ) {
        RestaurantDishDTO restaurantDishDTO = restaurantDishService.assignDishToRestaurant(restaurantId, dishId, price);
        return ResponseEntity.ok(restaurantDishDTO);
    }
}
