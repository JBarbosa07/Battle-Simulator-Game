package ui.gui;

import ui.BattleSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// NOTE: I referenced the TellerApp for the implementation of the Scanner code and file saving function in the ui

// The RPG battle simulator application
public class MenuGUI extends TemplateGUI {

    // EFFECTS: runs the application
    public MenuGUI(JPanel cardPanel, CardLayout cardLayout, BattleSimulator battleSim) {
        super(cardPanel, cardLayout, battleSim);
        initializeMenu();
        battleSim.loadAccounts();
    }

    private void initializeMenu() {
        JLabel title = new JLabel("Battle Simulator");
        title.setFont(new java.awt.Font("Arial", Font.ITALIC, 30));
        add(title);
        JLabel intro = new JLabel("Welcome to the Battle Simulator! Please choose an option below.");
        add(intro);
        initializeMenuInstructions();
        setVisible(true);
    }

    private void initializeMenuInstructions() {
        JButton create = new JButton("Create new character");
        create.setActionCommand("create");
        create.addActionListener(this);

        JButton manage = new JButton("Manage characters");
        manage.setActionCommand("manage");
        manage.addActionListener(this);

        JButton battle = new JButton("Battle");
        battle.setActionCommand("battle");
        battle.addActionListener(this);

        JButton save = new JButton("Save your data");
        save.setActionCommand("save");
        save.addActionListener(this);

        JButton quit = new JButton("Quit the game");
        quit.setActionCommand("quit");
        quit.addActionListener(this);

        add(create);
        add(manage);
        add(battle);
        add(save);
        add(quit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("create")) {
            cardLayout.show(cardPanel, "Create");
        }
        if (e.getActionCommand().equals("manage")) {
            cardLayout.show(cardPanel, "Manage");
        }
        if (e.getActionCommand().equals("battle")) {
            cardLayout.show(cardPanel, "Battle");
        }
        if (e.getActionCommand().equals("save")) {
            battleSim.saveList();
        }
        if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        }
    }
}
