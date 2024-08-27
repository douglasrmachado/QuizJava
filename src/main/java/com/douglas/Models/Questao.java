package com.douglas.Models;

import java.util.ArrayList;
import java.util.Collections;

public class Questao {

    private final String enunciado;
    private final String respostaCorreta;
    private final String[] outrasAlternativas;
    private final ArrayList<String> todasAlternativas;

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

    public String getEnunciado() {
        return enunciado;
    }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public String[] getOutrasAlternativas() {
        return outrasAlternativas;
    }

    public ArrayList<String> getTodasAlternativas() {
        return todasAlternativas;
    }

}