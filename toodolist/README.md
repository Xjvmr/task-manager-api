# 🚀 TaskManager API

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring_Boot-4.0.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot 4.0.5" />
  <img src="https://img.shields.io/badge/H2_Database-005C84?style=for-the-badge&logo=h2&logoColor=white" alt="H2 Database" />
  <img src="https://img.shields.io/badge/Lombok-BC002D?style=for-the-badge&logo=lombok&logoColor=white" alt="Lombok" />
</p>

## 📌 Sobre o Projeto
O **TaskManager API** e uma solucao de backend moderna para o gerenciamento de tarefas (To-Do List). Desenvolvida como uma API RESTful, a aplicacao foca em **seguranca e escalabilidade**, utilizando as versoes mais recentes do ecossistema Java.

O diferencial tecnico reside na implementacao de filtros de seguranca e validacao de credenciais para as rotas de tarefas, com senhas protegidas por algoritmos de hashing avancados.

---

## 🛠️ Stack Tecnologica

O projeto utiliza tecnologias de ponta para garantir um desenvolvimento limpo e seguro:

* **Linguagem:** Java 21 (LTS)
* **Framework Base:** Spring Boot 4.0.5
* **API REST:** Spring Web
* **Persistencia de Dados:** Spring Data JPA
* **Banco de Dados:** H2 Database (In-memory)
* **Seguranca:** BCrypt (at.favre.lib) & Jakarta Servlet Filters
* **Produtividade:** Lombok (reducao de boilerplate)
* **Testes:** JUnit 5 & Spring Test

---

## ✨ Funcionalidades Principais

- [x] **Seguranca Avancada:** Hashing de senhas com BCrypt para protecao de credenciais.
- [x] **Filtros HTTP:** Implementacao com Jakarta Servlet para interceptar e validar requisicoes.
- [x] **Gestao de Utilizadores:** Sistema de registo de novos perfis.
- [x] **Criacao de Tarefas:** Operacao de criar tarefas vinculadas ao utilizador.
- [x] **Persistencia Agil:** Uso de H2 Database para testes e desenvolvimento rapido.

---

## 📦 Dependencias Principais (`pom.xml`)

As escolhas tecnicas no arquivo de configuracao do Maven incluem:
- `spring-boot-starter-web`: Motor para a API REST.
- `spring-boot-starter-data-jpa`: Abstracao de acesso a dados.
- `at.favre.lib:bcrypt:0.10.2`: Biblioteca especializada para seguranca.
- `org.projectlombok:lombok`: Para manutencao de codigo limpo com anotacoes.

---

## 🛣️ Endpoints (Rotas Reais do Projeto)

Base URL: `http://localhost:8080`

| Metodo | Rota | Descricao | Autenticacao |
| :--- | :--- | :--- | :--- |
| `POST` | `/users/cadastro` | Regista um novo utilizador | Nao |
| `POST` | `/tasks/create` | Cria uma nova tarefa | Sim (Basic Auth) |
| `GET` | `/h2-console` | Acesso ao console do H2 | Nao |

### Exemplo - Criar utilizador

`POST /users/cadastro`

```json
{
  "nome": "Joao Victor",
  "userName": "joao",
  "password": "1234"
}
```

### Exemplo - Criar tarefa

`POST /tasks/create` (com Basic Auth)

```json
{
  "description": "Finalizar API",
  "title": "Estudo Spring",
  "startAT": "2026-04-17T09:00:00",
  "endtAT": "2026-04-17T12:00:00",
  "prioridade": "ALTA",
  "idUser": "11111111-2222-3333-4444-555555555555"
}
```

### H2 Console

URL: `http://localhost:8080/h2-console`

Credenciais:
- **JDBC URL:** `jdbc:h2:mem:todolist`
- **User Name:** `admin`
- **Password:** `admin`
- **Driver Class:** `org.h2.Driver`

---

## 🚀 Como Executar

### 1. Pre-requisitos
* Java 21 instalado.
* Maven 3.x (ou usar `mvnw` do projeto).
* IDE (IntelliJ/VS Code) com plugin **Lombok** ativado.

### 2. Passo a Passo
```bash
# Clone o repositorio
git clone https://github.com/Xjvmr/TooDoList.git

# Entre na pasta do projeto
cd TooDoList

# Instale as dependencias
mvn clean install

# Inicie a aplicacao
mvn spring-boot:run
```
