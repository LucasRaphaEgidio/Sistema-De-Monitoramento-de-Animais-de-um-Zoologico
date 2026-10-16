# Sistema de Monitoramento de Animais em um Zoológico 🐾

Projeto da disciplina **Certificadora de Competência 3** — UTFPR, Campus Cornélio Procópio.

Aplicação desktop em Java (Swing + MySQL/JDBC) para cadastro e gerenciamento de animais, espécies e histórico de alimentação de um zoológico.

> **Estado atual: MVP / entrega parcial (17/10).

## Integrantes

| Integrante | Responsabilidade |
|---|---|
| Gabriel Riul Perissé | Banco de dados |
| Pedro Araujo Bueno de Oliveira | Módulo de animais |
| Lucas Raphael Egídio da Silva | Módulo de espécies e alimentação |
| Luhan Christyan Rodrigues Batista | Interface gráfica |

## Requisitos funcionais — situação no MVP

| ID | Descrição | Situação |
|---|---|---|
| RF01 | Cadastro de animais | ✅ Implementado |
| RF02 | Cadastro de diferentes espécies | ✅ Implementado |
| RF03 | Associar animais às espécies | ✅ Implementado |
| RF04 | Armazenar atributos gerais dos animais (nome, idade, peso, sexo) | ✅ Implementado |
| RF05 | Atributos específicos de cada espécie (ave, mamífero, réptil) | ✅ Implementado |
| RF06 | Consultar animais cadastrados por espécie | ✅ Implementado |

## Pendências para o projeto final

- Integração e revisão dos módulos pela equipe.
- Testes adicionais (persistência, serviços) e validações.
- Documentação final de instalação e execução.
- Vídeos de instalação, apresentação e individuais.
- Implementações de telas para login de acesso

## Tecnologias

Java JDK 21 · Java Swing · MySQL 8 · JDBC · Apache Maven / Maven Wrapper · JUnit 5 · arquitetura em camadas (MVC).


LINK- GUIA PARA INSTALAÇÃO E EXECUÇÃO DO SISTEMA: 


## Preparar o banco

No MySQL Workbench, execute `database/schema.sql` (cria o banco `zoologico_certificadora` e as tabelas). Opcionalmente, execute `database/dados-exemplo.sql` para carregar dados de demonstração.

Defina a senha do usuário `root` no terminal (nunca no código):

```powershell
$env:ZOO_DB_PASSWORD = 'SUA_SENHA_DO_MYSQL'
```

Para usar outro usuário que não o `root`, defina também `$env:ZOO_DB_USER = 'NOME_DO_USUARIO'`.

Porta ou nome do banco diferentes podem ser ajustados em `src/main/resources/database.properties`.

## Executar

```powershell
.\mvnw.cmd test
.\mvnw.cmd compile exec:java
```

Sem o wrapper: `mvn test` e `mvn compile exec:java`.

## Organização do código

- `model` — entidades e hierarquia de animais (`Animal`, `Ave`, `Mamifero`, `Reptil`, `Especie`, `Alimentacao`).
- `dao` — persistência JDBC.
- `service` — regras de negócio e validações.
- `controller` — intermediação entre telas e regras (MVC).
- `view` — telas Swing.
- `infra` — conexão e criação da estrutura do banco.
- `exception` — exceções da aplicação.
