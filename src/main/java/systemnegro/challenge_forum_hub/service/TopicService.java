package systemnegro.challenge_forum_hub.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import systemnegro.challenge_forum_hub.domain.topic.*;
import systemnegro.challenge_forum_hub.domain.user.UserRepository;
import systemnegro.challenge_forum_hub.infra.exception.DuplicateTopicException;

@Service
@AllArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public Topic createTopic(CreateTopicDTO topicDTO, Long userId) {
        if (topicRepository.existsByTitleAndMessage(topicDTO.title(), topicDTO.message())) {
            throw new DuplicateTopicException();
        }
        var author = userRepository.getReferenceById(userId);


        Topic topic = new Topic(topicDTO, author);

        return topicRepository.save(topic);
    }

    public Topic getTopic(Long id) {
        return topicRepository.getReferenceById(id);
    }

    public Page<TopicDetailsDTO> listTopics(Pageable pageable) {
        return topicRepository.findAllByActiveTrue(pageable).map(TopicDetailsDTO::new);
    }

    //TODO verificar se o usuario que quer atualizar ou apagar um topico é o dono do mesmo
    public Topic updateTopic(Long id, UpdateTopicDTO updateTopicDTO) {
        if (topicRepository.existsByTitleAndMessage(updateTopicDTO.title(), updateTopicDTO.message())) {
            throw new DuplicateTopicException();
        }
        var topic = topicRepository.getReferenceById(id);
        topic.update(updateTopicDTO);
        return topic;
    }

    public void deleteTopic(Long id) {
        var topic = topicRepository.findById(id);
        if (topic.isEmpty()) {
            throw new EntityNotFoundException();
        }
        topicRepository.deleteById(id);
    }
}
