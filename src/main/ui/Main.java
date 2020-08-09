package ui;

import model.CharacterList;
import persistence.Reader;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    private static final String LIST_FILE = "./data/characterList.txt";

    private static void setUp() {
        CharacterList list;
        try {
            list = Reader.readList(new File(LIST_FILE));
        } catch (IOException e) {
            list = new CharacterList();
        }

        //Create and set up the window.
        JFrame frame = new JFrame("BattleSimulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel cardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();

        cardPanel.setLayout(cardLayout);

        JPanel menu = new BattleSimulatorGUI(cardPanel, cardLayout, list);

        cardPanel.add(menu, "Menu");

        frame.add(cardPanel);

        cardLayout.show(cardPanel, "Menu");

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setUp();
            }
        });
    }
}
