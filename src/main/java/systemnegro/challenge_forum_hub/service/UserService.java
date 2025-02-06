package systemnegro.challenge_forum_hub.service;


import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import systemnegro.challenge_forum_hub.domain.user.UpdateUserDTO;
import systemnegro.challenge_forum_hub.domain.user.User;
import systemnegro.challenge_forum_hub.domain.user.UserRegisterDTO;
import systemnegro.challenge_forum_hub.domain.user.UserRepository;
import systemnegro.challenge_forum_hub.infra.exception.UserAlreadyExistsException;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    @Transactional
    public User register(UserRegisterDTO registerDTO) {
        if (repository.existsByEmail(registerDTO.email())) {
            throw new UserAlreadyExistsException();
        }
        var encodedPassword = passwordEncoder.encode(registerDTO.password());
        User user = new User(registerDTO, encodedPassword);
        return repository.save(user);
    }

    public User getUser(Long id) {
        return repository.getReferenceById(id);
    }
    @Transactional
    public User updateUser(UpdateUserDTO updateUserDTO, Long id) {
        var user = repository.getReferenceById(id);
        verifyUser(id);
        user.update(updateUserDTO);
        return user;
    }
    @Transactional
    public void deleteUser(Long id) {
        repository.getReferenceById(id);
        verifyUser(id);
        repository.deleteById(id);
    }

    private void verifyUser(Long userID) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (!user.getId().equals(userID)) {
            throw new AccessDeniedException("Você não tem permissão para alterar este usuário.");
        }
    }
}
