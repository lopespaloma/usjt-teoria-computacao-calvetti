package br.projeto.analisadorlexico;
import java.util.*;
import java.util.regex.*;

public class AnalisadorLexico {
    // Tipos de tokens
    private static final String[] PALAVRAS_RESERVADAS = {"if", "else", "while", "switch", "int", "double"};
    private static final String[] OPERADORES = {"+", "-", "*", "/", "<", ">", "<=", ">=", "!="};
    private static final String[] SIMBOLOS = {",", ";", ":", "."};  // Inclui ; como símbolo
    private static final String[] PARENTESES = {"(", ")", "[", "]", "{", "}"};
    private static final String ATRIBUICAO = "=";
    private static final Pattern IDENTIFICADOR = Pattern.compile("[a-zA-Z_]\\w{0,29}");
    private static final Pattern REAL = Pattern.compile("\\d{1,9}\\.\\d*([eE][+-]?\\d{1,9})?");
    private static final Pattern INTEIRO = Pattern.compile("\\d{1,9}");

    private TabelaSimbolos tabelaSimbolos = new TabelaSimbolos();

    public void analisar(String codigo) {
        System.out.println("Código fonte:\n" + codigo);
        System.out.println("Análise léxica:");
        codigo = removerComentarios(codigo);
        String[] linhas = codigo.split("\\R");

        for (String linha : linhas) {
            List<String> tokens = separarTokens(linha);
            for (String token : tokens) {
                classificarToken(token);
            }
        }

        tabelaSimbolos.imprimir();
    }

    private List<String> separarTokens(String linha) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\w+|[=<>!]=|\\S").matcher(linha);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }

    private void classificarToken(String token) {
        if (token.isEmpty()) return;

        if (Arrays.asList(PALAVRAS_RESERVADAS).contains(token)) {
            System.out.println(token + " : Palavra Reservada");
            tabelaSimbolos.adicionar(token, "Palavra Reservada", "Palavra-chave reservada pela linguagem");
        } else if (Arrays.asList(OPERADORES).contains(token)) {
            System.out.println(token + " : Operador");
            tabelaSimbolos.adicionar(token, "Operador", "Usado para operações aritméticas ou lógicas");
        } else if (Arrays.asList(SIMBOLOS).contains(token)) {
            System.out.println(token + " : Pontuação");
            tabelaSimbolos.adicionar(token, "Pontuação", "Símbolo de pontuação");
        } else if (Arrays.asList(PARENTESES).contains(token)) {
            System.out.println(token + " : Parêntese");
            tabelaSimbolos.adicionar(token, "Parêntese", "Usado para agrupar expressões");
        } else if (token.equals(ATRIBUICAO)) {
            System.out.println(token + " : Atribuição");
            tabelaSimbolos.adicionar(token, "Atribuição", "Usado para atribuir valor a uma variável");
        } else if (REAL.matcher(token).matches()) {
            if (token.length() <= 9) {
                System.out.println(token + " : Real");
                tabelaSimbolos.adicionar(token, "Real", "Número de ponto flutuante");
            } else {
                System.out.println("Erro: Número real fora do limite: " + token);
            }
        } else if (INTEIRO.matcher(token).matches()) {
            if (token.length() <= 9) {
                System.out.println(token + " : Inteiro");
                tabelaSimbolos.adicionar(token, "Inteiro", "Número inteiro");
            } else {
                System.out.println("Erro: Número inteiro fora do limite: " + token);
            }
        } else if (IDENTIFICADOR.matcher(token).matches()) {
            if (token.length() <= 30) {
                System.out.println(token + " : Identificador");
                tabelaSimbolos.adicionar(token, "Identificador", "Nome de variável ou função");
            } else {
                System.out.println("Erro: Identificador fora do limite: " + token);
            }
        } else {
            System.out.println("Erro: Token desconhecido: " + token);
        }
    }

    private String removerComentarios(String codigo) {
        // Remover comentários de linha
        codigo = codigo.replaceAll("//.*", "");
        // Remover comentários de bloco
        codigo = codigo.replaceAll("/\\*.*?\\*/", "");
        return codigo;
    }
}
