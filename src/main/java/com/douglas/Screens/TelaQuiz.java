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

    /* CHAMA TELA QUIZ */
    public TelaQuiz() {
        controladorQuiz = new ControladorQuiz(null);
    }

    @FXML
    private void initialize() {
        atualizaComponentes();
    }

    /* ESPERA AÇÃO NA ALTERNATIVA */
    @FXML
    private void handleAlternativaAction(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String alternativa = clickedButton.getText();

        boolean acertou = controladorQuiz.respondeQuestao(alternativa);

        /* MOSTRA O RESULTADO EMBAIXO DOS BOTOES */
        if (acertou) {
            resultado.setText("Acertou!");
        } else {
            resultado.setText("Errou!");
        }

        desabilitarAlternativas();
        proxima.setVisible(true);
    }

    /* ESPERA AÇÃO NO BOTAO PROXIMA */
    @FXML
    private void handleProximaAction() {
        /* SE TIVER PROXIMA, VAI PRA PROXIMA*/
        if (controladorQuiz.temProximaQuestao()) {
            controladorQuiz.proximaQuestao();
            atualizaComponentes();
            /* SE NAO, FINALIZA */
        } else {
            atualizaComponentes(); // ATUALIZA ERROS E ACERTOS ANTES DE FINALIZAR
            int acertos = controladorQuiz.getAcertos();
            int erros = controladorQuiz.getErros();
            int totalQuestoes = controladorQuiz.getTotalQuestao();

            // LÓGICA PRA EMPATE VITORIA OU DERROTA
            if (acertos == erros) {
                resultado.setText("EMPATOU!");
            } else if (acertos > totalQuestoes / 2) {
                resultado.setText("VITÓRIA!");
            } else {
                resultado.setText("DERROTA!");
            }
            
            finalizarJogo();
        }
    }

    /* ESPERA AÇÃO DE REINICIAR */
    @FXML
    private void handleReiniciarAction() {
        controladorQuiz.reiniciar();
        atualizaComponentes();
    }

    /* ESPERA AÇÃO DE VOLTAR AO MENU */
    @FXML
    private void handleMenuAction() {
        App.popScreen();  // REMOVE A TELA ATUAL
        App.pushScreen("PRINCIPAL"); // PUXA TELA PRINCIPAL
    }

    /* ATUALIZA COMPONENTES  */
    public void atualizaComponentes() {
        Questao objQuestao = controladorQuiz.getQuestao();

        /* PEGA A LISTA DE QUESTOES */
        ArrayList<String> questoes = objQuestao.getTodasAlternativas();
        
        /* MOSTRA ENUNCIADO E ALTERNATIVAS */
        enunciado.setText(objQuestao.getEnunciado());
        alternativa1.setText(questoes.get(0));
        alternativa2.setText(questoes.get(1));
        alternativa3.setText(questoes.get(2));
        alternativa4.setText(questoes.get(3));
        alternativa5.setText(questoes.get(4));
    
        /* FICA DESATIVADO PRO USUARIO PODER CLICAR */
        alternativa1.setDisable(false);
        alternativa2.setDisable(false);
        alternativa3.setDisable(false);
        alternativa4.setDisable(false);
        alternativa5.setDisable(false);
    
        // DEPURAÇÃO PRA VER SE TÁ DANDO TUDO CERTO
        System.out.println("Atualizando acertos e erros:");
        System.out.println("Acertos: " + controladorQuiz.getAcertos());
        System.out.println("Erros: " + controladorQuiz.getErros());
        
        /* MUDA O RESULTADO DE ERROS E ACERTOS */
        acertos.setText("Acertos: " + controladorQuiz.getAcertos());
        erros.setText("Erros: " + controladorQuiz.getErros());
    
        /* BOTOES E ENUNCIADO */
        btReiniciar.setVisible(false);
        proxima.setVisible(false);
    
        /* ATIVA DEPOIS Q O USUARIO CLICAR */
        enunciado.setVisible(true);
        alternativa1.setVisible(true);
        alternativa2.setVisible(true);
        alternativa3.setVisible(true);
        alternativa4.setVisible(true);
        alternativa5.setVisible(true);
    }

    /* MÉTODO PRA DESABILITAR ALTERNATIVAS APÓS O CLICK */
    private void desabilitarAlternativas() {
        alternativa1.setDisable(true);
        alternativa2.setDisable(true);
        alternativa3.setDisable(true);
        alternativa4.setDisable(true);
        alternativa5.setDisable(true);
    }

    /* ESCONDE TUDO DPS QUE FINALIZA O JOGO, FICA APENAS ACERTOS, ERROS, RESULTADO, VOLTAR MENU E REINICIAR */
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