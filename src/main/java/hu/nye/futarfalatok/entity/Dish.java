package hu.nye.futarfalatok.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "dish")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Set<String> courses;

    @NotBlank
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    private float calories;

    private float protein;

    private float carbohydrates;

    private float fat;

    @NotBlank
    @ManyToMany(mappedBy = "dishes")
    private Set<Restaurant> restaurantList;

    @ManyToMany
    @JoinTable(name = "order_item",
            joinColumns = {@JoinColumn(name = "dish_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")}
    )
    private Set<UserOrder> cart;
}
