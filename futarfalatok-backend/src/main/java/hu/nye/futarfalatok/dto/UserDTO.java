package hu.nye.futarfalatok.dto;

import hu.nye.futarfalatok.entity.Review;
import hu.nye.futarfalatok.entity.UserOrder;
import hu.nye.futarfalatok.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Size(min = 11, max = 11, message = "Phone number must be exactly 11 digits long")
    @NotBlank
    private String phoneNumber;

    private Set<UserOrder> orders = new HashSet<>();

    private Set<Review> reviews = new HashSet<>();
}
