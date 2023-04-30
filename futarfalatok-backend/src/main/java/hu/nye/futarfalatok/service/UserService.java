package hu.nye.futarfalatok.service;

import hu.nye.futarfalatok.dto.UserDTO;
import hu.nye.futarfalatok.dto.UserRequestDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAllUsers();

    Optional<UserDTO> findUserById(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserRequestDTO userRequestDTO);

    void deleteUser(Long id);
}
