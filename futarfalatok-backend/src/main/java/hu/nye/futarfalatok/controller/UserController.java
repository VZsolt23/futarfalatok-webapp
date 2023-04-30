package hu.nye.futarfalatok.controller;

import hu.nye.futarfalatok.dto.UserDTO;
import hu.nye.futarfalatok.dto.UserRequestDTO;
import hu.nye.futarfalatok.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> allUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or (#id == authentication.principal.id)")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> givenUser(@PathVariable Long id) {
        Optional<UserDTO> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO user = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserDTO user = userService.updateUser(userRequestDTO);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
