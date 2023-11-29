package nl.pancompany.unicorn.persistence.database.dao;

import nl.pancompany.unicorn.application.domain.Leg;
import nl.pancompany.unicorn.application.domain.Unicorn;
import nl.pancompany.unicorn.application.domain.Unicorn.UnicornId;
import nl.pancompany.unicorn.persistence.database.model.UnicornJpaEntity;
import nl.pancompany.unicorn.persistence.database.model.UnicornLegJpaEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface UnicornJpaMapper {
    @Mapping(target = "id", source = "id", qualifiedByName = "mapUnicornId")
    Unicorn map(UnicornJpaEntity unicornJpaEntity);

    @Named("mapUnicornId")
    default UnicornId mapUnicornId(Long value) {
        return UnicornId.of(value);
    }

    UnicornJpaEntity map(Unicorn unicorn);

    @Mapping(target = "unicorn", ignore = true)
    @Mapping(target = "id", ignore = true)
    UnicornLegJpaEntity map(Leg leg);

    default Long map(UnicornId unicornId) {
        return unicornId == null ? null : unicornId.value();
    }
}
