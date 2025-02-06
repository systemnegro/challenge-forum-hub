package systemnegro.challenge_forum_hub.domain.response;

import systemnegro.challenge_forum_hub.domain.topic.TopicDetailsDTO;
import systemnegro.challenge_forum_hub.domain.user.UserDetailsDTO;

public record ResponseDetailsDTO(Long id, String message, UserDetailsDTO author, TopicDetailsDTO topic) {
    public ResponseDetailsDTO(Response response) {
        this(response.getId(), response.getMessage(),
                new UserDetailsDTO(response.getAuthor()),
                new TopicDetailsDTO(response.getTopic()));
    }
}
