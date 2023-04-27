package hu.nye.futarfalatok.controller;

import hu.nye.futarfalatok.dto.DeliveryLocationDTO;
import hu.nye.futarfalatok.service.DeliveryLocationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/locations")
public class DeliveryLocationController {

    private DeliveryLocationService deliveryLocationService;

    public DeliveryLocationController(DeliveryLocationService deliveryLocationService) {
        this.deliveryLocationService = deliveryLocationService;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryLocationDTO>> allLocations() {
        return ResponseEntity.ok(deliveryLocationService.findAllDeliveryLocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryLocationDTO> givenLocation(@PathVariable Long id) {
        Optional<DeliveryLocationDTO> location = deliveryLocationService.findDeliveryLocationById(id);
        return location.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<DeliveryLocationDTO> createLocation(@Valid @RequestBody DeliveryLocationDTO locationDTO) {
        DeliveryLocationDTO location = deliveryLocationService.createDeliveryLocation(locationDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(location);
    }

    @PutMapping()
    public ResponseEntity<DeliveryLocationDTO> updateLocation(@Valid @RequestBody DeliveryLocationDTO locationDTO) {
        DeliveryLocationDTO location = deliveryLocationService.updateDeliveryLocation(locationDTO);
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        deliveryLocationService.deleteDeliveryLocation(id);
        return ResponseEntity.noContent().build();
    }
}
