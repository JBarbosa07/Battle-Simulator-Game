package ui.gui.submenus;

import model.Character;
import model.CharacterList;
import model.exceptions.InvalidInputException;
import model.exceptions.StatLargerThanPoolException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// NOTE: I referenced the Java swing demo files for the implementation of my GUI

public class CreateMenuGUI extends MenuGUI {
    public CreateMenuGUI(JPanel cardPanel, CardLayout cardLayout, CharacterList list) {
        super(cardPanel, cardLayout, list);
    }

    public void initializeCreate() {
        JPanel createMenu = new JPanel();
        addToPanel(createMenu, "Create");
        setBoxLayout(createMenu);
        character = new Character();

        instantiateTitle(createMenu, "Create A Character");

        instantiateIntro(createMenu, "It's time to create a new character!");

        JButton start = new JButton("Let's start!");
        start.setActionCommand("start");
        start.addActionListener(this);
        start.setAlignmentX(CENTER_ALIGNMENT);
        createMenu.add(start);

        instantiateGoBack(createMenu, "return");
    }

    private void setName() {
        JPanel nameMenu = new JPanel();
        addToPanel(nameMenu, "Name");
        setBoxLayout(nameMenu);

        instantiateIntro(nameMenu, "Please enter the character's name.");

        instantiateTextField(nameMenu);

        instantiateSubmitButton(nameMenu, "name");

        instantiateErrorLabel(nameMenu);
    }

    private void setHP() {
        JPanel hpMenu = new JPanel();
        addToPanel(hpMenu, "HP");
        setBoxLayout(hpMenu);

        instantiateConfirmStatSetString(hpMenu, "Character's name is " + character.getName());

        instantiateIntro(hpMenu, "Please enter the character's HP value. Stat pool: " + character.getStatPool());

        instantiateTextField(hpMenu);

        instantiateSubmitButton(hpMenu, "hp");

        instantiateErrorLabel(hpMenu);
    }

    private void setATK() {
        JPanel atkMenu = new JPanel();
        addToPanel(atkMenu, "ATK");
        setBoxLayout(atkMenu);

        instantiateConfirmStatSetInt(atkMenu, "Character's HP is now ", character.getHP());

        instantiateIntro(atkMenu,"Please enter the character's ATK value. Stat pool: " + character.getStatPool());

        instantiateTextField(atkMenu);

        instantiateSubmitButton(atkMenu, "atk");

        instantiateErrorLabel(atkMenu);
    }

    private void setDEF() {
        JPanel defMenu = new JPanel();
        addToPanel(defMenu, "DEF");
        setBoxLayout(defMenu);

        instantiateConfirmStatSetInt(defMenu, "Character's ATK is now ", character.getATK());

        instantiateIntro(defMenu, "Please enter the character's DEF value. Stat pool: " + character.getStatPool());

        instantiateTextField(defMenu);

        instantiateSubmitButton(defMenu, "def");

        instantiateErrorLabel(defMenu);
    }

    private void setQuote() {
        JPanel quoteMenu = new JPanel();
        addToPanel(quoteMenu, "Quote");
        setBoxLayout(quoteMenu);

        instantiateConfirmStatSetInt(quoteMenu, "Character's DEF is now ", character.getDEF());

        instantiateIntro(quoteMenu,"Please enter the character's battle quote.");

        instantiateTextField(quoteMenu);

        instantiateSubmitButton(quoteMenu, "quote");

        instantiateErrorLabel(quoteMenu);
    }

    private void finishCharacter() {
        this.addCharacter(character);

        JPanel finishMenu = new JPanel();
        addToPanel(finishMenu, "Finish");
        setBoxLayout(finishMenu);

        instantiateConfirmStatSetString(finishMenu, "Character's quote is now " + character.getQuote() + ".");

        instantiateIntro(finishMenu, character.getName() + " was successfully created!");

        instantiateGoBack(finishMenu, "return");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        createCommands(e);
        returnCommands(e);
    }

    private void createCommands(ActionEvent e) {
        if (e.getActionCommand().equals("start")) {
            setName();
        }
        checkNameButton(e);
        checkHPButton(e);
        checkAtkButton(e);
        checkDefButton(e);
        if (e.getActionCommand().equals("quote")) {
            String inputString = textField.getText();
            if (inputString.equals("")) {
                errorLabel.setText("Please enter at least one character");
            } else {
                character.setQuote(inputString);
                finishCharacter();
            }
        }
    }

    private void checkDefButton(ActionEvent e) {
        if (e.getActionCommand().equals("def")) {
            try {
                int i = Integer.parseInt((textField.getText()));
                character.setDEF(i);
                setQuote();
            } catch (NumberFormatException nfe) {
                errorLabel.setText("Please enter an integer value");
            } catch (StatLargerThanPoolException statLargerThanPoolException) {
                errorLabel.setText("Inputted value exceeds the stat pool.");
            }
        }
    }

    private void checkAtkButton(ActionEvent e) {
        if (e.getActionCommand().equals("atk")) {
            try {
                int i = Integer.parseInt((textField.getText()));
                character.setATK(i);
                setDEF();
            } catch (NumberFormatException nfe) {
                errorLabel.setText("Please enter an integer value");
            } catch (StatLargerThanPoolException statLargerThanPoolException) {
                errorLabel.setText("Inputted value exceeds the stat pool.");
            }
        }
    }

    private void checkHPButton(ActionEvent e) {
        if (e.getActionCommand().equals("hp")) {
            try {
                int i = Integer.parseInt(textField.getText());
                character.setHP(i);
                setATK();
            } catch (NumberFormatException nfe) {
                errorLabel.setText("Please enter an integer value");
            } catch (InvalidInputException invalidInputException) {
                errorLabel.setText("HP cannot be zero");
            } catch (StatLargerThanPoolException statLargerThanPoolException) {
                errorLabel.setText("Inputted value exceeds the stat pool.");
            }
        }
    }

    private void checkNameButton(ActionEvent e) {
        if (e.getActionCommand().equals("name")) {
            String inputString = textField.getText();
            if (inputString.equals("")) {
                errorLabel.setText("Please enter at least one character");
            } else {
                character.setName(inputString);
                setHP();
            }
        }
    }
}
