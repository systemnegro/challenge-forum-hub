CREATE TABLE topicos (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    author_id BIGINT NOT NULL,
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES usuarios(id)
);