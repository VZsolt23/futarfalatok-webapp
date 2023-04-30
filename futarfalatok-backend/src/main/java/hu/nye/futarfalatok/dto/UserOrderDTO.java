package hu.nye.futarfalatok.dto;

import hu.nye.futarfalatok.entity.Dish;
import hu.nye.futarfalatok.entity.Restaurant;
import hu.nye.futarfalatok.entity.User;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderDTO {
    private Long id;

    private User user;

    private Restaurant restaurant;

    private Set<Dish> items;

    private String deliveryTime;

    @Min(value = 0)
    private int price;

    private Date orderDate;
}
