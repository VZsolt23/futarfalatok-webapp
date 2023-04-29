package hu.nye.futarfalatok.dto;

import hu.nye.futarfalatok.entity.Dish;
import hu.nye.futarfalatok.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDishDTO {

    private Long id;

    private Restaurant rest;

    private Dish food;

    private int price;
}
