package hu.nye.futarfalatok.controller;

import hu.nye.futarfalatok.dto.DishDTO;
import hu.nye.futarfalatok.dto.RestaurantDTO;
import hu.nye.futarfalatok.dto.RestaurantRequestDTO;
import hu.nye.futarfalatok.service.DishService;
import hu.nye.futarfalatok.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private DishService dishService;

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> allRestaurants() {
        return ResponseEntity.ok(restaurantService.findAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> givenRestaurant(@PathVariable Long id) {
        Optional<RestaurantDTO> restaurant = restaurantService.findRestaurantById(id);
        return restaurant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{restaurantId}/dishes")
    public ResponseEntity<Set<DishDTO>> allDishesInRestaurant(@PathVariable Long restaurantId) {
        Optional<Set<DishDTO>> dishesOptional = restaurantService.getDishesByRestaurantId(restaurantId);
        return dishesOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantRequestDTO restaurantDTO) {
        RestaurantDTO restaurant = restaurantService.createRestaurant(restaurantDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(restaurant);
    }

    @PutMapping()
    public ResponseEntity<RestaurantDTO> updateRestaurant(@Valid @RequestBody RestaurantRequestDTO restaurantDTO) {
        RestaurantDTO restaurant = restaurantService.updateRestaurant(restaurantDTO);
        return ResponseEntity.ok(restaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
