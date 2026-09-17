# API de Votação em Assembleias

API REST para gerenciar sessões de votação de pautas em assembleias de cooperativa: cada associado vota uma única vez por sessão, dentro de um prazo definido, e o resultado é apurado ao final.

Implementação de um [desafio técnico encontrado no r/brdev](https://www.reddit.com/r/brdev/comments/1fgh625/ajuda_com_desafio_t%C3%A9cnico_em_javaspring_para_vaga/?tl=pt-br).

## Regras de negócio

- Uma pauta só pode ter uma sessão de votação aberta por vez.
- A sessão tem prazo (padrão de 1 minuto se nenhuma duração for informada); fora do prazo, novos votos são rejeitados.
- Cada associado vota uma única vez por sessão — reforçado tanto na regra de negócio quanto por constraint única no banco.
- O resultado é apurado por contagem de votos SIM/NÃO, retornando APROVADA, REJEITADA ou EMPATE.

## Stack

- Java 21 + Spring Boot 4
- Spring Data JPA / Hibernate
- H2 em memória (padrão) ou MySQL via Docker Compose
- Bean Validation
- springdoc-openapi (Swagger UI)
- Lombok

## Como rodar

```bash
./mvnw spring-boot:run
```

Por padrão usa H2 em memória, sem necessidade de configuração. Para rodar com MySQL, suba o banco com `docker compose up -d` e ajuste `spring.datasource.*` em `application.properties`.

A API sobe em `http://localhost:8080`. Documentação interativa (Swagger UI) em `http://localhost:8080/swagger-ui/index.html`.

## Endpoints

| Método | Rota | Descrição |
|---|---|---|
| POST | `/associados` | Cadastra um associado |
| GET | `/associados` | Lista associados |
| POST | `/pautas` | Cria uma pauta |
| GET | `/pautas/{id}` | Busca uma pauta |
| POST | `/pautas/{pautaId}/sessoes` | Abre sessão de votação para a pauta (`{"duracaoMinutos": 5}`, opcional) |
| POST | `/pautas/{pautaId}/votos` | Registra o voto de um associado (`{"associadoId": 1, "voto": "SIM"}`) |
| GET | `/pautas/{pautaId}/votos/resultado` | Apura o resultado da votação |

Erros de negócio (sessão encerrada, voto duplicado, recurso não encontrado, validação) retornam JSON padronizado com `status` e `mensagem`, tratados centralmente por um `@RestControllerAdvice`.
