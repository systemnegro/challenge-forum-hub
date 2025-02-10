package systemnegro.challenge_forum_hub.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systemnegro.challenge_forum_hub.domain.response.CreateResponseDTO;
import systemnegro.challenge_forum_hub.domain.response.Response;
import systemnegro.challenge_forum_hub.domain.response.ResponseRepository;
import systemnegro.challenge_forum_hub.domain.response.UpdateResponseDTO;
import systemnegro.challenge_forum_hub.domain.topic.TopicRepository;
import systemnegro.challenge_forum_hub.domain.user.User;
import systemnegro.challenge_forum_hub.domain.user.UserRepository;

@Service
@RequiredArgsConstructor
public class ResponseService {
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final ResponseRepository responseRepository;

    @Transactional
    public Response createResp(CreateResponseDTO createResponseDTO, Long topicId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        var author = userRepository.getReferenceById(user.getId());
        var topic = topicRepository.findById(topicId).orElseThrow(EntityNotFoundException::new);

        Response response = new Response(author, createResponseDTO, topic);

        return responseRepository.save(response);
    }

    @Transactional
    public Response updateResp(UpdateResponseDTO updateResponseDTO, Long respId) {
        var response = responseRepository.getReferenceById(respId);
        verifyUser(response.getAuthor().getId());
        response.update(updateResponseDTO);
        return response;

    }

    private void verifyUser(Long authorID) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (!user.getId().equals(authorID)) {
            throw new AccessDeniedException("Você não tem permissão para alterar ou excluir este tópico.");
        }
    }
}
