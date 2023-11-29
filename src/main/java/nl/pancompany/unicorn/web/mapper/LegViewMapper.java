package nl.pancompany.unicorn.web.mapper;

import nl.pancompany.unicorn.application.domain.Unicorn;
import nl.pancompany.unicorn.application.dto.LegDto;
import nl.pancompany.unicorn.web.model.LegView;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
public interface LegViewMapper {

    LegView map(LegDto legDto);

    default Long map(Unicorn.UnicornId unicornId) {
        return unicornId.value();
    }
}
