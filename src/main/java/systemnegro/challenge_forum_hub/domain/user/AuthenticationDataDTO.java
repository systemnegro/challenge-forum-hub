package systemnegro.challenge_forum_hub.domain.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDataDTO(@NotBlank String login,
                                    @NotBlank String password) {
}
