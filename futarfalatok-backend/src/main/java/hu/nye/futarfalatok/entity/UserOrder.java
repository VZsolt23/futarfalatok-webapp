package hu.nye.futarfalatok.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import hu.nye.futarfalatok.enums.Coupon;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "order_item",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "dish_id", referencedColumnName = "id")}
    )
    @JsonManagedReference
    private Set<Dish> items = new HashSet<>();

    @NotBlank
    @Column(name = "customer_name")
    private String customerName;

    @OneToOne
    @JoinColumn(name = "customer_address_id", nullable = false)
    private DeliveryLocation customerAddress;

    @Column(name = "delivery_time")
    private String deliveryTime;

    private Coupon coupon;

    @Min(value = 0)
    @Column(name = "price")
    private int price;

    @Column(name = "order_date")
    private Date orderDate;
}
