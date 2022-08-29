package software.sigma.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.sigma.internship.entity.Locale;

public interface LocaleRepository extends JpaRepository<Locale, Long> {
}
