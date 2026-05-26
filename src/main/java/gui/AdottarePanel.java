package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class AdottarePanel extends JPanel {

    private JButton adottaButton;

    public AdottarePanel(Controller controller){
        setLayout(new BorderLayout());

        adottaButton.addActionListener(e -> {
            controller.goToHome();
        });

        add(adottaButton);
    }
}
