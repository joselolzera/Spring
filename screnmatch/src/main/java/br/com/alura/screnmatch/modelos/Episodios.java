package br.com.alura.screnmatch.modelos;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class Episodios {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;



    public Episodios(Integer numero, DadosEpisodio dadosEpisodio) {
        this.temporada = numero;
        this.titulo = dadosEpisodio.titulo();
        this.numeroEpisodio = dadosEpisodio.numero();

        try {
            this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());
        } catch (Exception xe) {
            this.avaliacao = 0.0;
        }

        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        } catch (DateTimeParseException e) {
            this.dataLancamento = LocalDate.now();
        }

    }


        public String getTitulo () {
            return titulo;
        }

        public void setTitulo (String titulo){
            this.titulo = titulo;
        }

        public Integer getNumeroEpisodio () {
            return numeroEpisodio;
        }

        public void setNumeroEpisodio (Integer numeroEpisodio){
            this.numeroEpisodio = numeroEpisodio;
        }

        public Double getAvaliacao () {
            return avaliacao;
        }

        public void setAvaliacao (Double avaliacao){
            this.avaliacao = avaliacao;
        }

        public LocalDate getDataLancamento () {
            return dataLancamento;
        }

        public void setDataLancamento (LocalDate dataLancamento){
            this.dataLancamento = dataLancamento;
        }

        public Integer getTemporada () {
            return temporada;
        }

        public void setTemporada (Integer temporada){
            this.temporada = temporada;
        }

    @Override
    public String toString() {
        return "Episodios{" +
                "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento +
                '}';
    }
}