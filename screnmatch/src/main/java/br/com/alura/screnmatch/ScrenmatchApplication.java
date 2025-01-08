package br.com.alura.screnmatch;

import br.com.alura.screnmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScrenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
//		List<DadosTemporada> temporadas = new ArrayList<>();
//
//		for(int i= 1; i <= dados.totalTemporadas();i++){
//			json = consumo.obterDados("https://omdbapi.com/?t=gilmore+girls&season="+i+"&apikey=6585022c");
//			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
//			temporadas.add(dadosTemporada);
//
//		}
//
//		temporadas.forEach(System.out::println);
	}
}
