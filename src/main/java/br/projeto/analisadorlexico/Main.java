package br.projeto.analisadorlexico;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder codigo = new StringBuilder();
        String linha;

        System.out.println("Digite o código fonte (digite 'FIM' em uma nova linha para finalizar):");

        while (!(linha = scanner.nextLine()).equals("FIM")) {
            codigo.append(linha).append("\n");
        }

        AnalisadorLexico analisador = new AnalisadorLexico();
        analisador.analisar(codigo.toString());

        scanner.close();
    }
}
