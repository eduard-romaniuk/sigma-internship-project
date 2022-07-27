package software.sigma.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.sigma.internship.entity.Twit;

public interface TwitRepository extends JpaRepository<Twit, Long> {
}
