package nl.pancompany.unicorn.application.dto;

import nl.pancompany.unicorn.application.domain.Color;
import nl.pancompany.unicorn.application.domain.Leg.LegPosition;
import nl.pancompany.unicorn.application.domain.Leg.LegSize;
import nl.pancompany.unicorn.application.domain.Unicorn.UnicornId;

public record LegDto(UnicornId unicornId, LegPosition legPosition, Color color,
                     LegSize legSize) {
}
