package systemnegro.challenge_forum_hub.domain.response;

import systemnegro.challenge_forum_hub.domain.topic.TopicDetailsDTO;
import systemnegro.challenge_forum_hub.domain.user.UserDetailsDTO;

import java.time.LocalDateTime;

public record ResponseDetailsDTO(Long id, String message, LocalDateTime createdAt, UserDetailsDTO author,
                                 TopicDetailsDTO topic) {
    public ResponseDetailsDTO(Response response) {
        this(response.getId(), response.getMessage(),
                response.getCreatedAt(),
                new UserDetailsDTO(response.getAuthor()),
                new TopicDetailsDTO(response.getTopic()));
    }
}
