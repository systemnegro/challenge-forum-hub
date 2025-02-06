package systemnegro.challenge_forum_hub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systemnegro.challenge_forum_hub.domain.response.CreateResponseDTO;
import systemnegro.challenge_forum_hub.domain.response.Response;
import systemnegro.challenge_forum_hub.domain.response.ResponseRepository;
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
    public Response createResp(CreateResponseDTO createResponseDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        var author = userRepository.getReferenceById(user.getId());
        var topic = topicRepository.getReferenceById(createResponseDTO.TopicID());

        Response response = new Response(author, createResponseDTO, topic);

        return responseRepository.save(response);
    }
}
