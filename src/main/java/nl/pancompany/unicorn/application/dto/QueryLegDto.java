package nl.pancompany.unicorn.application.dto;

import jakarta.validation.constraints.NotNull;
import nl.pancompany.unicorn.application.domain.Leg.LegPosition;
import nl.pancompany.unicorn.application.domain.Unicorn.UnicornId;

public record QueryLegDto(@NotNull UnicornId unicornId, @NotNull LegPosition legPosition) {
}
