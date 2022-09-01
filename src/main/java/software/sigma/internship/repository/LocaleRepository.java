package software.sigma.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.sigma.internship.entity.Locale;

import java.util.Optional;

public interface LocaleRepository extends JpaRepository<Locale, Long> {
    Optional<Locale> findLocaleByIsoCode(String isoCode);
}
