package nl.pancompany.unicorn.application.mapper;

import nl.pancompany.unicorn.application.domain.Leg;
import nl.pancompany.unicorn.application.domain.Unicorn;
import nl.pancompany.unicorn.application.dto.LegDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
public interface LegDtoMapper {

    @Mapping(target = "unicornId", source = "unicorn.id")
    @Mapping(target = ".", source = "leg")
    LegDto map(Unicorn unicorn, Leg leg);
}
