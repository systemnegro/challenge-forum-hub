CREATE TABLE respostas (
    id SERIAL PRIMARY KEY,
    message TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    solution BOOLEAN DEFAULT FALSE,
    topic_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    CONSTRAINT fk_respostas_topic FOREIGN KEY (topic_id) REFERENCES topicos(id) ON DELETE CASCADE,
    CONSTRAINT fk_respostas_author FOREIGN KEY (author_id) REFERENCES usuarios(id) ON DELETE CASCADE
);