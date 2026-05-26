package controller;


import gui.AdottarePanel;
import gui.AggiungiAnimalePanel;
import gui.HomePanel;
import gui.MainFrame;

public class Controller {

    private MainFrame frame;

    public Controller(MainFrame frame) {
        this.frame = frame;
    }

    public void start() {
        HomePanel homePanel = new HomePanel(this);
        AggiungiAnimalePanel aggiungiAnimalePanel = new AggiungiAnimalePanel(this);
        AdottarePanel adottarePanel = new AdottarePanel(this);



        frame.addScreen("home", homePanel);
        frame.addScreen("AggiungiAnimale", aggiungiAnimalePanel);
        frame.addScreen("adottarePanel", adottarePanel);

        frame.showScreen("home");
        frame.setVisible(true);
    }

    public void goToHome() {
        frame.showScreen("home");
    }

    public void goToAggiungiAnimale() {
        frame.showScreen("AggiungiAnimale");
    }

    public void goToAdottare() {
        frame.showScreen("adottarePanel");
    }

    public void createLibretto(String nome){
        System.out.println(nome);

    }

}