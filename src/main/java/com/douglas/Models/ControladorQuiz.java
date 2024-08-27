package com.douglas.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ControladorQuiz {
    private ArrayList<Questao> questoes;
    private int questaoAtual;
    private int acertos;
    private int erros;

    public ControladorQuiz(ArrayList<Questao> questoes) {
        this.questoes = questoes != null ? questoes : new ArrayList<>();
        lerQuestoes(); 
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
        if (questaoAtual >= 0 && questaoAtual < this.questoes.size()) {
            return this.questoes.get(questaoAtual);
        } else {
            throw new IndexOutOfBoundsException("Nenhuma questão disponível ou índice fora do alcance.");
        }
    }

    public void lerQuestoes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("questions/questoes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                // Ler e processar o enunciado
                String enunciado = linha.substring(linha.indexOf(":") + 2);
    
                // Ler e processar a resposta correta
                linha = reader.readLine();
                String correta = linha.substring(linha.indexOf(":") + 2);
    
                // Ler e processar as outras alternativas
                ArrayList<String> outras = new ArrayList<>();
                reader.readLine(); // Ignorar a linha "Outras Alternativas:"
                for (int i = 0; i < 4; i++) {
                    linha = reader.readLine();
                    if (linha != null && !linha.isEmpty()) {
                        outras.add(linha.substring(2));
                    }
                }
    
                // Criar a questão e adicionar ao controlador
                String[] outrasArray = new String[outras.size()];
                outras.toArray(outrasArray);
                Questao questao = new Questao(enunciado, correta, outrasArray);
                adicionarQuestao(questao);
    
                // Ignorar a linha em branco entre as questões
                reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    // Metodos do CRUD
    public void adicionarQuestao(Questao questao){
        questoes.add(questao);
    }
}