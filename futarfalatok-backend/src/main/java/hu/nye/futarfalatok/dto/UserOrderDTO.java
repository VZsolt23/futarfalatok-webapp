package hu.nye.futarfalatok.dto;

import hu.nye.futarfalatok.entity.Dish;
import hu.nye.futarfalatok.entity.User;
import hu.nye.futarfalatok.enums.Coupon;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderDTO {
    private Long id;

    private User user;

    private Set<Dish> items;

    @NotBlank
    private String customerName;


    private String deliveryTime;

    private Coupon coupon;

    @Min(value = 0)
    private int price;

    private Date orderDate;
}
