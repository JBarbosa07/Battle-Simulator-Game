package ui.gui.submenus;

import model.Character;
import model.CharacterList;
import model.exceptions.CharacterDoesntExistException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// NOTE: I referenced the Java swing demo files for the implementation of my GUI

public class ManageMenuGUI extends MenuGUI {
    public ManageMenuGUI(JPanel cardPanel, CardLayout cardLayout, CharacterList list) {
        super(cardPanel, cardLayout, list);
    }

    public void initializeManage() {
        JPanel manageMenu = new JPanel();
        setGridLayout(manageMenu);
        addToPanel(manageMenu, "Manage");

        instantiateTitle(manageMenu, "Manage Your Characters");

        instantiatePrintCharacters(manageMenu);

        instantiateIntro(manageMenu, "What would you like to do?");

        JButton view = new JButton("View a character");
        view.setActionCommand("view");
        view.addActionListener(this);
        manageMenu.add(view);

        JButton delete = new JButton("Delete a character");
        delete.setActionCommand("delete");
        delete.addActionListener(this);
        manageMenu.add(delete);

        instantiateGoBack(manageMenu, "return");
    }

    private void viewCharacter() {
        JPanel viewMenu = new JPanel();
        addToPanel(viewMenu, "View");
        setBoxLayout(viewMenu);

        instantiatePrintCharacters(viewMenu);

        instantiateIntro(viewMenu, "Please enter the name of the character you would like to view.");

        instantiateTextField(viewMenu);

        instantiateSubmitButton(viewMenu, "View character");

        instantiateGoBack(viewMenu, "returnManage");

        instantiateErrorLabel(viewMenu);
    }

    private void viewCharacterSuccess(Character character) {
        JPanel viewSuccessMenu = new JPanel();
        addToPanel(viewSuccessMenu, "View Success");
        setBoxLayout(viewSuccessMenu);

        instantiateIntro(viewSuccessMenu, character.printCharacter());

        instantiateGoBack(viewSuccessMenu, "returnManage");
    }

    private void deleteCharacter() {
        JPanel deleteMenu = new JPanel();
        addToPanel(deleteMenu, "Delete");
        setBoxLayout(deleteMenu);

        instantiatePrintCharacters(deleteMenu);

        instantiateIntro(deleteMenu,"Please enter the name of the character you would like to delete.");

        instantiateTextField(deleteMenu);

        instantiateSubmitButton(deleteMenu, "Delete character");

        instantiateGoBack(deleteMenu, "returnManage");

        instantiateErrorLabel(deleteMenu);
    }

    private void deleteCharacterSuccess(Character character) throws CharacterDoesntExistException {
        this.removeCharacter(character);
        JPanel deleteSuccessMenu = new JPanel();
        addToPanel(deleteSuccessMenu, "View Success");
        setBoxLayout(deleteSuccessMenu);

        instantiateIntro(deleteSuccessMenu,character.getName() + " was successfully deleted!");

        instantiateGoBack(deleteSuccessMenu, "returnManage");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        manageCommands(e);
        returnCommands(e);
    }

    private void manageCommands(ActionEvent e) {
        if (e.getActionCommand().equals("view")) {
            viewCharacter();
        }
        if (e.getActionCommand().equals("delete")) {
            deleteCharacter();
        }
        if (e.getActionCommand().equals("View character")) {
            try {
                Character c = list.getCharacter(textField.getText());
                viewCharacterSuccess(c);
            } catch (CharacterDoesntExistException characterDoesntExistException) {
                errorLabel.setText("That character does not exist");
            }
        }
        if (e.getActionCommand().equals("Delete character")) {
            try {
                Character c = list.getCharacter(textField.getText());
                deleteCharacterSuccess(c);
            } catch (CharacterDoesntExistException characterDoesntExistException) {
                errorLabel.setText("That character does not exist");
            }
        }
    }

    @Override
    public void returnCommands(ActionEvent e) {
        if (e.getActionCommand().equals("return")) {
            cardLayout.show(cardPanel, "Menu");
        } else if (e.getActionCommand().equals("returnManage")) {
            initializeManage();
        }
    }
}
