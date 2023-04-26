package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.DishDTO;
import hu.nye.futarfalatok.dto.UserOrderDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserOrderService {

    List<UserOrderDTO> findAllUserOrders();

    Optional<UserOrderDTO> findUserOrderById(Long id);

    UserOrderDTO assignDishToOrder(Long orderId, Long dishId);

    Optional<Set<DishDTO>> getDishesByOrderId(Long orderId);

    UserOrderDTO createUserOrder(UserOrderDTO userOrderDTO);

    UserOrderDTO updateUserOrder(UserOrderDTO userOrderDTO);

    void deleteUserOrder(Long id);
}
