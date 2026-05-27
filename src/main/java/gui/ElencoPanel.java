package gui;

import controller.Controller;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class ElencoPanel extends JPanel {
    private Controller controller;
    private JTextArea riepilogoTextArea;
    private JButton aggiornaButton;
    private JButton indietroButton;
    private JPanel buttonsPanel;

    public ElencoPanel(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        riepilogoTextArea = new JTextArea();
        riepilogoTextArea.setEditable(false);
        add(new JScrollPane(riepilogoTextArea), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        aggiornaButton = new JButton("Aggiorna elenco");
        indietroButton = new JButton("Torna alla home");

        aggiornaButton.addActionListener(e -> aggiornaRiepilogo());
        indietroButton.addActionListener(e -> controller.goToHome());

        buttonsPanel.add(aggiornaButton);
        buttonsPanel.add(indietroButton);
        add(buttonsPanel, BorderLayout.SOUTH);

        riepilogoTextArea.setText(controller.getRiepilogo());
    }

    public void aggiornaRiepilogo() {
        riepilogoTextArea.setText(controller.getRiepilogo());
    }
}