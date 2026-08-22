# Delivery Patterns API

API REST para gerenciamento de clientes e pedidos, criada para demonstrar a
aplicação prática de padrões de projeto com Java e Spring Boot.

Este projeto é uma evolução pessoal do laboratório **Explorando Padrões de
Projetos na Prática com Java**, da Digital Innovation One (DIO). A implementação
original foi expandida com um novo domínio de entregas, cálculo de frete,
acompanhamento de pedidos, validações, tratamento de erros, documentação da API
e testes automatizados.

## Objetivo

Demonstrar como padrões de projeto ajudam a separar responsabilidades e tornam
uma aplicação mais fácil de manter e evoluir. O sistema permite:

- cadastrar, consultar, atualizar e excluir clientes;
- criar e consultar pedidos;
- calcular o frete conforme a modalidade de entrega;
- alterar e acompanhar o status de um pedido;
- validar os dados recebidos pela API;
- retornar erros HTTP em um formato padronizado;
- explorar e testar os endpoints pelo Swagger UI.

## Padrões de projeto

| Padrão | Aplicação no projeto |
| --- | --- |
| **Strategy** | `CalculadoraFrete` define o contrato das diferentes regras de frete. |
| **Factory** | `CalculadoraFreteFactory` seleciona a Strategy correspondente à modalidade. |
| **Facade** | `PedidoFacade` coordena cliente, cálculo do frete e persistência do pedido. |
| **Repository** | Os repositórios Spring Data abstraem o acesso ao banco de dados. |
| **Singleton** | Services, controllers, factory e facade usam o escopo singleton padrão do Spring. |

As modalidades de frete são implementadas separadamente:

- `NORMAL`: taxa fixa de R$ 12,00;
- `EXPRESSA`: taxa fixa de R$ 25,00;
- `RETIRADA`: sem custo de frete.

## Tecnologias

- Java 17
- Spring Boot 3.5.4
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- Springdoc OpenAPI e Swagger UI
- JUnit 5 e AssertJ
- Maven

## Organização do código

```text
src/main/java/com/ramonthalison/delivery
├── cliente    # CRUD e persistência de clientes
├── erro       # Exceções e respostas HTTP padronizadas
├── frete      # Strategy e Factory do cálculo de frete
└── pedido     # Pedidos, Facade, status e persistência
```

## Endpoints

### Clientes

| Método | Rota | Descrição |
| --- | --- | --- |
| `POST` | `/clientes` | Cadastra um cliente. |
| `GET` | `/clientes` | Lista todos os clientes. |
| `GET` | `/clientes/{id}` | Consulta um cliente pelo identificador. |
| `PUT` | `/clientes/{id}` | Atualiza os dados de um cliente. |
| `DELETE` | `/clientes/{id}` | Exclui um cliente. |

### Pedidos

| Método | Rota | Descrição |
| --- | --- | --- |
| `POST` | `/pedidos` | Cria um pedido e calcula o frete. |
| `GET` | `/pedidos` | Lista todos os pedidos. |
| `GET` | `/pedidos/{id}` | Consulta um pedido pelo identificador. |
| `PATCH` | `/pedidos/{id}/status?status={status}` | Altera o status do pedido. |

Status aceitos: `CRIADO`, `EM_PREPARACAO`, `ENVIADO` e `ENTREGUE`.

## Como executar

### Pré-requisitos

- JDK 17 ou superior;
- Git.

O Maven não precisa estar instalado globalmente, pois o repositório mantém o
Maven Wrapper.

Clone o repositório e acesse sua pasta:

```bash
git clone https://github.com/RamonThalison/lab-padroes-projeto-spring.git
cd lab-padroes-projeto-spring
```

No Windows, inicie a aplicação com:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

Depois da inicialização, acesse:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- especificação OpenAPI: `http://localhost:8080/v3/api-docs`
- console H2: `http://localhost:8080/h2-console`

Para entrar no H2, utilize:

```text
JDBC URL: jdbc:h2:mem:delivery
User Name: sa
Password: deixe em branco
```

## Exemplo de uso

Primeiro, cadastre um cliente:

```http
POST /clientes
Content-Type: application/json

{
  "nome": "Ana",
  "cep": "01001000"
}
```

Use o `id` retornado para criar um pedido:

```http
POST /pedidos
Content-Type: application/json

{
  "clienteId": 1,
  "valor": 120.50,
  "tipoEntrega": "EXPRESSA"
}
```

Altere o status do pedido:

```http
PATCH /pedidos/1/status?status=EM_PREPARACAO
```

## Testes

No Windows:

```powershell
.\mvnw.cmd test
```

No Linux ou macOS:

```bash
./mvnw test
```

Os testes unitários validam separadamente as estratégias de frete normal,
expresso e retirada. O projeto foi verificado com três testes aprovados e sem
falhas.

## Validações e tratamento de erros

A API valida campos obrigatórios, tamanho do nome, formato do CEP, valor do
pedido e modalidade de entrega. Erros de validação retornam `400 Bad Request`,
enquanto clientes ou pedidos inexistentes retornam `404 Not Found`.

## Origem do projeto

Projeto-base: [Digital Innovation One — Padrões de Projeto com Spring](https://github.com/digitalinnovationone/lab-padroes-projeto-spring).

Esta versão foi reorganizada e evoluída para fins de estudo e portfólio.

## Autor

Desenvolvido por [Ramon Thalison](https://github.com/RamonThalison).
