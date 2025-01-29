package systemnegro.challenge_forum_hub.infra.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Usuário já existe");
    }
}
