package systemnegro.challenge_forum_hub.infra.exception;

public class DuplicateTopicException extends RuntimeException {
    public DuplicateTopicException() {
        super("Já existe um tópico com o mesmo título e mensagem.");
    }
}
