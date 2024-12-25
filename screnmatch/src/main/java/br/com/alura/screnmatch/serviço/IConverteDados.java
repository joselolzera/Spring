package br.com.alura.screnmatch.serviço;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
