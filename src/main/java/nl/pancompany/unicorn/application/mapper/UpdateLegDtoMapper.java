package nl.pancompany.unicorn.application.mapper;

import nl.pancompany.unicorn.application.domain.Leg;
import nl.pancompany.unicorn.application.dto.UpdateLegDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR, nullValuePropertyMappingStrategy = IGNORE)
public interface UpdateLegDtoMapper {

    void updateLeg(@MappingTarget Leg leg, UpdateLegDto legPatchDto);
}
