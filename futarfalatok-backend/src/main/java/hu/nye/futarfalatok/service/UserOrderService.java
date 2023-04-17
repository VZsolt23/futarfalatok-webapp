package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.UserOrderDTO;

import java.util.List;
import java.util.Optional;

public interface UserOrderService {

    List<UserOrderDTO> findAllUserOrders();

    Optional<UserOrderDTO> findUserOrderById(Long id);

    UserOrderDTO createUserOrder(UserOrderDTO userOrderDTO);

    UserOrderDTO updateUserOrder(UserOrderDTO userOrderDTO);

    void deleteUserOrder(Long id);
}
