# Desafio: Challenge Forum Hub

Uma API para gerenciamento de tópicos em um fórum, desenvolvida em Java com Spring Boot, utilizando autenticação JWT e boas práticas de arquitetura.

## Tecnologias Utilizadas

- **Java 21**: Linguagem principal para desenvolvimento.
- **Spring Boot**: Framework para criação de aplicações Java.
- **Spring Security**: Configuração de autenticação e autorização.
- **JWT (JSON Web Tokens)**: Para autenticação stateless.
- **JPA (Java Persistence API)**: Para gerenciamento do banco de dados.
- **PostgreSQL**: Banco de dados utilizado.
- **Lombok**: Para reduzir boilerplate no código.
- **Flyway**: Para controle de versões do banco de dados.
- **Bean Validation**: Para validação de dados de entrada.
- **Spring Web**: Para criação e exposição de endpoints REST.

## Funcionalidades

- Criação, edição e remoção de tópicos no fórum.
- Validação para evitar o cadastro de tópicos duplicados (mesmo título e mensagem).
- Autenticação e geração de token JWT para usuários.
- Autenticação stateless utilizando filtro de segurança personalizado.
- Migrações automatizadas de banco de dados utilizando Flyway.
- Validações automáticas de dados com Bean Validation.

## Estrutura do Projeto

### Pacotes

- **domain**: Contém as entidades do sistema, como `User` e `Topic`.
- **dto**: Define os Data Transfer Objects (DTOs) utilizados nas requisições e respostas.
- **repository**: Interfaces para interação com o banco de dados.
- **service**: Implementação da lógica de negócio.
- **controller**: Controladores REST que expõem os endpoints da API.
- **infra**:
  - *exception*: Contém classes para tratamento de exceções personalizadas.
  - *security*: Configurações e filtros relacionados à segurança.


### Endpoints Principais

#### Autenticação

- `POST /login`: Autentica um usuário e retorna um token JWT.
  - **Body:**
    ```json
    {
        "email": "user@example.com",
        "password": "password"
    }
    ```
  - **Resposta:**
    ```json
    {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

#### Tópicos

- `POST /topicos`: Cria um novo tópico.

  - **Header:**
    ```
    Authorization: Bearer <token>
    ```
  - **Body:**
    ```json
    {
        "title": "Título do tópico",
        "message": "Mensagem do tópico"
    }
    ```

- `GET /topicos`: Lista todos os tópicos

- `GET /topicos/{id}`: Pega um tópico.

- `PUT /topicos/{id}`: Atualiza um tópico existente.

- `DELETE /topicos/{id}`: Remove um tópico.


## Executando o Projeto

### Requisitos

- JDK 21
- Maven
- PostgreSQL

### Passos

1. Clone o repositório:

   ```bash
   git clone https://github.com/systemnegro/challenge-forum-hub
   ```

2. Configure o banco de dados no arquivo `application.properties`:

3. Execute as migrações do banco de dados:

   ```bash
   mvn flyway:migrate
   ```

4. Compile e execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

## Contribuição

Sinta-se à vontade para abrir issues ou enviar pull requests para melhorias e correções.


