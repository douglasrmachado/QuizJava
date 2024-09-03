package com.douglas.Screens;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.ArrayList;

import com.douglas.App;
import com.douglas.Models.ControladorQuiz;
import com.douglas.Models.Questao;

public class TelaQuiz {

    @FXML
    private Text enunciado;

    @FXML
    private Button alternativa1;

    @FXML
    private Button alternativa2;

    @FXML
    private Button alternativa3;

    @FXML
    private Button alternativa4;

    @FXML
    private Button alternativa5;

    @FXML
    private Text resultado;

    @FXML
    private Button proxima;

    @FXML
    private Button btMenu;

    @FXML
    private Text acertos;

    @FXML
    private Text erros;

    @FXML
    private Button btReiniciar;

    private ControladorQuiz controladorQuiz;

    public TelaQuiz() {
        controladorQuiz = new ControladorQuiz(null); // Inicializa o controlador de quiz
    }

    @FXML
    private void initialize() {
        atualizaComponentes();
    }

    @FXML
    private void handleAlternativaAction(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String alternativa = clickedButton.getText();

        boolean acertou = controladorQuiz.respondeQuestao(alternativa);

        if (acertou) {
            resultado.setText("Acertou!");
        } else {
            resultado.setText("Errou!");
        }

        desabilitarAlternativas();
        proxima.setVisible(true);
    }

    @FXML
    private void handleProximaAction() {
        if (controladorQuiz.temProximaQuestao()) {
            controladorQuiz.proximaQuestao();
            atualizaComponentes();
        } else {
            System.out.println("Nenhuma proxima questao disponível!");
            resultado.setText(controladorQuiz.getAcertos() > controladorQuiz.getTotalQuestao() / 2 ? "Você ganhou!" : "Você perdeu!");
            finalizarJogo();
        }
    }

    @FXML
    private void handleReiniciarAction() {
        controladorQuiz.reiniciar();
        atualizaComponentes();
    }

    @FXML
    private void handleMenuAction() {
        // Voltar ao menu principal
        App.popScreen();  // Remove a tela atual da pilha
        App.pushScreen("PRINCIPAL"); // Volta para a tela principal
    }

    public void atualizaComponentes() {
        Questao objQuestao = controladorQuiz.getQuestao();
        ArrayList<String> questoes = objQuestao.getTodasAlternativas();
        
        enunciado.setText(objQuestao.getEnunciado());
        alternativa1.setText(questoes.get(0));
        alternativa2.setText(questoes.get(1));
        alternativa3.setText(questoes.get(2));
        alternativa4.setText(questoes.get(3));
        alternativa5.setText(questoes.get(4));
    
        alternativa1.setDisable(false);
        alternativa2.setDisable(false);
        alternativa3.setDisable(false);
        alternativa4.setDisable(false);
        alternativa5.setDisable(false);
    
        // Adicione depuração aqui
        System.out.println("Atualizando acertos e erros:");
        System.out.println("Acertos: " + controladorQuiz.getAcertos());
        System.out.println("Erros: " + controladorQuiz.getErros());
        
        acertos.setText("Acertos: " + controladorQuiz.getAcertos());
        erros.setText("Erros: " + controladorQuiz.getErros());
    
        btReiniciar.setVisible(false);
        proxima.setVisible(false);
    
        // Certifique-se de que todos os componentes relevantes estão visíveis
        enunciado.setVisible(true);
        alternativa1.setVisible(true);
        alternativa2.setVisible(true);
        alternativa3.setVisible(true);
        alternativa4.setVisible(true);
        alternativa5.setVisible(true);
    }

    private void desabilitarAlternativas() {
        alternativa1.setDisable(true);
        alternativa2.setDisable(true);
        alternativa3.setDisable(true);
        alternativa4.setDisable(true);
        alternativa5.setDisable(true);
    }

    private void finalizarJogo() {
        enunciado.setVisible(false);
        alternativa1.setVisible(false);
        alternativa2.setVisible(false);
        alternativa3.setVisible(false);
        alternativa4.setVisible(false);
        alternativa5.setVisible(false);
        proxima.setVisible(false);
        btReiniciar.setVisible(true);
    }
}