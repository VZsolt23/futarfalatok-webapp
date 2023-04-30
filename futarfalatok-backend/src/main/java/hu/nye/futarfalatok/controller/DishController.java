package hu.nye.futarfalatok.controller;

import hu.nye.futarfalatok.dto.DishDTO;
import hu.nye.futarfalatok.dto.DishRequestDTO;
import hu.nye.futarfalatok.service.DishService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<List<DishDTO>> allDishes() {
        return ResponseEntity.ok(dishService.findAllDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> givenDish(@PathVariable Long id) {
        Optional<DishDTO> dish = dishService.findDishById(id);
        return dish.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<DishDTO> createDish(@Valid @RequestBody DishRequestDTO dishRequestDTO) {
        DishDTO dish = dishService.createDish(dishRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dish);
    }

    @PutMapping()
    public ResponseEntity<DishDTO> updateDish(@Valid @RequestBody DishRequestDTO dishRequestDTO) {
        DishDTO dish = dishService.updateDish(dishRequestDTO);
        return ResponseEntity.ok(dish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
