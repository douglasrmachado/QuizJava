package com.douglas.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* AQUI QUE VAMO MANIPULAR OS DADOS DO APP */
public class ControladorQuiz {
    private ArrayList<Questao> questoes;
    private int questaoAtual;
    private int acertos;
    private int erros;

    /* AQUI ELE CONFIGURA A LISTA DE QUESTOES, REINICIA O ESTADO DO QUIZ E CARREGA AS QUESTOES DE OUTRO DOCUMENTO */
    public ControladorQuiz(ArrayList<Questao> questoes) {
        this.questoes = questoes != null ? questoes : new ArrayList<>();
        reiniciar();
        lerQuestoes();
    }
    
    /* AQUI É O MÉTODO REINICIAR PRA REINICIAR O QUIZ */
    public void reiniciar() {
        Collections.shuffle(this.questoes);
        setAcertos(0);
        setQuestaoAtual(0);
        setErros(0);
    }

    // PEGA QUANTAS QUESTOES TEM NO TOTAL
    public int getTotalQuestao() {
        return this.questoes.size();
    }

    // VERIFICA SE TEM QUESTAO DEPOIS DA QUESTAOATUAL
    public boolean temProximaQuestao() {
        return questaoAtual < getTotalQuestao() - 1;
    }

    // AQUI VERIFICA SE TEM PROXIMAQUESTAO, SE SIM VAI PRA ELA
    public int proximaQuestao() {
        if(temProximaQuestao()) {
            this.questaoAtual++;
        }
        return this.questaoAtual;
    }

    /* VERIFICA O INDICE DE QUAL QUESTAO O USUARIO ESTÁ RESPONDENDO NO MOMENTO */
    public Questao getQuestao() {

        /* ESSE IF FAZ GARANTIR Q QUESTAOATUAL NAO SEJA NEGATIVA E NEM MAIOR Q O NUMERO DE QUESTOES ARMAZENADAS */
        if (questaoAtual >= 0 && questaoAtual < this.questoes.size()) {
            return this.questoes.get(questaoAtual);
        } else {
            throw new IndexOutOfBoundsException("Nenhuma questão disponível ou índice fora do alcance.");
        }
    }

    /* AQUI Q VAMO LER AS QUESTOES DO ARQUIVO TXT E BOTAR NO APP */
    public void lerQuestoes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("questions/questoes.txt"))) {
            String linha;

            // CADA LINHA É LIDA ATÉ Q NAO TENHAM MAIS LINHAS
            while ((linha = reader.readLine()) != null) {
                String enunciado = linha.substring(linha.indexOf(":") + 2);
    
                // ARMAZENA LINHA CORRETA
                linha = reader.readLine();
                String correta = linha.substring(linha.indexOf(":") + 2);
    
                // ARRAYLIST PRA ARMAZENAR 4 ALT INCORRETAS
                ArrayList<String> outras = new ArrayList<>();
                reader.readLine(); 
                for (int i = 0; i < 4; i++) {
                    linha = reader.readLine();
                    if (linha != null && !linha.isEmpty()) {
                        outras.add(linha.substring(2));
                    }
                }
    
                // CRIA A QUESTÃO E ADICIONA NO CONTROLADOR
                String[] outrasArray = new String[outras.size()];
                outras.toArray(outrasArray);
                Questao questao = new Questao(enunciado, correta, outrasArray);
                adicionarQuestao(questao);
    
                // IGNORA A LINHA EM BRANCO ENTRE AS QUESTOES PRA PODER CONTINUAR
                reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /* AQUI PEGA O DADO DA RESPOSTA DO USUÁRIO E COLOCA EM ACERTO OU ERRO(TAMBÉM TEM UM PRINTLN PRA VER NO TERMINAL SE TÁ FUNFANDO) */
    public boolean respondeQuestao(String alternativa) {
        boolean resultado;

        /* SE A RESPOSTA TÁ CORRETA, ADICIONA 1 EM ACERTOS */
        if (getQuestao().getRespostaCorreta().equals(alternativa)) {
            setAcertos(getAcertos() + 1);
            resultado = true;
            System.out.println("Resposta correta. Acertos: " + getAcertos());

        /* SE A RESPOSTA É INCORRETA, ADICIONA 1 EM ERROS */
        } else {
            setErros(getErros() + 1);
            resultado = false;
            System.out.println("Resposta incorreta. Erros: " + getErros());
        }
        return resultado;
    }

    // RETORNA O NUMERO ATUAL DE ACERTOS
    public int getAcertos() {
        return this.acertos;
    }

    // DEFINE O NUMERO DE ACERTOS
    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    // RETORNA O NUMERO ATUAL DE ERROS
    public int getErros() {
        return this.erros;
    }

    // DEFINE O NUMERO DE ERROS
    public void setErros(int erros) {
        this.erros = erros;
    }

    // RETORNA O INDICE DA QUESTAOATUAL(em qual ela ta atualmente no app)
    public int getQuestaoAtual() {
        return this.questaoAtual;
    }

    // DEFINE O INDICE DA QUESTAOATUAL(em qual ela ta atualmente no app)
    public void setQuestaoAtual(int questaoAtual) {
        this.questaoAtual = questaoAtual;
    }

    // ADICIONA UMA NOVA QUESTAO A LISTA DE QUESTOES
    public void adicionarQuestao(Questao questao){
        questoes.add(questao);
    }
}