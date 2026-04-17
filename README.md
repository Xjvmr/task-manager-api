# 🚀 TaskManager API

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring_Boot-4.0.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot 4.0.5" />
  <img src="https://img.shields.io/badge/H2_Database-005C84?style=for-the-badge&logo=h2&logoColor=white" alt="H2 Database" />
  <img src="https://img.shields.io/badge/Lombok-BC002D?style=for-the-badge&logo=lombok&logoColor=white" alt="Lombok" />
</p>

## 📌 Sobre o Projeto
O **TaskManager API** é uma solução de backend moderna para o gerenciamento de tarefas (To-Do List). Desenvolvida como uma API RESTful, a aplicação foca em **segurança e escalabilidade**, utilizando as versões mais recentes do ecossistema Java.

O diferencial técnico reside na implementação de filtros de segurança que garantem o isolamento de dados: cada utilizador autenticado gere exclusivamente as suas próprias tarefas, com senhas protegidas por algoritmos de hashing avançados.

---

## 🛠️ Stack Tecnológica

O projeto utiliza tecnologias de ponta para garantir um desenvolvimento limpo e seguro:

* **Linguagem:** Java 21 (LTS)
* **Framework Base:** Spring Boot 4.0.5
* **API REST:** Spring Web
* **Persistência de Dados:** Spring Data JPA
* **Banco de Dados:** H2 Database (In-memory)
* **Segurança:** BCrypt (at.favre.lib) & Jakarta Servlet Filters
* **Produtividade:** Lombok (redução de boilerplate)
* **Testes:** JUnit 5 & Spring Test

---

## ✨ Funcionalidades Principais

- [x] **Segurança Avançada:** Hashing de senhas com BCrypt para proteção de credenciais.
- [x] **Filtros HTTP:** Implementação com Jakarta Servlet para interceptar e validar requisições.
- [x] **Gestão de Utilizadores:** Sistema completo de registo de novos perfis.
- [x] **CRUD de Tarefas:** Operações de Criar, Listar, Atualizar e Eliminar tarefas vinculadas ao utilizador.
- [x] **Persistência Ágil:** Uso de H2 Database para testes e desenvolvimento rápido.

---

## 📦 Dependências Principais (`pom.xml`)

As escolhas técnicas no arquivo de configuração do Maven incluem:
- `spring-boot-starter-web`: Motor para a API REST.
- `spring-boot-starter-data-jpa`: Abstração de acesso a dados.
- `at.favre.lib:bcrypt:0.10.2`: Biblioteca especializada para segurança.
- `org.projectlombok:lombok`: Para manutenção de código limpo com anotações.

---

## 🛣️ Endpoints (Documentação Rápida)

| Método | Recurso | Descrição | Autenticação |
| :--- | :--- | :--- | :--- |
| `POST` | `/users/` | Regista um novo utilizador | Não |
| `POST` | `/todos/` | Cria uma nova tarefa | Sim (Basic Auth) |
| `GET` | `/todos/` | Lista tarefas do utilizador logado | Sim (Basic Auth) |
| `PUT` | `/todos/{id}` | Edita conteúdo ou status | Sim (Basic Auth) |
| `DELETE` | `/todos/{id}` | Remove uma tarefa | Sim (Basic Auth) |

---

## 🚀 Como Executar

### 1. Pré-requisitos
* Java 21 instalado.
* Maven 3.x.
* IDE (IntelliJ/VS Code) com plugin **Lombok** ativado.

### 2. Passo a Passo
```bash
# Clone o repositório
git clone [https://github.com/Xjvmr/TooDoList.git](https://github.com/Xjvmr/TooDoList.git)

# Instale as dependências
mvn clean install

# Inicie a aplicação
mvn spring-boot:run
