package ui.gui;

import ui.BattleSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BattleGUI extends TemplateGUI {

    public BattleGUI(JPanel cardPanel, CardLayout cardLayout, BattleSimulator battleSim) {
        super(cardPanel, cardLayout, battleSim);
        initialize();
    }

    private void initialize() {
        JLabel title = new JLabel("Battle");
        title.setFont(new Font("Arial", Font.ITALIC, 20));
        add(title);
        JLabel intro = new JLabel("What would you like to do?");
        add(intro);
        initializeInstructions();
        setVisible(true);
    }

    private void initializeInstructions() {
        JButton pvp = new JButton("Character VS Character");
        pvp.setActionCommand("pvp");
        pvp.addActionListener(this);

        JButton pvb = new JButton("Character VS Boss");
        pvb.setActionCommand("pvb");
        pvb.addActionListener(this);

        JButton goBack = new JButton("Return to previous menu");
        goBack.setActionCommand("return");
        goBack.addActionListener(this);

        add(pvp);
        add(pvb);
        add(goBack);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("return")) {
            cardLayout.show(cardPanel, "Menu");
        }
    }
}
