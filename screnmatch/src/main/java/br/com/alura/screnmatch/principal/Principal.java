package br.com.alura.screnmatch.principal;


import br.com.alura.screnmatch.modelos.Dados;
import br.com.alura.screnmatch.modelos.DadosEpisodio;
import br.com.alura.screnmatch.modelos.DadosTemporada;
import br.com.alura.screnmatch.serviço.ConsumistaAPI;
import br.com.alura.screnmatch.serviço.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final String ENDERECO= "https://omdbapi.com/?t=";
    private final String API_KEY= "&apikey=6585022c";
    private ConsumistaAPI consumo = new ConsumistaAPI();
    private ConverteDados conversor = new ConverteDados();
    private Scanner scanner = new Scanner(System.in);
    public void exibeMenu(){
        System.out.println("\nDigite o nome da série");
        var nomeSerie = scanner.nextLine();
        var consumo = new ConsumistaAPI();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" "," + ") + API_KEY);
        Dados dados = conversor.obterDados(json, Dados.class);
        List<DadosTemporada> temporadas = new ArrayList<>();

		for(int i= 1; i <= dados.totalTemporadas();i++){
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" "," + ") +"&season="+ i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);

		}

		temporadas.forEach(System.out::println);

        for (int i =0; i< dados.totalTemporadas();i++){
            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            for(int j=0; j<episodiosTemporada.size();j++){
                System.out.println(episodiosTemporada.get(j).titulo());

            }

        }
        //temporadas.forEach(t-> t.episodios().forEach(e-> System.out.println(e.titulo())));


    }
}
