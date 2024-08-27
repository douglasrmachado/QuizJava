package com.douglas.Models;

import java.util.ArrayList;
import java.util.Collections;

public class ControladorQuiz {

    private ArrayList<Questao> questoes;
    private int questaoAtual;
    private int acertos;
    private int erros;

    public ControladorQuiz(ArrayList<Questao> questoes) {
        this.questoes = questoes;
        reiniciar();
    }

    public void reiniciar() {
        Collections.shuffle(this.questoes);
        setAcertos(0);
        setQuestaoAtual(0);
        setErros(0);
    }

    public int getTotalQuestao() {
        return this.questoes.size() -1;
    }

    public boolean temProximaQuestao() {
        return questaoAtual < getTotalQuestao();
    }

    public int proximaQuestao() {
        this.questaoAtual += 1;
        return this.questaoAtual;
    }

    public Questao getQuestao() {
        return this.questoes.get(questaoAtual);
    }

    public boolean respondeQuestao(String alternativa) {
        if (getQuestao().getRespostaCorreta().equals(alternativa)) {
            setAcertos(getAcertos() + 1);
            return true;
        }
        setErros(getErros() + 1);
        return false;
    }

    public int getAcertos() {
        return this.acertos;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public int getErros() {
        return this.erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    public int getQuestaoAtual() {
        return this.questaoAtual;
    }

    public void setQuestaoAtual(int questaoAtual) {
        this.questaoAtual = questaoAtual;
    }
}