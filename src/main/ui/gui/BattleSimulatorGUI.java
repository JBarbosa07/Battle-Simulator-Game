package ui.gui;

import model.CharacterList;
import persistence.Reader;
import ui.gui.submenus.MainMenuGUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// NOTE: I referenced the Java swing demo files for the implementation of my GUI

public class BattleSimulatorGUI {
    private static final String LIST_FILE = "./data/characterList.txt";
    private CharacterList list;

    public BattleSimulatorGUI() {
        setUp();
    }

    private void setUp() {
        loadList();

        JFrame frame = new JFrame("BattleSimulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel cardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();

        cardPanel.setLayout(cardLayout);

        JPanel menu = new MainMenuGUI(cardPanel, cardLayout, list);

        cardPanel.add(menu, "Menu");

        frame.add(cardPanel);

        cardLayout.show(cardPanel, "Menu");

        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads character list from LIST_FILE, if that file exists;
    // otherwise initializes empty list
    private void loadList() {
        try {
            list = Reader.readList(new File(LIST_FILE));
        } catch (IOException e) {
            list = new CharacterList();
        }
    }
}
