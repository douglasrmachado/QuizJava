package com.douglas;

import java.util.ArrayList;

import com.douglas.Models.ControladorQuiz;
import com.douglas.Models.Questao;

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
    private Text acertos;
    private Text erros;
    private Button btReiniciar;

    @Override
    public void init() throws Exception {
        super.init();

        ArrayList<Questao> lista = new ArrayList<>();

        lista.add(new Questao("Qual a cor favorita da prof?", "pink",
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

        cena = new Scene(root, 1280, 720);

        cena.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        stage.setScene(cena);
        stage.show();
        root.getStyleClass().add("background");
        root.getStyleClass().add("enunciado");
        
    }

    @SuppressWarnings("unchecked")
    public void inicializaComponente(){

        enunciado = new Text("enunciado");
        alternativa1 = new Button("Questão 1");
        alternativa2 = new Button("Questão 2");
        alternativa3 = new Button("Questão 3");
        alternativa4 = new Button("Questão 4");
        alternativa5 = new Button("Questão 5");
        resultado = new Text("O resultado aparecerá aqui.");
        proxima = new Button("Próxima ->");
        acertos = new Text("Acertos:");
        erros = new Text("Erros:");
        btReiniciar = new Button("REINICIAR");


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
        root.getChildren().add(acertos);
        root.getChildren().add(erros);
        root.getChildren().add(btReiniciar);


        
        alternativa1.getStyleClass().add("botao");
        alternativa2.getStyleClass().add("botao");
        alternativa3.getStyleClass().add("botao");
        alternativa4.getStyleClass().add("botao");
        alternativa5.getStyleClass().add("botao");
        proxima.getStyleClass().add("botao");
        btReiniciar.getStyleClass().add("botao");

        alternativa1.setOnAction(respondeQuestao());
        alternativa2.setOnAction(respondeQuestao());
        alternativa3.setOnAction(respondeQuestao());
        alternativa4.setOnAction(respondeQuestao());
        alternativa5.setOnAction(respondeQuestao());
        proxima.setOnAction(proximaQuestao());
        btReiniciar.setOnAction(reiniciar());

    }

    public void atualizaComponentes(){


        Questao objQuestao = controladorQuiz.getQuestao();
        ArrayList<String> questoes = objQuestao.getTodasAlternativas();
        enunciado.setText(objQuestao.getEnunciado());
        alternativa1.setText(questoes.get(0));
        alternativa2.setText(questoes.get(1));
        alternativa3.setText(questoes.get(2));
        alternativa4.setText(questoes.get(3));
        alternativa5.setText(questoes.get(4));

        alternativa1.setVisible(true);
        alternativa2.setVisible(true);
        alternativa3.setVisible(true);
        alternativa4.setVisible(true);
        alternativa5.setVisible(true);

        acertos.setText("Acertos: " + String.valueOf(controladorQuiz.getAcertos()));
        erros.setText("Erros: " + String.valueOf(controladorQuiz.getErros()));

        btReiniciar.setVisible(false);
        proxima.setVisible(false);

    }

    public void acabouJogo() {
        enunciado.setVisible(false);
        alternativa1.setVisible(false);
        alternativa2.setVisible(false);
        alternativa3.setVisible(false);
        alternativa4.setVisible(false);
        alternativa5.setVisible(false);
        proxima.setVisible(false);
        btReiniciar.setVisible(true);
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
                resultado.setVisible(true);
                proxima.setVisible(true);

                alternativa1.setVisible(alternativa1 == clicado);
                alternativa2.setVisible(alternativa2 == clicado);
                alternativa3.setVisible(alternativa3 == clicado);
                alternativa4.setVisible(alternativa4 == clicado);
                alternativa5.setVisible(alternativa5 == clicado);
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
                } else {
                    if(controladorQuiz.getAcertos() > 3){
                        resultado.setText("VOCÊ GANHOU!");
                    } else {
                        resultado.setText("VOCÊ PERDEU!");
                    }
                    atualizaComponentes();
                    acabouJogo();
                }
                // Se sim muda para a próxima e atualiza a tela
            }
        };
    }
    @SuppressWarnings("rawtypes")
    private EventHandler reiniciar(){
        return new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                controladorQuiz.reiniciar();
                enunciado.setVisible(true);
                alternativa1.setVisible(true);
                alternativa2.setVisible(true);
                alternativa3.setVisible(true);
                alternativa4.setVisible(true);
                alternativa5.setVisible(true);
                resultado.setVisible(false);
                proxima.setVisible(false);
                btReiniciar.setVisible(false);
                atualizaComponentes();

            }
        };
    }


    public static void main(String[] args) {
        launch(args);
    }

}