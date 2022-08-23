package software.sigma.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.sigma.internship.entity.StatisticData;

import java.time.LocalDate;
import java.util.Optional;

public interface StatisticDataRepository extends JpaRepository<StatisticData, Long> {

    Optional<StatisticData> findByWarDate(LocalDate date);

    Optional<StatisticData> findFirstByOrderByDayNumberDesc();
}
