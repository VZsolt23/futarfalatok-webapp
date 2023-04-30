package hu.nye.futarfalatok.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    private Long userId;

    private Long restaurantId;

    private int price;

    private List<Long> dishes;
}
