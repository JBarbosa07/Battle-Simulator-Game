package ui.gui.submenus;

import model.Character;
import model.CharacterList;
import model.Combatant;
import model.exceptions.CharacterDoesntExistException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// NOTE: I referenced the Java swing demo files for the implementation of my GUI

public class BattleMenuGUI extends MenuGUI {
    private Character playerOne;

    public BattleMenuGUI(JPanel cardPanel, CardLayout cardLayout, CharacterList list) {
        super(cardPanel, cardLayout, list);
    }

    public void initializeBattle() {
        JPanel battleMenu = new JPanel();
        addToPanel(battleMenu, "Battle");
        setGridLayout(battleMenu);

        instantiateTitle(battleMenu, "Battle");

        instantiateIntro(battleMenu, "What would you like to do?");

        JButton pvp = new JButton("Have two characters fight each other");
        pvp.setActionCommand("pvp");
        pvp.addActionListener(this);
        battleMenu.add(pvp);

        JButton pvb = new JButton("Have a character fight a boss");
        pvb.setActionCommand("pvb");
        pvb.addActionListener(this);
        battleMenu.add(pvb);

        instantiateGoBack(battleMenu, "return");
    }

    private void characterVsCharacterSelectFighterOne() {
        JPanel pvpMenu = new JPanel();
        addToPanel(pvpMenu, "PvP");
        setBoxLayout(pvpMenu);

        instantiatePrintCharacters(pvpMenu);

        instantiateIntro(pvpMenu,"Please select fighter 1");

        instantiateTextField(pvpMenu);

        instantiateSubmitButton(pvpMenu, "Fighter 1 PvP");

        instantiateGoBack(pvpMenu, "returnBattle");

        instantiateErrorLabel(pvpMenu);
    }

    private void characterVsCharacterSelectFighterTwo() {
        JPanel pvp2Menu = new JPanel();
        addToPanel(pvp2Menu, "PvP2");
        setBoxLayout(pvp2Menu);

        instantiatePrintCharacters(pvp2Menu);

        instantiateIntro(pvp2Menu, "Please select fighter 2");

        instantiateTextField(pvp2Menu);

        instantiateSubmitButton(pvp2Menu, "Fighter 2 PvP");

        instantiateGoBack(pvp2Menu, "returnBattle");

        instantiateErrorLabel(pvp2Menu);
    }

    private void characterVsBossSelectFighterOne() {
        JPanel pvbMenu = new JPanel();
        addToPanel(pvbMenu, "PvB");
        setBoxLayout(pvbMenu);

        instantiatePrintCharacters(pvbMenu);

        instantiateIntro(pvbMenu, "Please select fighter 1.");

        instantiateTextField(pvbMenu);

        instantiateSubmitButton(pvbMenu, "Fighter 1 PvB");

        instantiateGoBack(pvbMenu, "returnBattle");

        instantiateErrorLabel(pvbMenu);
    }

    private void combatSimulation(Combatant p1, Combatant p2) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        battleCommands(e);
        returnCommands(e);
    }

    private void battleCommands(ActionEvent e) {
        if (e.getActionCommand().equals("pvp")) {
            characterVsCharacterSelectFighterOne();
        }
        if (e.getActionCommand().equals("pvb")) {
            characterVsBossSelectFighterOne();
        }
        if (e.getActionCommand().equals("Fighter 1 PvP")) {
            try {
                playerOne = list.getCharacter(textField.getText());
                characterVsCharacterSelectFighterTwo();
            } catch (CharacterDoesntExistException characterDoesntExistException) {
                errorLabel.setText("That character does not exist");
            }
        }
        if (e.getActionCommand().equals("Fighter 2 PvP") || e.getActionCommand().equals("Fighter 1 PvB")) {
            try {
                Character p1 = playerOne;
                Character p2 = list.getCharacter(textField.getText());
                combatSimulation(p1, p2);
            } catch (CharacterDoesntExistException characterDoesntExistException) {
                errorLabel.setText("That character does not exist");
            }
        }
    }

    @Override
    public void returnCommands(ActionEvent e) {
        if (e.getActionCommand().equals("return")) {
            cardLayout.show(cardPanel, "Menu");
        } else if (e.getActionCommand().equals("returnBattle")) {
            cardLayout.show(cardPanel, "Battle");
        }
    }
}
