package br.com.alura.screnmatch.modelos;

public enum Categorias {
    AÇÃO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime");


    private String categoriaOmdb;
    Categorias (String categoriaOmdb){
        this.categoriaOmdb = categoriaOmdb;
    }
    public static Categorias fromString(String text) {
        for (Categorias categoria : Categorias.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
