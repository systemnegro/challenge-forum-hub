package systemnegro.challenge_forum_hub.domain.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import systemnegro.challenge_forum_hub.domain.user.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@AllArgsConstructor
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

    public Topic() {
        this.createdAt = LocalDateTime.now();
        this.active = true;
    }
}
