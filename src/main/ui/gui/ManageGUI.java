package ui.gui;

import ui.BattleSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ManageGUI extends TemplateGUI {

    public ManageGUI(JPanel cardPanel, CardLayout cardLayout, BattleSimulator battleSim) {
        super(cardPanel, cardLayout, battleSim);
        initialize();
    }

    private void initialize() {
        JLabel title = new JLabel("Manage your characters");
        title.setFont(new Font("Arial", Font.ITALIC, 20));
        add(title);

        JLabel intro = new JLabel("What would you like to do?");
        add(intro);

        JLabel characters = new JLabel(battleSim.getList().printCharacters());
        add(characters);

        initializeInstructions();
        setVisible(true);
    }

    private void initializeInstructions() {
        JButton view = new JButton("View a character");
        view.setActionCommand("view");
        view.addActionListener(this);

        JButton delete = new JButton("Delete a character");
        delete.setActionCommand("delete");
        delete.addActionListener(this);

        JButton goBack = new JButton("Return to previous menu");
        goBack.setActionCommand("return");
        goBack.addActionListener(this);

        add(view);
        add(delete);
        add(goBack);
    }

    private void viewCharacter() {
        JPanel viewMenu = new JPanel();
        cardPanel.add(viewMenu, "View");
        cardLayout.show(cardPanel, "View");
        JLabel characters = new JLabel(battleSim.getList().printCharacters());
        viewMenu.add(characters);
        JLabel intro = new JLabel("Please enter the name of the character you would like to view.");
        viewMenu.add(intro);
    }

    private void deleteCharacter() {
        JPanel deleteMenu = new JPanel();
        cardPanel.add(deleteMenu, "Delete");
        cardLayout.show(cardPanel, "Delete");
        JLabel characters = new JLabel(battleSim.getList().printCharacters());
        deleteMenu.add(characters);
        JLabel intro = new JLabel("Please enter the name of the character you would like to delete.");
        deleteMenu.add(intro);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("view")) {
            viewCharacter();
        }
        if (e.getActionCommand().equals("delete")) {
            deleteCharacter();
        }
        if (e.getActionCommand().equals("return")) {
            cardLayout.show(cardPanel, "Menu");
        }
    }
}
