package gui;

import controller.Controller;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
            MainFrame frame = new MainFrame();
            Controller controller = new Controller(frame);

            controller.start();
    }
}

