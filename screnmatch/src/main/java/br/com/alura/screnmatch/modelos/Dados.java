package br.com.alura.screnmatch.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(@JsonAlias("Title") String titulo, @JsonAlias("imdbRating") String notaImdb, @JsonAlias("totalSeasons")Integer totalTemporadas, @JsonProperty("imdbVotes")String votos) {

}
