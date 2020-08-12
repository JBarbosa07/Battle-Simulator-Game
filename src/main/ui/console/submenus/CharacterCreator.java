package ui.console.submenus;

import model.CharacterList;
import model.Character;
import model.exceptions.InvalidInputException;
import model.exceptions.StatLargerThanPoolException;
import ui.exceptions.CharacterAlreadyExistsException;
import ui.exceptions.InputtedNonIntException;

import java.util.Scanner;

// Represents the BattleSimulator's character creator
public class CharacterCreator extends SubMenu {
    private Character character;

    public CharacterCreator(CharacterList list, Scanner input) {
        super(list, input);
    }

    // MODIFIES: this
    // EFFECTS: allows player to create a new character and add it to list
    public void createNewCharacter() throws InvalidInputException, StatLargerThanPoolException,
            CharacterAlreadyExistsException, InputtedNonIntException {
        this.character = new Character();
        enterName(character);
        enterHP(character);
        enterATK(character);
        enterDEF(character);
        enterQuote(character);
        addCharacter(character);
        System.out.println("\n" + character.getName() + " was successfully created!");
    }

    // MODIFIES: this
    // EFFECTS: prompts the player to set a name; if a character has already taken the name, throws
    // CharacterAlreadyExistsException
    private void enterName(Character c) throws CharacterAlreadyExistsException {
        System.out.println("\nLet's create a new character!");
        System.out.println("Please enter the character's name (no spaces allowed)");
        String name = input.next();

        if (list.isNameTaken(name)) {
            throw new CharacterAlreadyExistsException();
        }

        c.setName(name);
        System.out.println("\nCharacter's name is " + c.getName());
    }

    // MODIFIES: this
    // EFFECTS: prompts the player to set HP; if inputted value isn't an integer, throw InputtedNonIntException
    private void enterHP(Character c) throws InvalidInputException, StatLargerThanPoolException,
            InputtedNonIntException {
        System.out.println("\nPlease enter the character's HP value. Stat pool: " + c.getStatPool());

        checkInputForInt();

        int hp = input.nextInt();
        c.setHP(hp);
        System.out.println("\nCharacter's HP is now " + c.getHP());
    }

    // MODIFIES: this
    // EFFECTS: prompts the player to set ATK; if inputted value isn't an integer, throw InputtedNonIntException
    private void enterATK(Character c) throws StatLargerThanPoolException, InputtedNonIntException {
        System.out.println("\nPlease enter the character's ATK value. Stat pool: " + c.getStatPool());

        checkInputForInt();

        int atk = input.nextInt();
        c.setATK(atk);
        System.out.println("\nCharacter's ATK is now " + c.getATK());
    }

    // MODIFIES: this
    // EFFECTS: prompts the player to set DEF; if inputted value isn't an integer, throw InputtedNonIntException
    private void enterDEF(Character c) throws StatLargerThanPoolException, InputtedNonIntException {
        System.out.println("\nPlease enter the character's DEF value. Stat pool: " + c.getStatPool());

        checkInputForInt();

        int def = input.nextInt();
        c.setDEF(def);
        System.out.println("\nCharacter's DEF is now " + c.getDEF());
    }

    // MODIFIES: this
    // EFFECTS: prompts the player to set quote
    private void enterQuote(Character c) {
        System.out.println("\nPlease enter the character's battle quote.");
        input.nextLine();
        String quote = input.nextLine();
        c.setQuote(quote);
        System.out.println("\nCharacter's quote is now " + c.getQuote());
    }

    private void checkInputForInt() throws InputtedNonIntException {
        if (!input.hasNextInt()) {
            throw new InputtedNonIntException();
        }
    }

}
