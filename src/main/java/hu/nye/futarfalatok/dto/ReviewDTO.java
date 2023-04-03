package hu.nye.futarfalatok.dto;

import hu.nye.futarfalatok.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;

    private User reviewUserId;

    private long restaurantId;

    private String body;
}
