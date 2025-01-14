package systemnegro.challenge_forum_hub.domain.topic;

import jakarta.validation.constraints.NotBlank;



public record CreateTopicDTO(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        String authorEmail) {
}
