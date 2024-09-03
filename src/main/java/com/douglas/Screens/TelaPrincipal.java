package com.douglas.Screens;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.douglas.App;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TelaPrincipal {
    
    /* AQUI SE CLICAR NO CADASTRAR PERGUNTA PUXA ESSA TELA */
    @FXML
    void abrirTelaCadastro(ActionEvent event){
        App.pushScreen("CADASTRO");
    }

    /* AQUI SE CLICAR NO INICIAR QUIZ E NÃO TIVER PERGUNTAS JÁ TOMA ERRO */
    @FXML
    void abrirTelaQuiz(Event event) {
        try {
            if (!verificaSeHaPerguntas()) {
                throw new Exception("Não há perguntas cadastradas.");
            }
            App.pushScreen("QUIZ");
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhuma pergunta cadastrada");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /* MÉTODO PRA VERIFICAR SE TEM OU NÃO PERGUNTAS CADASTRADAS */
    
    private boolean verificaSeHaPerguntas() {
        String caminhoArquivo = "./questions/questoes.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    return true; // SE ENCONTRAR UMA LINHA COM ALGUM DADO, TEM PERGUNTA
                }
            }
        } catch (IOException e) {
            // PEGA AS POSSÍVEIS EXCEÇÕES DE IO
            e.printStackTrace();
        }

        return false; // SE CHEGOU AQUI, NÃO TEM PERGUNTA CADASTRADA
    }
}
