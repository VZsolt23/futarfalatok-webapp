package hu.nye.futarfalatok.dto;

import hu.nye.futarfalatok.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Size(min = 11, max = 11, message = "Phone number must be exactly 11 digits long")
    @NotBlank
    private String phoneNumber;
}
