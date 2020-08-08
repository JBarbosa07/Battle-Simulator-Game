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
        JLabel intro = new JLabel("What would you like to do? " + battleSim.getList().printCharacters());
        add(intro);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("return")) {
            cardLayout.show(cardPanel, "Menu");
        }
    }
}
