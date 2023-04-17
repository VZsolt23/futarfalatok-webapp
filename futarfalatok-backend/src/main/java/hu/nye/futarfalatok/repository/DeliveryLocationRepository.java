package hu.nye.futarfalatok.repository;

import hu.nye.futarfalatok.entity.DeliveryLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryLocationRepository extends JpaRepository<DeliveryLocation, Long> {
}
