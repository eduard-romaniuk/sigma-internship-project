package software.sigma.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.sigma.internship.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
