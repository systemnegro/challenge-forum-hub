package systemnegro.challenge_forum_hub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import systemnegro.challenge_forum_hub.domain.user.UpdateUserDTO;
import systemnegro.challenge_forum_hub.domain.user.UserDetailsDTO;
import systemnegro.challenge_forum_hub.domain.user.UserRegisterDTO;
import systemnegro.challenge_forum_hub.service.UserService;

@RestController
@RequestMapping("usuarios")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/cadastro")
    public ResponseEntity<UserDetailsDTO> register(@RequestBody @Valid UserRegisterDTO registerDTO, UriComponentsBuilder uriBuilder) {
        var user = service.register(registerDTO);
        var uri = uriBuilder.path("/usuarios{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDetailsDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable Long id) {
        var user = service.getUser(id);
        return ResponseEntity.ok(new UserDetailsDTO(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsDTO> update(@RequestBody UpdateUserDTO updateUserDTO, @PathVariable Long id) {
        var updatedUser = service.updateUser(updateUserDTO, id);
        return ResponseEntity.ok(new UserDetailsDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
