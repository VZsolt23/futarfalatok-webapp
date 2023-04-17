package hu.nye.futarfalatok.repository;

import hu.nye.futarfalatok.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
}
