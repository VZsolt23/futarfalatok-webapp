package hu.nye.futarfalatok.dto;

import hu.nye.futarfalatok.entity.Restaurant;
import hu.nye.futarfalatok.entity.UserOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {
    private Long id;

    private Set<String> courses;

    @NotBlank
    private String name;

    private String imagePath;

    private float calories;

    private float protein;

    private float carbohydrates;

    private float fat;

    @NotBlank
    private Set<Restaurant> restaurantList;

    private Set<UserOrder> cart;
}
