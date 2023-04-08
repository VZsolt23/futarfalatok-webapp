package hu.nye.futarfalatok.service.impl;

import hu.nye.futarfalatok.dto.DeliveryLocationDTO;
import hu.nye.futarfalatok.entity.DeliveryLocation;
import hu.nye.futarfalatok.exception.DeliveryLocationNotFound;
import hu.nye.futarfalatok.repository.DeliveryLocationRepository;
import hu.nye.futarfalatok.service.DeliveryLocationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryLocationServiceImpl implements DeliveryLocationService {

    private DeliveryLocationRepository deliveryLocationRepository;
    private ModelMapper modelMapper;

    public DeliveryLocationServiceImpl(DeliveryLocationRepository deliveryLocationRepository, ModelMapper modelMapper) {
        this.deliveryLocationRepository = deliveryLocationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DeliveryLocationDTO> findAllDeliveryLocations() {
        return deliveryLocationRepository.findAll()
                .stream()
                .map(deliveryLocation -> modelMapper.map(deliveryLocation, DeliveryLocationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DeliveryLocationDTO> findDeliveryLocationById(Long id) {
        return deliveryLocationRepository.findById(id)
                .map(deliveryLocation -> modelMapper.map(deliveryLocation, DeliveryLocationDTO.class));
    }

    @Override
    public DeliveryLocationDTO createDeliveryLocation(DeliveryLocationDTO deliveryLocationDTO) {
        DeliveryLocation location = modelMapper.map(deliveryLocationDTO, DeliveryLocation.class);
        DeliveryLocation savedLocation = deliveryLocationRepository.save(location);

        return modelMapper.map(savedLocation, DeliveryLocationDTO.class);
    }

    @Override
    public DeliveryLocationDTO updateDeliveryLocation(DeliveryLocationDTO deliveryLocationDTO) {
        Long id = deliveryLocationDTO.getId();
        Optional<DeliveryLocation> location = deliveryLocationRepository.findById(id);

        if (location.isEmpty()) {
            throw new DeliveryLocationNotFound("Given location is not found: " + id);
        }

        DeliveryLocation deliveryLocation = modelMapper.map(deliveryLocationDTO, DeliveryLocation.class);
        DeliveryLocation savedLocation = deliveryLocationRepository.save(deliveryLocation);

        return modelMapper.map(savedLocation, DeliveryLocationDTO.class);
    }

    @Override
    public void deleteDeliveryLocation(Long id) {
        Optional<DeliveryLocation> location = deliveryLocationRepository.findById(id);

        if (location.isPresent()) {
            deliveryLocationRepository.delete(location.get());
        } else {
            throw new DeliveryLocationNotFound("Given location is not found: " + id);
        }
    }
}
