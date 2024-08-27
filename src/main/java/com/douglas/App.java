package com.douglas;

import java.util.ArrayList;
import com.douglas.Models.ControladorQuiz;
import com.douglas.Models.Questao;
import com.douglas.Screens.*;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryNoFXML;

public class App extends BaseAppNavigator{
    private ControladorQuiz controladorQuiz;

    @Override
    public void init() throws Exception {
        super.init();
        ArrayList<Questao> lista = new ArrayList<>();
        // APAGAR ISSO AQUI EM
        lista.add(new Questao("Qual a capital do Paraná?", "Curitiba", new String[]{"Floripa", "Porto Alegre", "São Paulo", "pau"}));
        controladorQuiz = new ControladorQuiz(lista);
    }

    @Override
    public String getHome() {
        return "PRINCIPAL";
    }

    @Override
    public String getAppTitle() {
        return "Quiz App";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryNoFXML(o-> new TelaPrincipal().getRoot()) );
        registraTela("QUIZ", new ScreenRegistryNoFXML(o-> new TelaQuiz(controladorQuiz).getRoot()));
        registraTela("CADASTRO", new ScreenRegistryNoFXML(o-> new TelaCadastro(controladorQuiz).getRoot()));
    }
}