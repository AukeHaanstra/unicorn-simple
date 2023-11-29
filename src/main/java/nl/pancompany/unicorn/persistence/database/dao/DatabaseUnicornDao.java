package nl.pancompany.unicorn.persistence.database.dao;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.application.domain.Unicorn;
import nl.pancompany.unicorn.application.domain.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.service.Dao;
import nl.pancompany.unicorn.persistence.database.model.UnicornJpaEntity;
import nl.pancompany.unicorn.persistence.database.repository.UnicornRepo;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@RequiredArgsConstructor
@Transactional
public class DatabaseUnicornDao implements Dao<Unicorn, UnicornId> {

    private final UnicornRepo unicornRepo;
    private final UnicornJpaMapper mapper;

    @Override
    public Unicorn findUnicorn(UnicornId unicornId) {
        UnicornJpaEntity unicorn = unicornRepo.findById(unicornId.value()).orElseThrow(EntityNotFoundException::new);
        return mapper.map(unicorn);
    }

    @Override
    public Unicorn save(Unicorn unicorn) {
        return mapper.map(unicornRepo.save(mapper.map(unicorn)));
    }

    @Override
    public long count() {
        return unicornRepo.count();
    }
}
