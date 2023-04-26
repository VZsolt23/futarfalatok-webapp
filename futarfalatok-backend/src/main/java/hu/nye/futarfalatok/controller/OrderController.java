package hu.nye.futarfalatok.controller;

import hu.nye.futarfalatok.dto.DishDTO;
import hu.nye.futarfalatok.dto.UserOrderDTO;
import hu.nye.futarfalatok.service.DishService;
import hu.nye.futarfalatok.service.UserOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final UserOrderService userOrderService;

    private DishService dishService;

    @GetMapping
    public ResponseEntity<List<UserOrderDTO>> allOrders() {
        return ResponseEntity.ok(userOrderService.findAllUserOrders());
    }

    @GetMapping("/{orderId}/dishes")
    public ResponseEntity<Set<DishDTO>> allDishesInOrder(@PathVariable Long orderId) {
        Optional<Set<DishDTO>> dishesOptional = userOrderService.getDishesByOrderId(orderId);
        return dishesOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{orderId}/dish/{dishId}")
    public ResponseEntity<UserOrderDTO> assignDishToOrder(
            @PathVariable Long orderId,
            @PathVariable Long dishId
    ) {
        UserOrderDTO userOrderDTO = userOrderService.assignDishToOrder(orderId, dishId);
        return ResponseEntity.ok(userOrderDTO);
    }
}
