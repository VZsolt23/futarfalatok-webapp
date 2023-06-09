package hu.nye.futarfalatok.dto;

import hu.nye.futarfalatok.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;

    private User reviewUserId;

    private long restaurantId;

    private String body;
}
