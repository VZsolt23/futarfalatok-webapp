package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAllUsers();

    Optional<UserDTO> findUserById(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(Long id);
}
