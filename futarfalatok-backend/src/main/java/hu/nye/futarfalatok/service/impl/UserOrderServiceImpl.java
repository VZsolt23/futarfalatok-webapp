package hu.nye.futarfalatok.service.impl;

import hu.nye.futarfalatok.dto.UserOrderDTO;
import hu.nye.futarfalatok.entity.UserOrder;
import hu.nye.futarfalatok.exception.OrderNotFound;
import hu.nye.futarfalatok.repository.UserOrderRepository;
import hu.nye.futarfalatok.service.UserOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    private UserOrderRepository userOrderRepository;
    private ModelMapper modelMapper;

    public UserOrderServiceImpl(UserOrderRepository userOrderRepository, ModelMapper modelMapper) {
        this.userOrderRepository = userOrderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserOrderDTO> findAllUserOrders() {
        return userOrderRepository.findAll()
                .stream()
                .map(userOrder -> modelMapper.map(userOrder, UserOrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserOrderDTO> findUserOrderById(Long id) {
        return userOrderRepository.findById(id)
                .map(userOrder -> modelMapper.map(userOrder, UserOrderDTO.class));
    }

    @Override
    public UserOrderDTO createUserOrder(UserOrderDTO userOrderDTO) {
        UserOrder userOrder = modelMapper.map(userOrderDTO, UserOrder.class);
        UserOrder savedUserOrder = userOrderRepository.save(userOrder);

        return modelMapper.map(savedUserOrder, UserOrderDTO.class);
    }

    @Override
    public UserOrderDTO updateUserOrder(UserOrderDTO userOrderDTO) {
        Long id = userOrderDTO.getId();
        Optional<UserOrder> userOrder = userOrderRepository.findById(id);

        if (userOrder.isEmpty()) {
            throw new OrderNotFound("Given order is not found: " + id);
        }

        UserOrder updateUserOrder = modelMapper.map(userOrderDTO, UserOrder.class);
        UserOrder savedUserOrder = userOrderRepository.save(updateUserOrder);

        return modelMapper.map(savedUserOrder, UserOrderDTO.class);
    }

    @Override
    public void deleteUserOrder(Long id) {
        Optional<UserOrder> userOrder = userOrderRepository.findById(id);

        if (userOrder.isPresent()) {
            userOrderRepository.delete(userOrder.get());
        } else {
            throw new OrderNotFound("Given order is not found: " + id);
        }
    }
}
