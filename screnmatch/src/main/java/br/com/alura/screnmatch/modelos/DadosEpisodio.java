package br.com.alura.screnmatch.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record  DadosEpisodio (@JsonAlias("Title") String titulo,@JsonAlias("Episode") Integer numero,@JsonAlias("indbRating") String avaliacao,@JsonAlias("Released") String dataLancamento){

        }

