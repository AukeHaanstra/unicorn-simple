package nl.pancompany.unicorn.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.pancompany.unicorn.application.domain.Leg;
import nl.pancompany.unicorn.application.domain.Leg.LegPosition;
import nl.pancompany.unicorn.application.domain.Unicorn.UnicornId;
import nl.pancompany.unicorn.application.dto.LegDto;
import nl.pancompany.unicorn.application.dto.UpdateLegDto;
import nl.pancompany.unicorn.application.dto.QueryLegDto;
import nl.pancompany.unicorn.application.service.UnicornLegService;
import nl.pancompany.unicorn.web.mapper.LegViewMapper;
import nl.pancompany.unicorn.web.model.LegView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UnicornLegController {

    private final LegViewMapper legViewMapper;
    private final UnicornLegService unicornLegService;
    private final ObjectMapper objectMapper;

    @GetMapping(path = "/unicorns/{unicornId}/legs/{legPosition}")
    public ResponseEntity<LegView> getLeg(@PathVariable("unicornId") Long unicornId,
                                          @PathVariable("legPosition") LegPosition legPosition) {
        LegView leg = legViewMapper.map(unicornLegService.getLeg(new QueryLegDto(UnicornId.of(unicornId), legPosition)));
        return ResponseEntity.ok(leg);
    }

    @PatchMapping(path = "/unicorns/{unicornId}/legs/{legPosition}")
    public ResponseEntity<?> patchLeg(@PathVariable("unicornId") Long unicornId,
                                      @PathVariable("legPosition") LegPosition legPosition,
                                      @RequestBody JsonPatch jsonPatch)
            throws JsonPatchException, JsonProcessingException {
        var legPatchDto = enrichWithPatch(new UpdateLegDto(UnicornId.of(unicornId), legPosition, null, null),
                jsonPatch);
        unicornLegService.updateLeg(legPatchDto);
        return ResponseEntity.noContent().build();
    }

    private UpdateLegDto enrichWithPatch(UpdateLegDto leg, JsonPatch patch)
            throws JsonProcessingException, JsonPatchException {
        JsonNode patched = patch.apply(objectMapper.convertValue(leg, JsonNode.class));
        return objectMapper.treeToValue(patched, UpdateLegDto.class);
    }
}
