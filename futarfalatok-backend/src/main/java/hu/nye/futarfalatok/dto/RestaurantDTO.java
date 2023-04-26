package hu.nye.futarfalatok.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.nye.futarfalatok.entity.Dish;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class RestaurantDTO {
    private Long id;

    @NotBlank
    private String name;

    private Set<Dish> dishes = new HashSet<>();

    @Min(value = 0)
    @Max(value = 5)
    private float rating;

    @Min(value = 0)
    private int deliveryFee;
}
