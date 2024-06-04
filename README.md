# Analisador Léxico em Java

Este projeto é um analisador léxico simples escrito em Java. O analisador léxico é capaz de processar um trecho de código fonte, identificar tokens e construir uma tabela de símbolos. 

## Funcionalidades

- Reconhece identificadores, palavras reservadas, números inteiros e reais, operadores, símbolos de pontuação e parênteses.
- Elimina caracteres em branco, tabulações e novas linhas.
- Remove comentários de linha (`//`) e de bloco (`/* ... */`).
- Controla o tamanho limite dos nomes dos identificadores, números inteiros e números reais.
- Emite mensagens de advertência ou erro para símbolos fora das especificações.
- Cria uma tabela de símbolos que contém todos os identificadores reconhecidos, juntamente com seus tipos e descrições.

## Estrutura do Projeto

- `AnalisadorLexico.java`: Classe principal que realiza a análise léxica.
- `TabelaSimbolos.java`: Classe que armazena a tabela de símbolos.
- `EntradaTabelaSimbolos.java`: Classe que representa uma entrada na tabela de símbolos.
- `Main.java`: Classe que lê a entrada do usuário e executa o analisador léxico.
