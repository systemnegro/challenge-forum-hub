package systemnegro.challenge_forum_hub.domain.user;

public record UserDetailsDTO(Long id, String name, String email) {
    public UserDetailsDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
