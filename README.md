# Sistema de Monitoramento de Animais em um Zoológico 🐾

Projeto da disciplina **Certificadora de Competência 3** — UTFPR, Campus Cornélio Procópio.

O objetivo do sistema é facilitar o gerenciamento e o monitoramento dos animais de um zoológico, centralizando as informações em um único lugar. Por meio dele é possível cadastrar animais e espécies, consultar e atualizar seus dados, além de registrar e acompanhar o histórico de alimentação.

Aplicação desktop em Java (Swing + MySQL/JDBC).

## Integrantes

| Integrante | Responsabilidade |
|---|---|
| Gabriel Riul Perissé | Banco de dados |
| Pedro Araujo Bueno de Oliveira | Módulo de animais |
| Lucas Raphael Egídio da Silva | Módulo de espécies e alimentação |
| Luhan Christyan Rodrigues Batista | Interface gráfica |

## Requisitos funcionais

| ID | Descrição | Situação |
|---|---|---|
| RF01 | Cadastro de animais | ✅ Implementado |
| RF02 | Cadastro de diferentes espécies | ✅ Implementado |
| RF03 | Associar animais às espécies | ✅ Implementado |
| RF04 | Armazenar atributos gerais dos animais (nome, idade, peso, sexo) | ✅ Implementado |
| RF05 | Atributos específicos de cada espécie (ave, mamífero, réptil) | ✅ Implementado |
| RF06 | Consultar animais cadastrados por espécie | ✅ Implementado |
| — | Atualização e exclusão de animais | ✅ Implementado |
| — | Registro e histórico de alimentação | ✅ Implementado |

Conforme o Projeto Inicial, esta versão **não inclui autenticação nem perfis de acesso**: o foco é o gerenciamento das informações.

## Regras de negócio principais

- **Animal:** nome obrigatório; idade não negativa; peso maior que zero; sexo, espécie e característica específica obrigatórios.
- **Espécie:** nome e classe (ave, mamífero ou réptil) obrigatórios; o nome é único.
- **Alimentação:** animal, alimento e quantidade (maior que zero) obrigatórios; data e hora registradas automaticamente.
- **Exclusão de animal:** remove também o histórico de alimentação dele, em uma transação (com rollback em caso de falha).

## Tecnologias

Java JDK 21 · Java Swing · MySQL 8 · JDBC · Apache Maven / Maven Wrapper · JUnit 5 · arquitetura em camadas (MVC).

## Instalação e execução

### 1. Pré-requisitos

- **JDK 21** instalado (`java -version`).
- **MySQL Server 8** em execução e o **MySQL Workbench** (ou outro cliente SQL).
- Maven **não** precisa ser instalado: o projeto usa o Maven Wrapper (`mvnw`).

### 2. Obter o código

```powershell
git clone https://github.com/LucasRaphaEgidio/Sistema-De-Monitoramento-de-Animais-de-um-Zoologico
cd Sistema-De-Monitoramento-de-Animais-de-um-Zoologico
```

### 3. Preparar o banco

No MySQL Workbench, abra e execute `database/schema.sql`. Ele cria o banco `zoologico_certificadora` e as tabelas `especies`, `animais` e `alimentacoes`. É **obrigatório**: a aplicação cria as tabelas se faltarem, mas não cria o banco.

Opcionalmente, execute `database/dados-exemplo.sql` para carregar espécies, animais e alimentações de demonstração.

### 4. Configurar o acesso ao banco

A senha nunca fica no código: ela é lida da variável de ambiente `ZOO_DB_PASSWORD`. Defina-a no terminal em que for executar o sistema:

```powershell
$env:ZOO_DB_PASSWORD = 'SUA_SENHA_DO_MYSQL'
```

Por padrão o usuário é `root`. Para usar outro usuário, defina também:

```powershell
$env:ZOO_DB_USER = 'NOME_DO_USUARIO'
```

Porta, host ou nome do banco diferentes podem ser ajustados em `src/main/resources/database.properties`.

### 5. Executar os testes e o sistema

```powershell
.\mvnw.cmd test
.\mvnw.cmd compile exec:java
```

Sem o wrapper: `mvn test` e `mvn compile exec:java`.

### Problemas comuns

| Mensagem | Causa e solução |
|---|---|
| "A senha do MySQL nao foi configurada" | `ZOO_DB_PASSWORD` não foi definida **nesse** terminal. |
| "Nao foi possivel conectar ao MySQL" | Serviço do MySQL parado, porta/usuário diferentes de `3306`/`root`, ou senha incorreta. |
| Erro de banco desconhecido | `database/schema.sql` ainda não foi executado. |

## Testes automatizados

`.\mvnw.cmd test` executa 21 testes JUnit que cobrem a hierarquia de animais e a fábrica (`AnimalFactory`), a igualdade de espécies e as regras de negócio de animais, espécies e alimentação. Os testes de regras não dependem do banco de dados.

## Organização do código

- `model` — entidades e hierarquia de animais (`Animal`, `Ave`, `Mamifero`, `Reptil`, `Especie`, `Alimentacao`).
- `dao` — persistência JDBC.
- `service` — regras de negócio e validações.
- `controller` — intermediação entre telas e regras (MVC).
- `view` — telas Swing.
- `infra` — conexão e criação da estrutura do banco.
- `exception` — exceções da aplicação.
- `database` — script de criação do banco e dados de exemplo.

## Vídeos

Os vídeos da entrega estão listados no arquivo [`Videos`](Videos).
