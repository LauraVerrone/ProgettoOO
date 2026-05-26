package gui;

import controller.Controller;

import javax.swing.*;

public class Home {
    private JPanel mainPanel;
    private JButton button1;
    private static JFrame frameHome;
    private Controller controller;

    public static void main(String[] args) {
        frameHome = new JFrame("Home");
        frameHome.setContentPane(new Home().mainPanel);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.pack();
        frameHome.setVisible(true);
    }

    public Home() {
        controller = new Controller();

        button1.addActionListener(e -> {
            // codice quando premi il bottone
        });
    }
}