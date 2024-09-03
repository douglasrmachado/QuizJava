package com.douglas.Screens;

import com.douglas.Models.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.douglas.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/* TELA CADASTRO (O NOME DIZ TUDO NÉ IRMÃO) */
public class TelaCadastro {
    @FXML
    private Pane root;
    @FXML
    private Label lbEnunciado;
    @FXML
    private Label lbCorreta;
    @FXML
    private Label lbErrada1;
    @FXML
    private Label lbErrada2;
    @FXML
    private Label lbErrada3;
    @FXML
    private Label lbErrada4;
    @FXML
    private TextField tfEnunciado;
    @FXML
    private TextField tfCorreta;
    @FXML
    private TextField tfErrada1;
    @FXML
    private TextField tfErrada2;
    @FXML
    private TextField tfErrada3;
    @FXML
    private TextField tfErrada4;
    @FXML
    private Button btCadastrar;
    @FXML
    private Button btCancelar;

    private ControladorQuiz controladorQuiz;

    

    public TelaCadastro(ControladorQuiz controladorQuiz){
        this.controladorQuiz = controladorQuiz;
    }

    @FXML
    private void limpaTextField(){
        tfEnunciado.clear();
        tfCorreta.clear();
        tfErrada1.clear();
        tfErrada2.clear();
        tfErrada3.clear();
        tfErrada4.clear();
    }

    @FXML
    private void cadastrarQuestao(Event event){
        String enunciado = tfEnunciado.getText();
        String correta = tfCorreta.getText();
        String[] outras = {tfErrada1.getText(), tfErrada2.getText(), tfErrada3.getText(), tfErrada4.getText()};
    
        Questao questao = new Questao(enunciado, correta, outras);
    
        controladorQuiz.adicionarQuestao(questao);
    
        limpaTextField();
    
        salvarQuestaoNoArquivo(questao);
    }

    @FXML
    private void retornarMenu(Event event){
        App.popScreen();
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
}