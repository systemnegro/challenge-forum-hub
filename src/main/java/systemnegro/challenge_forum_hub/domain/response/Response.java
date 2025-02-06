package systemnegro.challenge_forum_hub.domain.response;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import systemnegro.challenge_forum_hub.domain.topic.Topic;
import systemnegro.challenge_forum_hub.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "respostas")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime createdAt;
    private Boolean solution;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User author;

    public Response(User author, CreateResponseDTO createResponseDTO, Topic topic) {
        this.createdAt = LocalDateTime.now();
        this.author = author;
        this.message = createResponseDTO.message();
        this.topic = topic;
        this.solution = false;
    }
}
