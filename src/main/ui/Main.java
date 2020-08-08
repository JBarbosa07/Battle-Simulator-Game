package ui;

import ui.gui.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("BattleSimulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel cardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        BattleSimulator battleSim = new BattleSimulator();

        cardPanel.setLayout(cardLayout);

        TemplateGUI menu = new MenuGUI(cardPanel, cardLayout, battleSim);
        TemplateGUI create = new CreateGUI(cardPanel, cardLayout, battleSim);
        TemplateGUI manage = new ManageGUI(cardPanel, cardLayout, battleSim);
        TemplateGUI battle = new BattleGUI(cardPanel, cardLayout, battleSim);

        cardPanel.add(menu, "Menu");
        cardPanel.add(create, "Create");
        cardPanel.add(manage, "Manage");
        cardPanel.add(battle, "Battle");

        frame.add(cardPanel);

        cardLayout.show(cardPanel, "Menu");

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
