package com.douglas.Screens;

import com.douglas.App;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class TelaPrincipal {
    private VBox root;
    private Button btQuizz;
    private Button btCadastro;

    public TelaPrincipal(){
        inicializaComponentes();
    }

    private void inicializaComponentes(){
        root = new VBox();
        btQuizz = new Button("Iniciar Quizz");
        btQuizz.setOnAction(this::abrirTelaQuiz);
        btCadastro = new Button("Cadastrar Perguntas");
        btCadastro.setOnAction(this::abrirTelaCadastro);

        root.getChildren().addAll(btQuizz, btCadastro);
        root.setPrefSize(400, 600);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
    }

    private void abrirTelaQuiz(Event event){
        App.pushScreen("QUIZ");
    }

    private void abrirTelaCadastro(Event event) {
        App.pushScreen("CADASTRO");
    }

    public VBox getRoot(){
        return root;
    }

}
