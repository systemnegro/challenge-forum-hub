package systemnegro.challenge_forum_hub.domain.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateResponseDTO(@NotNull Long TopicID, @NotBlank String message) {
}
