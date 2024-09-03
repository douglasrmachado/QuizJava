package com.douglas.Models;

import java.util.ArrayList;
import java.util.Collections;

public class Questao {

    private final String enunciado;
    private final String respostaCorreta;
    private final String[] outrasAlternativas;
    private final ArrayList<String> todasAlternativas;

    /* CONSTRUTOR DE QUESTAO */
    public Questao(String enunciado, String respostaCorreta, String[] outrasAlternativas) {
        this.enunciado = enunciado;
        this.respostaCorreta = respostaCorreta;
        this.outrasAlternativas = outrasAlternativas;
        this.todasAlternativas = new ArrayList<>();
        for(String alternativa:outrasAlternativas){
            todasAlternativas.add(alternativa);
        }
        this.todasAlternativas.add(respostaCorreta);
        Collections.shuffle(todasAlternativas);
    }

    /* RECEBE DADO DO ENUNCIADO */
    public String getEnunciado() {
        return enunciado;
    }

    /* RECEBE DADO DA RESPOSTA CORRETA */
    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    /* RECEBE DADO DAS OUTRAS ALTERNATIVAS */
    public String[] getOutrasAlternativas() {
        return outrasAlternativas;
    }

    /* LISTA DAS ALTERNATIVAS */
    public ArrayList<String> getTodasAlternativas() {
        return todasAlternativas;
    }
}