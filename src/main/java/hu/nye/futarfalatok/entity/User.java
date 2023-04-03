package hu.nye.futarfalatok.entity;

import hu.nye.futarfalatok.enums.Coupon;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    private Set<Coupon> coupons;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserOrder> orders;

    @OneToMany(mappedBy = "reviewUserId", cascade = CascadeType.ALL)
    private Set<Review> reviews;
}
