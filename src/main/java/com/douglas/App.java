package com.douglas;

import java.util.ArrayList;
import com.douglas.Models.ControladorQuiz;
import com.douglas.Models.Questao;
import com.douglas.Screens.*;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;


/* AQUI É O INÍCIO DE TUDO PELO Q EU ENTENDI, AQUI É A TELA QUE VAI ABRIR QUANDO RODAR O PROGRAMA */

public class App extends BaseAppNavigator{
    private ControladorQuiz controladorQuiz;

    @Override
    public void init() throws Exception {
        super.init();
        ArrayList<Questao> lista = new ArrayList<>();
        controladorQuiz = new ControladorQuiz(lista);

    }

    /* n sei oq é isso, acho q é a tela só */
    @Override
    public String getHome() {
        return "PRINCIPAL";
    }

    /* TITULO Q APARECE LÁ EM CIMA DA TELA DO APP */
    @Override
    public String getAppTitle() {
        return "Quiz App";
    }

    /* AQUI É PARA REGISTRAR AS TELAS QUE VÃO EXISTIR DENTRO DO APP E TAMBÉM JÁ FAZENDO A CONEXÃO DO ARQUIVO FXML */
    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o-> new TelaPrincipal()) );
        registraTela("QUIZ", new ScreenRegistryFXML(App.class, "telaquiz.fxml", o-> new TelaQuiz()));
        registraTela("CADASTRO", new ScreenRegistryFXML(App.class, "cadastro.fxml", o-> new TelaCadastro(controladorQuiz)));
    }
}