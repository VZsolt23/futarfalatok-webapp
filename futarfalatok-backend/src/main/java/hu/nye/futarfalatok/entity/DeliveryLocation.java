package hu.nye.futarfalatok.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "delivery_location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @NotBlank
    private String name;

    @NotBlank
    private double distance;
}
