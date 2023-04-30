package hu.nye.futarfalatok.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishRequestDTO {
    private Long id;

    private String course;

    @NotBlank
    private String name;

    private float calories;

    private float protein;

    private float carbohydrates;

    private float fat;

}