package hu.nye.futarfalatok.service.impl;

import hu.nye.futarfalatok.dto.UserDTO;
import hu.nye.futarfalatok.entity.User;
import hu.nye.futarfalatok.exception.UserNotFound;
import hu.nye.futarfalatok.repository.UserRepository;
import hu.nye.futarfalatok.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        Long id = userDTO.getId();
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFound("Given user is not found: " + id);
        }

        User updateUser = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(updateUser);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new UserNotFound("Given user is not found: " + id);
        }
    }
}
