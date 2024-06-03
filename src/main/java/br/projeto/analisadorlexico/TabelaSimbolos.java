package br.projeto.analisadorlexico;
import java.util.*;

public class TabelaSimbolos {
    private List<EntradaTabelaSimbolos> simbolos = new ArrayList<>();  // Mantem a ordem de inserção

    public void adicionar(String identificador, String tipo, String descricao) {
        simbolos.add(new EntradaTabelaSimbolos(identificador, tipo, descricao));
    }

    public void imprimir() {
        System.out.println("\nTabela de símbolos:");
        System.out.printf("%-20s %-20s %-40s%n", "Símbolo", "Tipo", "Descrição");
        System.out.println("----------------------------------------------------------------------");
        for (EntradaTabelaSimbolos entrada : simbolos) {
            System.out.printf("%-20s %-20s %-40s%n", entrada.getIdentificador(), entrada.getTipo(), entrada.getDescricao());
        }
    }
}
