package systemnegro.challenge_forum_hub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import systemnegro.challenge_forum_hub.domain.user.AuthenticationDataDTO;
import systemnegro.challenge_forum_hub.domain.user.User;
import systemnegro.challenge_forum_hub.infra.security.TokenService;
import systemnegro.challenge_forum_hub.infra.security.TokenJwtDTO;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJwtDTO> login(@RequestBody @Valid AuthenticationDataDTO dataDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dataDTO.login(), dataDTO.password());
        var authentication = manager.authenticate(authenticationToken);
        String tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJwtDTO(tokenJWT));
    }

}
