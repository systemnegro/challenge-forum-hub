package systemnegro.challenge_forum_hub.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email, @NotBlank String password) {
}
