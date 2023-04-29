package hu.nye.futarfalatok.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonManagedReference
    private Long id;

    private Set<String> courses;

    @NotBlank
    private String name;

    private float calories;

    private float protein;

    private float carbohydrates;

    private float fat;

    @OneToMany(mappedBy = "food", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RestaurantDish> dishItems = new HashSet<>();

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<UserOrder> cart = new HashSet<>();
}
