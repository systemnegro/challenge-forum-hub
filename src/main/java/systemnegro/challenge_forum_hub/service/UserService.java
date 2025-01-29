package systemnegro.challenge_forum_hub.service;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import systemnegro.challenge_forum_hub.domain.user.User;
import systemnegro.challenge_forum_hub.domain.user.UserRegisterDTO;
import systemnegro.challenge_forum_hub.domain.user.UserRepository;
import systemnegro.challenge_forum_hub.infra.exception.UserAlreadyExistsException;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public User register(UserRegisterDTO registerDTO) {
        if (repository.existsByEmail(registerDTO.email())) {
            throw new UserAlreadyExistsException();
        }
        var encodedPassword = passwordEncoder.encode(registerDTO.password());
        User user = new User(registerDTO, encodedPassword);
        return repository.save(user);
    }
}
