package br.projeto.analisadorlexico;

public class EntradaTabelaSimbolos {
    private String identificador;
    private String tipo;
    private String descricao;

    public EntradaTabelaSimbolos(String identificador, String tipo, String descricao) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }
}
