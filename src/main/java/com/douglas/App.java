package com.douglas;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();

        Text enunciado = new Text("Pergunta 1/4: Qual a capital de São Paulo?");
        root.getChildren().add(enunciado);
        
        Button alternativa1 = new Button("Floripa");
        root.getChildren().add(alternativa1);

        alternativa1.setOnAction(this::respondeQuestao1);

        Button alternativa2 = new Button("Rio de Janeiro");
        root.getChildren().add(alternativa2);

        alternativa2.setOnAction(this::respondeQuestao2);

        Button alternativa3 = new Button("Campo Grande");
        root.getChildren().add(alternativa3);

        alternativa3.setOnAction(this::respondeQuestao3);

        Button alternativa4 = new Button("São Paulo");
        root.getChildren().add(alternativa4);

        alternativa4.setOnAction(this::respondeQuestao4);

        Text enunciado2 = new Text("ACERTOU MUITO!");
        root.getChildren().add(enunciado2);

        Button alternativa5 = new Button("Próxima");
        root.getChildren().add(alternativa5);

        alternativa5.setOnAction(this::respondeQuestao);

        root.setStyle("-fx-background-color: purple");
        Scene scene = new Scene(root,600,800);
        stage.setScene(scene);
        stage.show();
    }

    private void respondeQuestao(Event event) {
        System.out.println("CLICOU EM PRÓXIMA");
    }

    private void respondeQuestao1(Event event) {
        System.out.println("CLICOU NO BOTÃO 1");
    }

    private void respondeQuestao2(Event event) {
        System.out.println("CLICOU NO BOTÃO 2");
    }

    private void respondeQuestao3(Event event) {
        System.out.println("CLICOU NO BOTÃO 3");
    }

    private void respondeQuestao4(Event event) {
        System.out.println("CLICOU NO BOTÃO 4");
    }

    public static void main(String[] args) {
        launch();
    }

}