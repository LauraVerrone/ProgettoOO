package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        setTitle("Applicazione Swing");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        add(mainPanel);
    }

    public void addScreen(String name, JPanel panel) {
        mainPanel.add(panel, name);
    }

    public void showScreen(String name) {
        cardLayout.show(mainPanel, name);
    }
}
