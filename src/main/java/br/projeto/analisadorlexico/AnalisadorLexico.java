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
    private static final Pattern NUMERAL = Pattern.compile("\\d{1,9}(\\.\\d*([eE][+-]?\\d{1,9})?)?");

    private TabelaSimbolos tabelaSimbolosInicial = new TabelaSimbolos();
    private TabelaSimbolos tabelaSimbolosDinamica = new TabelaSimbolos();
    private Set<String> simbolosReconhecidos = new HashSet<>();

    public AnalisadorLexico() {
        inicializarTabelaSimbolos();
    }

    public void analisar(String codigo) {
        System.out.println("\nCódigo fonte fornecido:\n" + codigo);
        System.out.println("Iniciando análise léxica...\n");
        codigo = removerComentarios(codigo);
        String[] linhas = codigo.split("\\R");

        for (String linha : linhas) {
            List<String> tokens = separarTokens(linha);
            for (String token : tokens) {
                classificarToken(token);
            }
        }

        tabelaSimbolosDinamica.imprimir();
    }

    public void resetarAnalise() {
        tabelaSimbolosDinamica = new TabelaSimbolos();
        simbolosReconhecidos.clear();
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

        if (simbolosReconhecidos.contains(token)) return;

        if (Arrays.asList(PALAVRAS_RESERVADAS).contains(token)) {
            System.out.println("Token: " + token + " | Tipo: Palavra Reservada");
            tabelaSimbolosDinamica.adicionar(token, "Palavra Reservada", "Palavra-chave reservada pela linguagem");
            simbolosReconhecidos.add(token);
        } else if (Arrays.asList(OPERADORES).contains(token)) {
            System.out.println("Token: " + token + " | Tipo: Operador");
            tabelaSimbolosDinamica.adicionar(token, "Operador", "Usado para operações aritméticas ou lógicas");
            simbolosReconhecidos.add(token);
        } else if (Arrays.asList(SIMBOLOS).contains(token)) {
            System.out.println("Token: " + token + " | Tipo: Pontuação");
            tabelaSimbolosDinamica.adicionar(token, "Pontuação", "Símbolo de pontuação");
            simbolosReconhecidos.add(token);
        } else if (Arrays.asList(PARENTESES).contains(token)) {
            System.out.println("Token: " + token + " | Tipo: Parêntese");
            tabelaSimbolosDinamica.adicionar(token, "Parêntese", "Usado para agrupar expressões");
            simbolosReconhecidos.add(token);
        } else if (token.equals(ATRIBUICAO)) {
            System.out.println("Token: " + token + " | Tipo: Atribuição");
            tabelaSimbolosDinamica.adicionar(token, "Atribuição", "Usado para atribuir valor a uma variável");
            simbolosReconhecidos.add(token);
        } else if (NUMERAL.matcher(token).matches()) {
            System.out.println("Token: " + token + " | Tipo: Numeral");
            tabelaSimbolosDinamica.adicionar(token, "Numeral", "Número");
            simbolosReconhecidos.add(token);
        } else if (IDENTIFICADOR.matcher(token).matches()) {
            System.out.println("Token: " + token + " | Tipo: Identificador");
            tabelaSimbolosDinamica.adicionar(token, "Identificador", "Nome de variável ou função");
            simbolosReconhecidos.add(token);
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

    public void mostrarTabelaSimbolos() {
        tabelaSimbolosInicial.imprimirAgrupada();
    }

    private void inicializarTabelaSimbolos() {
        for (String palavraReservada : PALAVRAS_RESERVADAS) {
            tabelaSimbolosInicial.adicionar(palavraReservada, "Palavra Reservada", "Palavra-chave reservada pela linguagem");
        }
        for (String operador : OPERADORES) {
            tabelaSimbolosInicial.adicionar(operador, "Operador", "Usado para operações aritméticas ou lógicas");
        }
        for (String simbolo : SIMBOLOS) {
            tabelaSimbolosInicial.adicionar(simbolo, "Pontuação", "Símbolo de pontuação");
        }
        for (String parentese : PARENTESES) {
            tabelaSimbolosInicial.adicionar(parentese, "Parêntese", "Usado para agrupar expressões");
        }
        tabelaSimbolosInicial.adicionar(ATRIBUICAO, "Atribuição", "Usado para atribuir valor a uma variável");

        // Adiciona exemplos de identificadores e números
        tabelaSimbolosInicial.adicionar("A-Z, a-z, _", "Identificador", "Letras maiúsculas, minúsculas ou sublinhado, usado como nome de variável ou função");
        tabelaSimbolosInicial.adicionar("1", "Numeral", "Número");
        tabelaSimbolosInicial.adicionar("1.0", "Numeral", "Número");
    }
}