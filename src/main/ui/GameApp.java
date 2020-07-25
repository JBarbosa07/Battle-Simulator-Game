package ui;

import model.Character;
import model.CharacterList;

import java.util.Scanner;

// The RPG battle simulator application
public class GameApp {
    private CharacterList list;
    private Scanner input;

    // EFFECTS: runs the application
    public GameApp() {
        runGame();
    }

    private void runGame() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);
        list = new CharacterList();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Thanks for playing!");

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            createCharacter();
        } else if (command.equals("m")) {
            manageCharacters();
        } else if (command.equals("b")) {
            battle();
        } else if (command.equals("v")) {
            viewCharacter();
        } else if (command.equals("e")) {
            editCharacter();
        } else if (command.equals("d")) {
            deleteCharacter();
        } else {
            System.err.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to the Battle Simulator! Please choose an option below.");
        System.out.println("\tc -> create new character");
        System.out.println("\tm -> manage characters");
        System.out.println("\tb -> battle");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: allows player to create a new character
    private void createCharacter() {
        Character character = new Character();
        input.nextLine();
        enterName(character);
        enterHP(character);
        enterATK(character);
        enterDEF(character);
        enterQuote(character);
        list.addCharacter(character);
        System.out.println(character.getName() + " was successfully created!");
    }

    // EFFECTS: prompts the player to set a name
    private void enterName(Character c) {
        System.out.println("Let's create a new character!");
        System.out.println("Please enter the character's name.");
        String name = input.nextLine();
        c.setName(name);
        System.out.println("Character's name is " + c.getName());
    }

    // EFFECTS: prompts the player to set HP
    private void enterHP(Character c) {
        System.out.println("Please enter the character's HP value. Stat pool: " + c.getStatPool());
        int hp = input.nextInt();
        c.setHP(hp);
        System.out.println("Character's HP is now " + c.getHP());
        System.out.println("Stat pool: " + c.getStatPool());
    }

    // EFFECTS: prompts the player to set ATK
    private void enterATK(Character c) {
        System.out.println("Please enter the character's ATK value. Stat pool: " + c.getStatPool());
        int atk = input.nextInt();
        c.setATK(atk);
        System.out.println("Character's ATK is now " + c.getATK());
        System.out.println("Stat pool: " + c.getStatPool());
    }

    // EFFECTS: prompts the player to set DEF
    private void enterDEF(Character c) {
        System.out.println("Please enter the character's DEF value. Stat pool: " + c.getStatPool());
        int def = input.nextInt();
        c.setDEF(def);
        System.out.println("Character's DEF is now " + c.getDEF());
        System.out.println("Stat pool: " + c.getStatPool());
    }

    // EFFECTS: prompts the player to set quote
    private void enterQuote(Character c) {
        System.out.println("Please enter the character's battle quote.");
        input.nextLine();
        String quote = input.nextLine();
        c.setQuote(quote);
        System.out.println("Character's quote is now " + c.getQuote());
    }




    // MODIFIES: this
    // EFFECTS: allows player to manage existing characters
    private void manageCharacters() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu2();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("b")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

    }

    // EFFECTS: displays character manager options to player
    private void displayMenu2() {
        System.out.println(list.printCharacters());
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tv -> view a character");
        System.out.println("\te -> edit a character");
        System.out.println("\td -> delete a character");
        System.out.println("\tb -> go back");
    }

    // EFFECTS: allows player to view their characters
    private void viewCharacter() {
        input.nextLine();
        System.out.println("Please enter the name of the character you would like to view.");
        String name = input.nextLine();
        Character character = list.getCharacter(name);
        System.out.println(character.printCharacter());
    }

    // EFFECTS: allows player to edit their characters
    private void editCharacter() {

    }

    // EFFECTS: allows player to delete their characters
    private void deleteCharacter() {
        System.out.println(list.printCharacters());
        System.out.println("Please enter the name of the character you would like to delete.");
        input.nextLine();
        String name = input.nextLine();
        list.removeCharacter(list.getCharacter(name));
        System.out.println(name + " was successfully deleted.");
        System.out.println(list.printCharacters());
    }

    // MODIFIES: this
    // EFFECTS: allows player to enter battle simulation
    private void battle(){

    }

}
