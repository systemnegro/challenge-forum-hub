package systemnegro.challenge_forum_hub.domain.response;

import jakarta.validation.constraints.NotBlank;

public record UpdateResponseDTO(@NotBlank String message) {
}
