package hu.nye.futarfalatok.repository;

import hu.nye.futarfalatok.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
