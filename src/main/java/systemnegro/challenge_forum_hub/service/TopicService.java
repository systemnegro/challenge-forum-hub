package systemnegro.challenge_forum_hub.service;

import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import systemnegro.challenge_forum_hub.domain.topic.*;
import systemnegro.challenge_forum_hub.domain.user.User;
import systemnegro.challenge_forum_hub.domain.user.UserRepository;
import systemnegro.challenge_forum_hub.infra.exception.DuplicateTopicException;

@Service
@AllArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    @Transactional
    public Topic createTopic(CreateTopicDTO topicDTO) {
        if (topicRepository.existsByTitleAndMessage(topicDTO.title(), topicDTO.message())) {
            throw new DuplicateTopicException();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        var author = userRepository.getReferenceById(user.getId());

        Topic topic = new Topic(topicDTO, author);

        return topicRepository.save(topic);
    }

    public Topic getTopic(Long id) {
        return topicRepository.getReferenceById(id);
    }

    public Page<TopicDetailsDTO> listTopics(Pageable pageable) {
        return topicRepository.findAllByActiveTrue(pageable).map(TopicDetailsDTO::new);
    }

    @Transactional
    public Topic updateTopic(Long id, UpdateTopicDTO updateTopicDTO) {
        if (topicRepository.existsByTitleAndMessage(updateTopicDTO.title(), updateTopicDTO.message())) {
            throw new DuplicateTopicException();
        }
        var topic = topicRepository.getReferenceById(id);
        verifyUser(topic.getAuthor().getId());
        topic.update(updateTopicDTO);
        return topic;
    }

    @Transactional
    public void deleteTopic(Long id) {
        var topic = topicRepository.findById(id);
        if (topic.isEmpty()) {
            throw new EntityNotFoundException();
        }
        verifyUser(topic.get().getAuthor().getId());
        topicRepository.deleteById(id);
    }

    private void verifyUser(Long authorID) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (!user.getId().equals(authorID)) {
            throw new AccessDeniedException("Você não tem permissão para alterar ou excluir este tópico.");
        }
    }
}
