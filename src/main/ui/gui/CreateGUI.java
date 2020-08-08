package ui.gui;

import ui.BattleSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateGUI extends TemplateGUI {

    public CreateGUI(JPanel cardPanel, CardLayout cardLayout, BattleSimulator battleSim) {
        super(cardPanel, cardLayout, battleSim);
        initialize();
    }

    private void initialize() {
        JLabel title = new JLabel("Create a character");
        title.setFont(new java.awt.Font("Arial", Font.ITALIC, 20));
        add(title);
        JLabel intro = new JLabel("Let's create a new character! Please enter the character's name.");
        add(intro);
        initializeInstructions();
        setVisible(true);
    }

    private void initializeInstructions() {
        JButton create = new JButton("Test");
        create.setActionCommand("create");
        create.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
