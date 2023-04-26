package hu.nye.futarfalatok.service.impl;

import hu.nye.futarfalatok.dto.DishDTO;
import hu.nye.futarfalatok.dto.UserOrderDTO;
import hu.nye.futarfalatok.entity.Dish;
import hu.nye.futarfalatok.entity.UserOrder;
import hu.nye.futarfalatok.exception.DishNotFound;
import hu.nye.futarfalatok.exception.OrderNotFound;
import hu.nye.futarfalatok.repository.DishRepository;
import hu.nye.futarfalatok.repository.UserOrderRepository;
import hu.nye.futarfalatok.service.UserOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private final UserOrderRepository userOrderRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private final ModelMapper modelMapper;

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
    public UserOrderDTO assignDishToOrder(Long orderId, Long dishId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new DishNotFound("Dish not found!"));
        UserOrder order = userOrderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFound("Order not found!"));

        order.getItems().add(dish);
        UserOrder savedOrder = userOrderRepository.save(order);

        return modelMapper.map(savedOrder, UserOrderDTO.class);
    }

    @Override
    public Optional<Set<DishDTO>> getDishesByOrderId(Long orderId) {
        Optional<UserOrder> optionalOrder = userOrderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Set<DishDTO> dishDTOSet = optionalOrder.get().getItems().stream()
                    .map(dish -> modelMapper.map(dish, DishDTO.class))
                    .collect(Collectors.toSet());
            return Optional.of(dishDTOSet);
        } else {
            return Optional.empty();
        }
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
