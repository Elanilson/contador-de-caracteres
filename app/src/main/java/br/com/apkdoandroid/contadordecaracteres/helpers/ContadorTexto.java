package br.com.apkdoandroid.contadordecaracteres.helpers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContadorTexto {
    public static int contarCaracteres(String texto) {
        return texto.length();
    }

    public static int contarCaracteresSemEspaco(String texto) {
        return texto.replaceAll("\\s+", "").length();
    }

    public static int contarPalavras(String texto) {
        return texto.split("\\s+").length;
    }

    public static int contarPalavrasUnicas(String texto) {
        Set<String> palavrasUnicas = new HashSet<>(Arrays.asList(texto.split("\\s+")));
        return palavrasUnicas.size();
    }

    public static int contarEspacos(String texto) {
        int espacos = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == ' ') {
                espacos++;
            }
        }
        return espacos;
    }


    public static int contarParagrafos(String texto) {
        return texto.split("\n\n").length;
    }

    public static int contarLinhas(String texto) {
        return texto.split("\n").length;
    }

    public static int contarNumeros(String texto) {
        return texto.replaceAll("[^0-9]", "").length();
    }

    public static int contarVogais(String texto) {
        return texto.replaceAll("[^aeiouAEIOUÀ-ÖØ-öø-ÿ]", "").length();
    }

    public static int contarConsoantes(String texto) {
        return texto.replaceAll("[^a-zA-ZÀ-ÖØ-öø-ÿ&&[^aeiouAEIOU]]", "").length();
    }

    public static int contarPontuacao(String texto) {
        return texto.replaceAll("[a-zA-Z0-9\\s]", "").length();
    }

    public static int contarFrases(String texto) {
        return texto.split("[.!?]+\\s*").length;
    }

    public static int contarLetrasMaiusculas(String texto) {
        return texto.replaceAll("[^A-ZÀ-ÖØ-öø-ÿ]", "").length();
    }

    public static int contarLetrasMinusculas(String texto) {
        return texto.replaceAll("[^a-zÀ-ÖØ-öø-ÿ]", "").length();
    }
}
