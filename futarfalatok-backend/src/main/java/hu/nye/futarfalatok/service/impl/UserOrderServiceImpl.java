package hu.nye.futarfalatok.service.impl;

import hu.nye.futarfalatok.dto.DishDTO;
import hu.nye.futarfalatok.dto.OrderRequestDTO;
import hu.nye.futarfalatok.dto.UserOrderDTO;
import hu.nye.futarfalatok.entity.Dish;
import hu.nye.futarfalatok.entity.Restaurant;
import hu.nye.futarfalatok.entity.User;
import hu.nye.futarfalatok.entity.UserOrder;
import hu.nye.futarfalatok.exception.DishNotFound;
import hu.nye.futarfalatok.exception.OrderNotFound;
import hu.nye.futarfalatok.repository.DishRepository;
import hu.nye.futarfalatok.repository.RestaurantRepository;
import hu.nye.futarfalatok.repository.UserOrderRepository;
import hu.nye.futarfalatok.repository.UserRepository;
import hu.nye.futarfalatok.service.UserOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    private final UserOrderRepository userOrderRepository;

    private UserRepository userRepository;

    private RestaurantRepository restaurantRepository;

    private DishRepository dishRepository;

    private final ModelMapper modelMapper;

    public UserOrderServiceImpl(UserOrderRepository userOrderRepository, UserRepository userRepository, RestaurantRepository restaurantRepository, DishRepository dishRepository, ModelMapper modelMapper) {
        this.userOrderRepository = userOrderRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
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
    public UserOrderDTO createUserOrder(OrderRequestDTO orderRequestDTO) {
        Long userId = orderRequestDTO.getUserId();
        Long restaurantId = orderRequestDTO.getRestaurantId();
        List<Long> dishes = orderRequestDTO.getDishes();
        ;
        int price = orderRequestDTO.getPrice();

        User user = userRepository.findById(userId).get();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();

        int deliveryFee = restaurant.getDeliveryFee();

        String[] options = {"1 óra", "30 perc", "45 perc", "25 perc", "1 óra 10 perc"};
        Random random = new Random();
        String result = options[random.nextInt(options.length)];

        Set<Dish> dishSet = new HashSet<>(dishRepository.findAllById(dishes));

        UserOrder newOrder = new UserOrder();

        newOrder.setUser(user);
        newOrder.setRestaurant(restaurant);
        newOrder.setPrice(price + deliveryFee);
        newOrder.setOrderDate(new Date());
        newOrder.setDeliveryTime(result);
        newOrder.setItems(dishSet);

        UserOrder savedUserOrder = userOrderRepository.save(newOrder);

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
