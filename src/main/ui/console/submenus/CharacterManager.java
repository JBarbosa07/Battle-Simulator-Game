package ui.console.submenus;

import model.CharacterList;
import model.exceptions.CharacterDoesntExistException;

import java.util.Scanner;

// Represents the BattleSimulator's character manager
public class CharacterManager extends SubMenu {

    public CharacterManager(CharacterList list, Scanner input) {
        super(list, input);
    }

    // MODIFIES: this
    // EFFECTS: retrieves character with inputted name and prints their stats
    public void viewCharacter() throws CharacterDoesntExistException {
        System.out.println("\nPlease enter the name of the character you would like to view.");
        String name = getInputString();

        getCharacter(name);
        System.out.println("\n" + character.printCharacter());
    }

    // MODIFIES: this
    // EFFECTS: retrieves character with inputted name and removes them from list
    public void deleteCharacter() {
        System.out.println(list.printCharacters());
        System.out.println("\nPlease enter the name of the character you would like to delete.");
        String name = getInputString();

        try {
            getCharacter(name);
            removeCharacter(character);
            System.out.println("\n" + name + " was successfully deleted.");
        } catch (CharacterDoesntExistException e) {
            System.err.println("That character does not exist.");
        }
    }

    private void getCharacter(String name) throws CharacterDoesntExistException {
        character = list.getCharacter(name);
    }
}
