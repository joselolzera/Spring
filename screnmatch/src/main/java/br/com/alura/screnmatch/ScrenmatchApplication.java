package br.com.alura.screnmatch;

import br.com.alura.screnmatch.modelos.Dados;
import br.com.alura.screnmatch.serviço.ConsumistaAPI;
import br.com.alura.screnmatch.serviço.ConverteDados;
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
		var consumo = new ConsumistaAPI();
		var json = consumo.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=46f17329");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		Dados dados = conversor.obterDados(json, Dados.class);
		System.out.println(dados);
	}
}
