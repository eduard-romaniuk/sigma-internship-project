package software.sigma.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.sigma.internship.entity.StatisticData;

public interface StatisticDataRepository extends JpaRepository<StatisticData, Long> {
}
