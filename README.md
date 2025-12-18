# 🎬 ScreenMatch — Sistema de Busca e Gerenciamento de Séries

## 📌 Visão Geral

O **ScreenMatch** é uma aplicação Java desenvolvida com **Spring Boot** que permite buscar, armazenar, consultar e analisar séries e episódios a partir de dados obtidos de uma API externa (**OMDb API**).

O sistema combina:
- Consumo de APIs REST
- Persistência com **Spring Data JPA**
- Consultas avançadas com **JPQL**
- Uso de **Java Streams** e **Optional**
- Integração com a **OpenAI API (ChatGPT)** para tradução automática de textos

Este projeto tem foco em **boas práticas de arquitetura**, clareza de código e domínio do ecossistema Java moderno.

---

## 🚀 Funcionalidades

### 📺 Séries
- Buscar séries diretamente na **OMDb API**
- Salvar séries no banco de dados local
- Buscar séries por:
    - Título (case-insensitive)
    - Ator
    - Categoria (gênero)
    - Avaliação mínima
    - Total máximo de temporadas
- Listar as **Top 5 séries mais bem avaliadas**
- Consultar séries com episódios mais recentes

### 🎞️ Episódios
- Listar episódios por série
- Buscar episódios por:
    - Trecho do título
    - Temporada específica
    - Ano de lançamento
- Obter os **Top 5 episódios mais bem avaliados** de uma série

### 🌎 Integrações
- Consumo genérico de APIs REST usando `HttpClient`
- Tradução automática de textos utilizando **ChatGPT (OpenAI API)**

---

## 🧱 Arquitetura do Projeto

Estrutura principal do projeto:

br.com.alura.screenmatch
├── model → Entidades JPA (Serie, Episodio, Categoria)
├── repository → Repositórios Spring Data JPA
├── service → Regras de negócio e integrações externas
├── principal → Classe principal de execução

yaml
Copiar código

---

## 🗄️ Persistência de Dados

O projeto utiliza **Spring Data JPA** para persistência de dados, com:

- Consultas derivadas
- Consultas personalizadas com **JPQL**
- Relacionamentos entre entidades (`@OneToMany`)
- Uso de agregações como `MAX`, `ORDER BY`

### Exemplo de Repositório

```java
public interface SerieRepository extends JpaRepository<Serie, Long> {

    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();
}
🌐 Consumo de API Externa (OMDb)
Os dados de séries e episódios são obtidos por meio da OMDb API.

Classe responsável pelo consumo genérico:

java
Copiar código
public class ConsumoApi {

    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        try {
            HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
🤖 Integração com ChatGPT (OpenAI API)
O projeto possui um serviço responsável por traduzir textos automaticamente utilizando o ChatGPT.

java
Copiar código
public class ConsultaChatGPT {

    public static String obterTraducao(String texto) {
        OpenAiService service =
            new OpenAiService(System.getenv("OPENAI_APIKEY"));

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}
⚙️ Tecnologias Utilizadas
Java 11+

Spring Boot

Spring Data JPA

Hibernate

JPQL

OMDb API

OpenAI API (ChatGPT)

HttpClient (Java 11)

Maven

Banco de dados relacional (H2, PostgreSQL, MySQL, etc.)

▶️ Como Executar o Projeto
1️⃣ Clonar o repositório
bash
Copiar código
git clone https://github.com/seu-usuario/screenmatch.git
2️⃣ Configurar variáveis de ambiente
OMDb API
bash
Copiar código
OMDB_APIKEY=sua_chave_aqui
OpenAI API
bash
Copiar código
OPENAI_APIKEY=sua_chave_aqui
3️⃣ Compilar o projeto
bash
Copiar código
mvn clean install
4️⃣ Executar a aplicação
Execute a classe principal:

java
Copiar código
Principal.java
📌 Observações
O uso da OMDb API exige uma chave válida

A funcionalidade de tradução depende da OpenAI API

Projeto ideal para fins educacionais e portfólio

Demonstra domínio de:

APIs REST

JPA avançado

Java moderno

Integrações externas