package com.douglas;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private ControladorQuiz controladorQuiz;

    private VBox root;
    private Scene cena;
    private Text enunciado;
    private Button alternativa1;
    private Button alternativa2;
    private Button alternativa3;
    private Button alternativa4;
    private Button alternativa5;
    private Text resultado;
    private Button proxima;

    @Override
    public void init() throws Exception {
        super.init();

        ArrayList<Questao> lista = new ArrayList<>();

        lista.add(new Questao("Qual a cor favorita da prof?", "rosa",
                new String[] { "preto", "laranja", "roxo", "vermelho" }));
        lista.add(new Questao("Qual a cor favorita da prof 2?", "verde",
                new String[] { "preto", "laranja", "roxo", "vermelho" }));
        lista.add(new Questao("Qual a cor favorita da prof? 3", "azul",
                new String[] { "preto", "laranja", "roxo", "vermelho" }));
        lista.add(new Questao("Qual a cor favorita da prof? 4", "cinza",
                new String[] { "preto", "laranja", "roxo", "vermelho" }));
        lista.add(new Questao("Qual a cor favorita da prof? 5", "marrom",
                new String[] { "preto", "laranja", "roxo", "vermelho" }));
        

        controladorQuiz = new ControladorQuiz(lista);

    }

    @Override
    public void start(Stage stage) throws Exception {

        inicializaComponente();
        atualizaComponentes();

        cena = new Scene(root, 500, 300);

        stage.setScene(cena);
        stage.show();
        root.setStyle("-fx-background-color: CC00FF");
    }

    @SuppressWarnings("unchecked")
    public void inicializaComponente(){

        enunciado = new Text("Enunciado");
        alternativa1 = new Button("Questão 1");
        alternativa2 = new Button("Questão 2");
        alternativa3 = new Button("Questão 3");
        alternativa4 = new Button("Questão 4");
        alternativa5 = new Button("Questão 5");
        resultado = new Text("O resultado aparecerá aqui.");
        proxima = new Button("Próxima ->");

        root = new VBox();
        root.getChildren().add(enunciado);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10.0);

        root.getChildren().add(alternativa1);
        root.getChildren().add(alternativa2);
        root.getChildren().add(alternativa3);
        root.getChildren().add(alternativa4);
        root.getChildren().add(alternativa5);
        root.getChildren().add(resultado);
        root.getChildren().add(proxima);

        alternativa1.setOnAction(respondeQuestao());
        alternativa2.setOnAction(respondeQuestao());
        alternativa3.setOnAction(respondeQuestao());
        alternativa4.setOnAction(respondeQuestao());
        alternativa5.setOnAction(respondeQuestao());
        proxima.setOnAction(respondeQuestao());

    }

    public void atualizaComponentes(){

        Questao objQuestao = controladorQuiz.getQuestao();
        ArrayList<String> questoes = objQuestao.getTodasAlternativas();

        alternativa1.setText(questoes.get(0));
        alternativa2.setText(questoes.get(1));
        alternativa3.setText(questoes.get(2));
        alternativa4.setText(questoes.get(3));
        alternativa5.setText(questoes.get(4));


    }


    @SuppressWarnings("rawtypes")
    private EventHandler respondeQuestao(){
        return new EventHandler<Event>(){
            @Override
            public void handle(Event event){
                //Button clicado = (Button)event.getSource();
                Button clicado = (Button) event.getSource();
                String alternativa = clicado.getText();

                boolean result = controladorQuiz.respondeQuestao(alternativa);

                if(result){
                    resultado.setText("Acertou paizão!");
                }else {
                    resultado.setText("Errou paizão!");
                }
            }
        };
    }

    @SuppressWarnings("rawtypes")
    private EventHandler proximaQuestao(){
        return new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                // Tem próxima questão?
                if (controladorQuiz.temProximaQuestao()){
                    controladorQuiz.proximaQuestao();
                    atualizaComponentes();
                }
                // Se sim muda para a próxima e atualiza a tela
            }
        };
    }

    public static void main(String[] args) {
        launch(args);
    }

}