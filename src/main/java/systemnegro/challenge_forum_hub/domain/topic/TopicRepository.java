package systemnegro.challenge_forum_hub.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByTitleAndMessage(String title, String message);

    Page<Topic> findAllByActiveTrue(Pageable pageable);
}

