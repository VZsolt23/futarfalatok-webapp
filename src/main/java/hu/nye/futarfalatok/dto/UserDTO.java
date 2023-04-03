package hu.nye.futarfalatok.dto;

import hu.nye.futarfalatok.enums.Coupon;
import hu.nye.futarfalatok.entity.Review;
import hu.nye.futarfalatok.entity.UserOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private Boolean isAdmin;

    @NotBlank
    private String phoneNumber;

    private Set<Coupon> coupons;

    private Set<UserOrder> orders;

    private Set<Review> reviews;
}
