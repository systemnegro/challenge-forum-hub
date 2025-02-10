package systemnegro.challenge_forum_hub.domain.topic;




import java.time.LocalDateTime;

public record TopicDetailsDTO(Long id, String title, String message, LocalDateTime createdAt, Boolean active, String authorEmail) {

    public TopicDetailsDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreatedAt(),topic.getActive(), topic.getAuthor().getEmail());
    }




}
