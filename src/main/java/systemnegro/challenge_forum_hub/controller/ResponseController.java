package systemnegro.challenge_forum_hub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import systemnegro.challenge_forum_hub.domain.response.CreateResponseDTO;
import systemnegro.challenge_forum_hub.domain.response.ResponseDetailsDTO;
import systemnegro.challenge_forum_hub.service.ResponseService;

@RestController
@RequestMapping("/respostas")
@AllArgsConstructor
public class ResponseController {
    private final ResponseService service;

    @PostMapping
    public ResponseEntity<ResponseDetailsDTO> create(@RequestBody @Valid CreateResponseDTO createResponseDTO, UriComponentsBuilder uriBuilder) {
        var response = service.createResp(createResponseDTO);
        var uri = uriBuilder.path("respostas/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseDetailsDTO(response));
    }
}
