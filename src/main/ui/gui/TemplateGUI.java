package ui.gui;

import ui.BattleSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class TemplateGUI extends JPanel implements ActionListener {
    protected JPanel cardPanel;
    protected CardLayout cardLayout;
    protected BattleSimulator battleSim;

    public TemplateGUI(JPanel cardPanel, CardLayout cardLayout, BattleSimulator battleSim) {
        GridLayout gridLayout = new GridLayout(0,1);
        this.setLayout(gridLayout);
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.battleSim = battleSim;
    }
}
