package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class AggiungiAnimalePanel extends JPanel {
    private JPanel panel1;
    private JButton salva;
    private JLabel title;

    public AggiungiAnimalePanel(Controller controller){
        setLayout(new BorderLayout());

        add(title);
    }

}
