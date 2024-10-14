package br.com.alura.screenmatch.model;

import java.util.OptionalDouble;

public class Serie {

    private String titulo;
    private String Ano;
    private Integer totalTemporadas;
    private Double avalicao;
    private Categoria genero;
    private String atores;
    private String poster;
    private String sinopse;

    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.Ano = dadosSerie.Ano();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avalicao = OptionalDouble.of(Double.valueOf(dadosSerie.avalicao())).orElse(0);
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
        this.sinopse = dadosSerie.sinopse();
    }
}
