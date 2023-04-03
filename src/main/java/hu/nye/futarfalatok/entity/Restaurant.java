package hu.nye.futarfalatok.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "restaurant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany
    @JoinTable(name = "dish_restaurant",
            joinColumns = {@JoinColumn(name = "restaurant_id")},
            inverseJoinColumns = {@JoinColumn(name = "dish_id")}
    )
    private Set<Dish> dishes;

    @Min(value = 0)
    @Max(value = 5)
    private float rating;

    @Min(value = 0)
    @Column(name = "delivery_fee")
    private int deliveryFee;

    @OneToMany(mappedBy = "restaurantId", cascade = CascadeType.ALL)
    private Set<Review> restaurant_reviews;
}
