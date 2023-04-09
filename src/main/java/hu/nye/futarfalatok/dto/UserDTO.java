package hu.nye.futarfalatok.dto;

import hu.nye.futarfalatok.enums.Coupon;
import hu.nye.futarfalatok.entity.Review;
import hu.nye.futarfalatok.entity.UserOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @Size(min = 11, max = 11, message = "Phone number must be exactly 11 digits long")
    @NotBlank
    private String phoneNumber;

    private Set<Coupon> coupons;

    private Set<UserOrder> orders;

    private Set<Review> reviews;
}
