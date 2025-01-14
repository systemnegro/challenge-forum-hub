package systemnegro.challenge_forum_hub.domain.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import systemnegro.challenge_forum_hub.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;
    private LocalDateTime createdAt;
    private Boolean active;

    @ManyToOne(optional = false)
    private User author;


    public Topic(CreateTopicDTO topicDTO, User author) {
        this.createdAt = LocalDateTime.now();
        this.active = true;
        this.title = topicDTO.title();
        this.message = topicDTO.message();
        this.author = author;

    }


    public void update(UpdateTopicDTO update) {
        if (update.title() != null) this.title = update.title();
        if (update.message() != null) this.message = update.message();
    }
}
