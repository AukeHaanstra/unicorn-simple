package nl.pancompany.unicorn.persistence.inmemory;

import lombok.RequiredArgsConstructor;
import nl.pancompany.unicorn.application.domain.Unicorn;
import nl.pancompany.unicorn.application.domain.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.service.Dao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Profile("test")
@RequiredArgsConstructor
public class InMemoryUnicornDao implements Dao<Unicorn, UnicornId> {

    private final Map<UnicornId, Unicorn> inMemoryStore = new HashMap<>();

    private long nextUnicornId = 1;

    @Override
    public Unicorn findUnicorn(UnicornId unicornId) {
        return inMemoryStore.get(unicornId);
    }

    @Override
    public Unicorn save(Unicorn unicorn) {
        if (unicorn.id() == null || unicorn.id().value() == null) {
            unicorn = new Unicorn(UnicornId.of(nextUnicornId++), unicorn.name(), unicorn.legs());
        }
        inMemoryStore.put(unicorn.id(), unicorn);
        return unicorn;
    }

    @Override
    public long count() {
        return inMemoryStore.size();
    }

    public void clear() {
        inMemoryStore.clear();
    }
}
