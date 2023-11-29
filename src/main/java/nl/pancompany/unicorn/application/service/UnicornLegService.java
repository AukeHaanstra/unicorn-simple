package nl.pancompany.unicorn.application.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.application.domain.Leg;
import nl.pancompany.unicorn.application.domain.Unicorn;
import nl.pancompany.unicorn.application.domain.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.dto.LegDto;
import nl.pancompany.unicorn.application.dto.QueryLegDto;
import nl.pancompany.unicorn.application.dto.UpdateLegDto;
import nl.pancompany.unicorn.application.mapper.LegDtoMapper;
import nl.pancompany.unicorn.application.mapper.UpdateLegDtoMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UnicornLegService {

    private final Dao<Unicorn, UnicornId> unicornDao;
    private final UpdateLegDtoMapper updateLegDtoMapper;
    private final LegDtoMapper legDtoMapper;

    public void updateLeg(@Valid UpdateLegDto updateLegDto) {
        Unicorn unicorn = unicornDao.findUnicorn(updateLegDto.unicornId());
        Leg leg = unicorn.getLeg(updateLegDto.legPosition());
        updateLegDtoMapper.updateLeg(leg, updateLegDto);
        unicornDao.save(unicorn);
        log.info("Updated {} leg of unicorn {} (id={}) to color={} and size={}", leg.getLegPosition(), unicorn.name(),
                unicorn.id().value(), leg.getColor(), leg.getLegSize());
    }

    public LegDto getLeg(@Valid QueryLegDto queryLegDto) {
        Unicorn unicorn = unicornDao.findUnicorn(queryLegDto.unicornId());
        return legDtoMapper.map(unicorn, unicorn.getLeg(queryLegDto.legPosition()));
    }
}
