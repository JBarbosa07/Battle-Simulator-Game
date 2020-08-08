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
        JButton pvp = new JButton("Have two characters fight each other");
        pvp.setActionCommand("pvp");
        pvp.addActionListener(this);

        JButton pvb = new JButton("Have a character fight a boss");
        pvb.setActionCommand("pvb");
        pvb.addActionListener(this);

        JButton goBack = new JButton("Return to previous menu");
        goBack.setActionCommand("return");
        goBack.addActionListener(this);

        add(pvp);
        add(pvb);
        add(goBack);
    }

    private void characterVsCharacter() {
        JPanel pvpMenu = new JPanel();
        cardPanel.add(pvpMenu, "PvP");
        cardLayout.show(cardPanel, "PvP");
        JLabel characters = new JLabel(battleSim.getList().printCharacters());
        pvpMenu.add(characters);
        JLabel intro = new JLabel("Please select fighter 1");
        pvpMenu.add(intro);
    }

    private void characterVsBoss() {
        JPanel pvbMenu = new JPanel();
        cardPanel.add(pvbMenu, "PvB");
        cardLayout.show(cardPanel, "PvB");
        JLabel characters = new JLabel(battleSim.getList().printCharacters());
        pvbMenu.add(characters);
        JLabel intro = new JLabel("Please select fighter 1.");
        pvbMenu.add(intro);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("pvp")) {
            characterVsCharacter();
        }
        if (e.getActionCommand().equals("pvb")) {
            characterVsBoss();
        }
        if (e.getActionCommand().equals("return")) {
            cardLayout.show(cardPanel, "Menu");
        }
    }
}
