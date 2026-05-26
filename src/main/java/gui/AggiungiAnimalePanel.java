package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class AggiungiAnimalePanel extends JPanel {
    private JPanel panel1;
    private JTextField textField1;
    private JButton inviaNomeButton;

    public AggiungiAnimalePanel(Controller controller){
        setLayout(new BorderLayout());

        inviaNomeButton.addActionListener(e ->{
            controller.createLibretto(textField1.getText());
        });

        add(textField1, BorderLayout.NORTH);
        add(inviaNomeButton, BorderLayout.SOUTH);
        add(panel1, BorderLayout.CENTER);

    }

}
