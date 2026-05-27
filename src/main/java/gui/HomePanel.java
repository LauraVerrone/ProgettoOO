package gui;

import controller.Controller;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class HomePanel extends JPanel {
    private JLabel titleLabel;
    private JButton aggiungiAnimaleButton;
    private JButton aggiungiAdottanteButton;
    private JButton adottareButton;
    private JButton elencoButton;

    public HomePanel(Controller controller) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        titleLabel = new JLabel("Rifugio Animali - GUI, Controller e Model", JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        aggiungiAnimaleButton = new JButton("Aggiungi animale con libretto sanitario");
        aggiungiAdottanteButton = new JButton("Registra adottante");
        adottareButton = new JButton("Crea richiesta di adozione");
        elencoButton = new JButton("Visualizza dati in memoria");

        aggiungiAnimaleButton.addActionListener(e -> controller.goToAggiungiAnimale());
        aggiungiAdottanteButton.addActionListener(e -> controller.goToAggiungiAdottante());
        adottareButton.addActionListener(e -> controller.goToAdottare());
        elencoButton.addActionListener(e -> controller.goToElenco());

        buttonsPanel.add(aggiungiAnimaleButton);
        buttonsPanel.add(aggiungiAdottanteButton);
        buttonsPanel.add(adottareButton);
        buttonsPanel.add(elencoButton);

        add(buttonsPanel, BorderLayout.CENTER);
    }
}