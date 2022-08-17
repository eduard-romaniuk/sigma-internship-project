package software.sigma.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.sigma.internship.entity.Fund;

public interface FundRepository extends JpaRepository<Fund, Long> {
}
