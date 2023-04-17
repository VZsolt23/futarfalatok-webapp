package hu.nye.futarfalatok.dto;

import hu.nye.futarfalatok.entity.Dish;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private Long id;

    @NotBlank
    private String name;

    private Set<Dish> dishes;

    @Min(value = 0)
    @Max(value = 5)
    private float rating;

    @Min(value = 0)
    private int deliveryFee;
}
