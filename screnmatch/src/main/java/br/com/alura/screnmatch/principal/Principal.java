package br.com.alura.screnmatch.principal;


import br.com.alura.screnmatch.modelos.Dados;
import br.com.alura.screnmatch.modelos.DadosEpisodio;
import br.com.alura.screnmatch.modelos.DadosTemporada;
import br.com.alura.screnmatch.modelos.Episodios;
import br.com.alura.screnmatch.serviço.ConsumistaAPI;
import br.com.alura.screnmatch.serviço.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

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
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
        Dados dados = conversor.obterDados(json, Dados.class);
        List<DadosTemporada> temporadas = new ArrayList<>();

		for(int i= 1; i <= dados.totalTemporadas();i++){
			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") +"&season="+ i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);

		}

//		temporadas.forEach(System.out::println);

        for (int i =0; i< dados.totalTemporadas();i++){
            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
            for(int j=0; j<episodiosTemporada.size();j++){
//                System.out.println(episodiosTemporada.get(j).titulo());

            }

        }
        //temporadas.forEach(t-> t.episodios().forEach(e-> System.out.println(e.titulo())));
        List<DadosEpisodio> dadosEpisodio = temporadas.stream().flatMap(t -> t.episodios().stream()).collect(Collectors.toList());

        List<Episodios> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodios(t.numero(), d))
                ).collect(Collectors.toList());
        episodios.forEach(System.out::println);

        System.out.printf("Digite o nome, ou trecho do titulo do episodio: ");
        var trechoTitulo= scanner.nextLine();
        Optional<Episodios> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst();
        if(episodioBuscado.isPresent()){
            System.out.printf("Episodio encontrado: ");
            System.out.printf("Temporada: " + episodioBuscado.get().getTemporada());

        }else{
            System.out.printf("Episodio não encontrado.");
        }
//        System.out.println("\nTop 10 episódios");
//        episodios.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.printf("Primeiro filtro(N/A)"+e)
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .limit(10)
//                .forEach(System.out::println);


//        System.out.printf("A partir de que ano que voce deseja ver os episodios? ");
//        var ano = scanner.nextInt();
//        scanner.nextLine();
//        LocalDate dataBuscada = LocalDate.of(ano,1,1);
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBuscada))
//                .forEach(e -> System.out.printf(
//                        "Temporada:  " + e.getTemporada() +
//                                "Episódio:  " + e.getTitulo() +
//                                        "Data Lançamento:  " + e.getDataLancamento().format(formatador)+"\n"
//                ));

            Map<Integer, Double>   avaliacoesPorTemporada = episodios.stream().filter(e -> e.getAvaliacao()!= 0)
                    .collect(Collectors.groupingBy(Episodios::getTemporada,
                            Collectors.averagingDouble(Episodios::getAvaliacao)));
        System.out.println(avaliacoesPorTemporada);
        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodios::getAvaliacao));
        System.out.println("Média: " + est.getAverage());
        System.out.println("Melhor episódio: " + est.getMax());
        System.out.println("Pior episódio: " + est.getMin());
        System.out.println("Quantidade: " + est.getCount());

    }
}