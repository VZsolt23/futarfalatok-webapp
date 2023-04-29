package hu.nye.futarfalatok.dto;

import hu.nye.futarfalatok.entity.Restaurant;
import hu.nye.futarfalatok.entity.RestaurantDish;
import hu.nye.futarfalatok.entity.UserOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {
    private Long id;

    private Set<String> courses;

    @NotBlank
    private String name;

    private float calories;

    private float protein;

    private float carbohydrates;

    private float fat;

    private Set<RestaurantDish> dishItems = new HashSet<>();

    private int priceOfDish;

    private Set<UserOrder> cart = new HashSet<>();;
}
