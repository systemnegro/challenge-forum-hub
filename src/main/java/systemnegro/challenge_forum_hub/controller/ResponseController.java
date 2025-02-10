package systemnegro.challenge_forum_hub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import systemnegro.challenge_forum_hub.domain.response.CreateResponseDTO;
import systemnegro.challenge_forum_hub.domain.response.ResponseDetailsDTO;
import systemnegro.challenge_forum_hub.domain.response.UpdateResponseDTO;
import systemnegro.challenge_forum_hub.service.ResponseService;

@RestController
@RequestMapping("/respostas")
@AllArgsConstructor
public class ResponseController {
    private final ResponseService service;

    @PostMapping("/topicos/{topicId}")
    public ResponseEntity<ResponseDetailsDTO> create(@RequestBody @Valid CreateResponseDTO createResponseDTO,
                                                     @PathVariable Long topicId,
                                                     UriComponentsBuilder uriBuilder) {
        var response = service.createResp(createResponseDTO, topicId);
        var uri = uriBuilder.path("respostas/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseDetailsDTO(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDetailsDTO> update(@RequestBody @Valid UpdateResponseDTO updateResponseDTO,
                                                     @PathVariable Long id) {
        var updatedResponse = service.updateResp(updateResponseDTO, id);
        return ResponseEntity.ok(new ResponseDetailsDTO(updatedResponse));
    }
}
