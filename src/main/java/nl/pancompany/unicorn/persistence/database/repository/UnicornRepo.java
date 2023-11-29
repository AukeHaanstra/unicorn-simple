package nl.pancompany.unicorn.persistence.database.repository;

import nl.pancompany.unicorn.persistence.database.model.UnicornJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnicornRepo extends JpaRepository<UnicornJpaEntity, Long> {
}
