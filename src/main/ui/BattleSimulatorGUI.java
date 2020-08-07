package ui;

import model.Boss;
import model.Character;
import model.CharacterList;
import model.Combatant;
import model.exceptions.CharacterDoesntExistException;
import model.exceptions.InvalidInputException;
import model.exceptions.StatLargerThanPoolException;
import persistence.Reader;
import persistence.Writer;
import ui.exceptions.CharacterAlreadyExistsException;
import ui.exceptions.InputtedNonIntException;
import ui.exceptions.StalemateException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

// NOTE: I referenced the TellerApp for the implementation of the Scanner code and file saving function in the ui

// The RPG battle simulator application
public class BattleSimulatorGUI extends JPanel implements ActionListener {

    // EFFECTS: runs the application
    public BattleSimulatorGUI() {
        super(new GridLayout(4,1));
        initializeMenu();
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
        create.setMnemonic(KeyEvent.VK_C);
        create.setActionCommand("create");

        JButton manage = new JButton("Manage characters");
        manage.setMnemonic(KeyEvent.VK_M);
        manage.setActionCommand("manage");

        JButton battle = new JButton("Battle");
        //Use the default text position of CENTER, TRAILING (RIGHT).
        battle.setMnemonic(KeyEvent.VK_B);
        battle.setActionCommand("battle");

        JButton save = new JButton("Save your data");
        //Use the default text position of CENTER, TRAILING (RIGHT).
        save.setMnemonic(KeyEvent.VK_S);
        save.setActionCommand("save");

        JButton quit = new JButton("Quit the game");
        //Use the default text position of CENTER, TRAILING (RIGHT).
        quit.setMnemonic(KeyEvent.VK_Q);
        quit.setActionCommand("quit");

        add(create);
        add(manage);
        add(battle);
        add(save);
        add(quit);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
