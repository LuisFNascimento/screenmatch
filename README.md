redução de preço
Copiar
# ScreenMatch - Sistema de Busca e Gerenciamento de Séries

## Descrição
O projeto ScreenMatch é uma aplicação Java que permite buscar, listar, e gerenciar séries e episódios. Ele integra dados de uma API externa (OMDb API) e armazena informações em um repositório local. Oferece funcionalidades como busca por título, ator, categoria, avaliações e episódios específicos, além de consultar séries através de critérios variados.

Além disso, o projeto possui um serviço de tradução usando o ChatGPT (OpenAI API) e uma implementação de consumo de API de forma genérica.

## Funcionalidades
- Buscar séries na API externa
- Gerenciar séries salvas localmente
- Buscar episódios por série
- Listar séries buscadas
- Buscar séries por título, ator ou categoria
- Obter top 5 séries com maior avaliação
- Filtrar séries por total de temporadas e avaliação
- Buscar episódios por trecho do nome
- Obter top episódios de uma série
- Buscar episódios lançados após uma data específica
- Tradução de textos utilizando o ChatGPT

## Como Executar
1. Clone o repositório:
```bash
git clone <URL-do-seu-repositorio>
Compile o projeto Java.
Configure as variáveis de ambiente necessárias, como a chave da API da OpenAI ('OPENAI_API
Execute a classe 'Principal
Depender
Java 11 ou superior
Biblioteca para manipulação de JSON (não especificada, incluir se necessário)
Repositório de dados ('SerieRepository
Biblioteca do OpenAI (okanning/openai-java, por exemplo)
Bota de mola de estrutura
Código de Serviços Adicionais
Classe de Tradução com ChatGPT
Java
Copiar
package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService(System.getenv("OPENAI_APIKEY"));

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
Classe de Consumo de API Genérica
Java
Copiar
package br.com.alura.screenmatch.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    public String obterDados(String endereco) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;
    }
}
Observações
Para funcionalidades envolvendo busca por episódios ou séries, o sistema utiliza uma API externa (OMDb API) com uma chave de API.
Para tradução via ChatGPT, é necessário configurar a variável de ambiente 'OPENAI_API
Adaptar
Contato
Dúvidas ou sugestões, entre em contato: [seu email ou github]

Este projeto demonstra integração com APIs externas, uso de Java Streams, Optionals, além de consumo de APIs REST e integração com OpenAI para traduções automáticas.

Texto
Copiar

Quer que eu ajuste ou adicione algum detalhe extra?
Regenerar
Copiar
Boa resposta
Resposta ruim
