package hu.nye.futarfalatok.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequestDTO {
    private Long id;

    @NotBlank
    private String name;

    @Min(value = 0)
    @Max(value = 5)
    private float rating;

    @Min(value = 0)
    private int deliveryFee;
}
