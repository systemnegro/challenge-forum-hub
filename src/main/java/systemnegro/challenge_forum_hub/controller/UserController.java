package systemnegro.challenge_forum_hub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import systemnegro.challenge_forum_hub.domain.user.UserDetailsDTO;
import systemnegro.challenge_forum_hub.domain.user.UserRegisterDTO;
import systemnegro.challenge_forum_hub.service.UserService;

@RestController
@RequestMapping("usuarios")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<UserDetailsDTO> register(@RequestBody @Valid UserRegisterDTO registerDTO, UriComponentsBuilder uriBuilder) {
        var user = service.register(registerDTO);
        var uri = uriBuilder.path("/usuarios{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDetailsDTO(user));
    }
}
