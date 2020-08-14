package ui.gui.submenus;

import model.Boss;
import model.CharacterList;
import model.Combatant;
import model.exceptions.CharacterDoesntExistException;
import ui.exceptions.StalemateException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// NOTE: I referenced the Java swing demo files for the implementation of my GUI

public class BattleMenuGUI extends MenuGUI {
    private Combatant playerOne;
    private Combatant playerTwo;
    private int damage1;
    private int damage2;
    private boolean isPlayerOneTurn;

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

        instantiateIntro(pvpMenu, "Please select fighter 1");

        instantiateTextField(pvpMenu);

        instantiateSubmitButton(pvpMenu, "Fighter 1 PvP");

        instantiateGoBack(pvpMenu, "returnBattle");

        instantiateErrorLabel(pvpMenu);
    }

    private void characterVsCharacterSelectFighterTwo(Combatant p1) {
        JPanel pvp2Menu = new JPanel();
        addToPanel(pvp2Menu, "PvP2");
        setBoxLayout(pvp2Menu);

        instantiatePrintCharacters(pvp2Menu);

        instantiateIntro(pvp2Menu, p1.getName() + " was selected to be fighter 1");

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

    private void combatSimulation(Combatant p1, Combatant p2) throws StalemateException {
        isPlayerOneTurn = false;

        damage1 = p1.getATK() - p2.getDEF();
        damage2 = p2.getATK() - p1.getDEF();

        if ((damage1 <= 0) && (damage2 <= 0)) {
            throw new StalemateException();
        }

        JPanel firstCombat =  new JPanel();
        addToPanel(firstCombat, "firstCombat");
        setBoxLayout(firstCombat);

        JLabel startBattle = new JLabel("Let the battle begin!");
        firstCombat.add(startBattle);
        startBattle.setAlignmentX(CENTER_ALIGNMENT);

        instantiateBattleQuote(p1, firstCombat);

        p2.attackedBy(p1);

        damageMessage(p1, p2, damage1, firstCombat);

        instantiateHealthRemaining(p2, firstCombat);

        instantiateContinueButton(firstCombat);
    }

    private void keepFighting(Combatant p1, Combatant p2) {
        JPanel combatPanel =  new JPanel();
        addToPanel(combatPanel, "combatPanel");
        setBoxLayout(combatPanel);

        if (isPlayerOneTurn) {
            isPlayerOneTurn = false;

            instantiateBattleQuote(p1, combatPanel);

            p2.attackedBy(p1);

            damageMessage(p1, p2, damage1, combatPanel);

            instantiateHealthRemaining(p2, combatPanel);

        } else {
            isPlayerOneTurn = true;

            instantiateBattleQuote(p2, combatPanel);

            p1.attackedBy(p2);

            damageMessage(p2, p1, damage2, combatPanel);

            instantiateHealthRemaining(p1, combatPanel);
        }
        instantiateContinueButton(combatPanel);
    }

    private void damageMessage(Combatant attacker, Combatant defender, int damage, JPanel panel) {
        String messageString;
        if (damage > 0) {
            messageString = attacker.getName() + " attacked " + defender.getName() + " and did " + damage + " damage!";
            JLabel normalMessage = new JLabel(messageString);
            panel.add(normalMessage);
            normalMessage.setAlignmentX(CENTER_ALIGNMENT);
        } else {
            messageString = attacker.getName() + " attacked " + defender.getName() + " and did 0 damage!";
            JLabel altMessage = new JLabel(messageString);
            panel.add(altMessage);
            altMessage.setAlignmentX(CENTER_ALIGNMENT);
        }
    }

    private void battleResult(Combatant p1, Combatant p2) {
        JPanel resultPanel = new JPanel();
        addToPanel(resultPanel, "result");
        setBoxLayout(resultPanel);

        if (p1.isDead()) {
            instantiateResultMessage(p1, p2, resultPanel, " can no longer fight!  ");
        } else {
            instantiateResultMessage(p2, p1, resultPanel, " can no longer fight! ");
        }
        instantiateGoBack(resultPanel, "returnBattle");
    }

    private void stalemate() {
        JPanel stalemate = new JPanel();
        addToPanel(stalemate, "stalemate");
        setBoxLayout(stalemate);

        JLabel stalemateMessage = new JLabel("They were evenly matched! The battle was a tie!");
        stalemate.add(stalemateMessage);
        stalemateMessage.setAlignmentX(CENTER_ALIGNMENT);

        instantiateGoBack(stalemate, "returnBattle");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        battleCommands(e);
        returnCommands(e);
    }

    private void battleCommands(ActionEvent e) {
        if (e.getActionCommand().equals("pvp")) {
            characterVsCharacterSelectFighterOne();
        } else if (e.getActionCommand().equals("pvb")) {
            characterVsBossSelectFighterOne();
        } else if (e.getActionCommand().equals("Fighter 1 PvP")) {
            setPlayerOneAndSelectNext();
        } else if (e.getActionCommand().equals("Fighter 2 PvP")) {
            setPlayerTwoAndStartBattle();
        } else if (e.getActionCommand().equals("Fighter 1 PvB")) {
            setPlayerOneAndStartBossBattle();
        } else if (e.getActionCommand().equals("next")) {
            continueBattleOrEnd();
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

    // Helpers
    private void setPlayerOneAndSelectNext() {
        try {
            playerOne = list.getCharacter(textField.getText());
            characterVsCharacterSelectFighterTwo(playerOne);
        } catch (CharacterDoesntExistException characterDoesntExistException) {
            errorLabel.setText("That character does not exist");
        }
    }

    private void setPlayerTwoAndStartBattle() {
        try {
            playerTwo = list.getCharacter(textField.getText());
            combatSimulation(playerOne, playerTwo);
        } catch (CharacterDoesntExistException characterDoesntExistException) {
            errorLabel.setText("That character does not exist");
        } catch (StalemateException e) {
            stalemate();
        }
    }

    private void setPlayerOneAndStartBossBattle() {
        try {
            playerOne = list.getCharacter(textField.getText());
            playerTwo = new Boss();
            combatSimulation(playerOne, playerTwo);
        } catch (CharacterDoesntExistException characterDoesntExistException) {
            errorLabel.setText("That character does not exist");
        } catch (StalemateException stalemateException) {
            stalemate();
        }
    }

    private void continueBattleOrEnd() {
        if (playerOne.isDead() || playerTwo.isDead()) {
            battleResult(playerOne, playerTwo);
        } else {
            keepFighting(playerOne, playerTwo);
        }
    }

    private void instantiateBattleQuote(Combatant c, JPanel panel) {
        JLabel battleQuote;
        battleQuote = new JLabel(c.getName() + ": " + c.getQuote());
        panel.add(battleQuote);
        battleQuote.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void instantiateContinueButton(JPanel panel) {
        JButton continueButton;
        continueButton = new JButton("Next turn");
        continueButton.addActionListener(this);
        continueButton.setActionCommand("next");
        panel.add(continueButton);
        continueButton.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void instantiateHealthRemaining(Combatant c, JPanel combatPanel) {
        JLabel healthRemaining;
        healthRemaining = new JLabel(c.getName() + " has " + c.getHP() + " HP remaining!");
        combatPanel.add(healthRemaining);
        healthRemaining.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void instantiateResultMessage(Combatant p1, Combatant p2, JPanel resultPanel, String s) {
        JLabel result;
        result = new JLabel(p1.getName() + s + p2.getName() + " wins!");
        resultPanel.add(result);
        result.setAlignmentX(CENTER_ALIGNMENT);
    }
}
