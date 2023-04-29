package hu.nye.futarfalatok.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonManagedReference
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "rest", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RestaurantDish> restaurantItems = new HashSet<>();

    @Min(value = 0)
    @Max(value = 5)
    private float rating;

    @Min(value = 0)
    @Column(name = "delivery_fee")
    private int deliveryFee;

    @OneToMany(mappedBy = "restaurantId", cascade = CascadeType.ALL)
    private Set<Review> restaurant_reviews = new HashSet<>();
}
