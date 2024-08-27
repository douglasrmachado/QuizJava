package com.douglas.Screens;

import com.douglas.Models.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.douglas.*;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class TelaCadastro {
    private VBox root;
    private Label lbEnunciado;
    private Label lbCorreta;
    private Label lbErrada1;
    private Label lbErrada2;
    private Label lbErrada3;
    private Label lbErrada4;
    private TextField tfEnunciado;
    private TextField tfCorreta;
    private TextField tfErrada1;
    private TextField tfErrada2;
    private TextField tfErrada3;
    private TextField tfErrada4;
    private Button btCadastrar;
    private Button btCancelar;
    private Label lbConfirmaCadastro;
    private ControladorQuiz controladorQuiz;


    public TelaCadastro(ControladorQuiz controladorQuiz){
        this.controladorQuiz = controladorQuiz;
        inicializaComponentes();
    }


    private void inicializaComponentes(){
        root = new VBox();

        lbEnunciado  = new Label("Enunciado da Questão");
        tfEnunciado = new TextField();
        lbCorreta = new Label("Resposta Correta");
        tfCorreta = new TextField();
        lbErrada1 = new Label("Resposta Incorreta 1");
        tfErrada1 = new TextField();
        lbErrada2 = new Label("Resposta Incorreta 2");
        tfErrada2 = new TextField();
        lbErrada3 = new Label("Resposta Incorreta 3");
        tfErrada3 = new TextField();
        lbErrada4 = new Label("Resposta Incorreta 4");
        tfErrada4 = new TextField();
        lbConfirmaCadastro = new Label("Cadastro Confirmado");
        btCadastrar = new Button("Cadastrar Questão");
        btCadastrar.setOnAction(this::cadastrarQuestao);
        btCancelar = new Button("Cancelar");
        btCancelar.setOnAction(this::retornarMenu);

        lbConfirmaCadastro.setVisible(false);

        root.getChildren().addAll(lbEnunciado, tfEnunciado, lbCorreta, tfCorreta,
                                  lbErrada1, tfErrada1, lbErrada2, tfErrada2, 
                                  lbErrada3, tfErrada3, lbErrada4, tfErrada4, lbConfirmaCadastro, btCadastrar, btCancelar);
        
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        

    }

    private void limpaTextField(){
        tfEnunciado.clear();
        tfCorreta.clear();
        tfErrada1.clear();
        tfErrada2.clear();
        tfErrada3.clear();
        tfErrada4.clear();

    }

    private void cadastrarQuestao(Event event){
        Questao questao;
        String enunciado = tfEnunciado.getText();
        String correta = tfCorreta.getText();
        String[] outras = {tfErrada1.getText(), tfErrada2.getText(), tfErrada3.getText(), tfErrada4.getText()};

        questao = new Questao(enunciado, correta, outras);

        controladorQuiz.adicionarQuestao(questao);

        lbConfirmaCadastro.setVisible(true);

        limpaTextField();

        salvarQuestaoNoArquivo(questao);

    }
    
    private void salvarQuestaoNoArquivo(Questao questao) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("questions/questoes.txt", true))) {
        writer.write("Enunciado: " + questao.getEnunciado() + "\n");
        writer.write("Correta: " + questao.getRespostaCorreta() + "\n");
        writer.write("Outras Alternativas:\n");
        for (String alternativa : questao.getOutrasAlternativas()) {
            writer.write("- " + alternativa + "\n");
        }
        writer.write("\n");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private void retornarMenu(Event event){
        App.popScreen();
    }

    public VBox getRoot() {
        return root;
    }
}