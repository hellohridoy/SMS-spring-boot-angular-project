package school.management.system.com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.management.system.com.example.entity.User;
import school.management.system.com.example.enums.UserRole;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByRole(UserRole userRole);

    Optional<User> findFirstByEmail(String email);
}
