package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Categoria;

public record SerieDTO (Long id,
                        String titulo,
                        Integer Ano,
                        Double totalTemporadas,
                        String avaliacao,
                        Categoria atores,
                        String poster,
                        String sinopse){
}
