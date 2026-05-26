package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    private JButton aggiungiAnimaleButton;

    public HomePanel(Controller controller) {
        setLayout(new BorderLayout());


        aggiungiAnimaleButton.addActionListener(e -> {
            controller.goToAggiungiAnimale();
        });

        add(aggiungiAnimaleButton, BorderLayout.CENTER);

    }
}
