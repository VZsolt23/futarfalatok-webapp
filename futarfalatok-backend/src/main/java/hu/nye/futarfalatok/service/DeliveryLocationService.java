package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.DeliveryLocationDTO;

import java.util.List;
import java.util.Optional;

public interface DeliveryLocationService {

    List<DeliveryLocationDTO> findAllDeliveryLocations();

    Optional<DeliveryLocationDTO> findDeliveryLocationById(Long id);

    DeliveryLocationDTO createDeliveryLocation(DeliveryLocationDTO deliveryLocationDTO);

    DeliveryLocationDTO updateDeliveryLocation(DeliveryLocationDTO deliveryLocationDTO);

    void deleteDeliveryLocation(Long id);
}
