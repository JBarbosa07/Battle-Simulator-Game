package ui;

import model.Boss;
import model.Character;
import model.CharacterList;
import model.Combatant;
import model.exceptions.CharacterDoesntExistException;
import model.exceptions.InvalidInputException;
import model.exceptions.StatLargerThanPoolException;
import ui.exceptions.CharacterAlreadyExistsException;
import ui.exceptions.InputtedNonIntException;
import ui.exceptions.StalemateException;

import java.util.Scanner;

// NOTE: I referenced the TellerApp for the implementation of the Scanner code in the ui

// The RPG battle simulator application
public class BattleSimulator {
    private CharacterList list;
    private Scanner input;

    // EFFECTS: runs the application
    public BattleSimulator() {
        runGame();
    }

    private void runGame() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);
        list = new CharacterList();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandMain(command);
            }
        }

        System.out.println("Thanks for playing!");

    }

    // MODIFIES: this
    // EFFECTS: handles player inputs in the main menu
    private void processCommandMain(String command) {
        if (command.equals("c")) {
            try {
                createCharacter();
            } catch (CharacterAlreadyExistsException e) {
                System.err.println("That character already exists");
            } catch (InvalidInputException e) {
                System.err.println("The HP stat cannot be 0 or less");
            }  catch (InputtedNonIntException e) {
                System.err.println("Must input an integer");
                input.next();
            } catch (StatLargerThanPoolException e) {
                System.err.println("The inputted value must not exceed stat pool");
            }
        } else if (command.equals("m")) {
            manageCharacters();
        } else if (command.equals("b")) {
            battle();
        } else {
            System.err.println("Selection not valid");
        }
    }

    // MODIFIES: this
    // processes player inputs in the character manager menu
    private void processCommandManager(String command) {
        if (command.equals("v")) {
            try {
                viewCharacter();
            } catch (CharacterDoesntExistException e) {
                System.err.println("That character does not exist");
            }
        } else if (command.equals("d")) {
            deleteCharacter();
        } else {
            System.err.println("Selection not valid");
        }
    }

    // MODIFIES: this
    // handles player inputs in the battle menu
    private void processCommandBattle(String command) {
        if (command.equals("pvp")) {
            try {
                characterVsCharacter();
            } catch (CharacterDoesntExistException e) {
                System.err.println("That character does not exist");
            }
        } else if (command.equals("pvb")) {
            try {
                characterVsBoss();
            } catch (CharacterDoesntExistException e) {
                System.err.println("That character does not exist");
            }
        } else {
            System.err.println("Selection not valid");
        }
    }

    // EFFECTS: displays menu of options to player
    private void displayMenu() {
        System.out.println("\nWelcome to the Battle Simulator! Please choose an option below.");
        System.out.println("\tc -> create new character");
        System.out.println("\tm -> manage characters");
        System.out.println("\tb -> battle");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays character manager options to player
    private void displayMenu2() {
        System.out.println(list.printCharacters());
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tv -> view a character");
        System.out.println("\td -> delete a character");
        System.out.println("\tr -> return to previous menu");
    }

    // EFFECTS: displays character manager options to player
    private void displayMenu3() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tpvp -> have two characters fight each other");
        System.out.println("\tpvb -> have a character fight a boss");
        System.out.println("\tr -> return to previous menu");
    }

    // MODIFIES: this
    // EFFECTS: allows player to create a new character
    private void createCharacter() throws InvalidInputException, StatLargerThanPoolException,
            CharacterAlreadyExistsException, InputtedNonIntException {
        Character character = new Character();
        enterName(character);
        enterHP(character);
        enterATK(character);
        enterDEF(character);
        enterQuote(character);
        list.addCharacter(character);
        System.out.println(character.getName() + " was successfully created!");
    }

    // EFFECTS: prompts the player to set a name
    private void enterName(Character c) throws CharacterAlreadyExistsException {
        System.out.println("Let's create a new character!");
        System.out.println("Please enter the character's name.");
        String name = input.next();
        if (list.isNameTaken(name)) {
            throw new CharacterAlreadyExistsException();
        }
        c.setName(name);
        System.out.println("Character's name is " + c.getName());
    }

    // EFFECTS: prompts the player to set HP
    private void enterHP(Character c) throws InvalidInputException, StatLargerThanPoolException,
            InputtedNonIntException {
        System.out.println("Please enter the character's HP value. Stat pool: " + c.getStatPool());
        if (!input.hasNextInt()) {
            throw new InputtedNonIntException();
        }
        int hp = input.nextInt();
        c.setHP(hp);
        System.out.println("Character's HP is now " + c.getHP());
        System.out.println("Stat pool: " + c.getStatPool());
    }

    // EFFECTS: prompts the player to set ATK
    private void enterATK(Character c) throws StatLargerThanPoolException, InputtedNonIntException {
        System.out.println("Please enter the character's ATK value. Stat pool: " + c.getStatPool());
        if (!input.hasNextInt()) {
            throw new InputtedNonIntException();
        }
        int atk = input.nextInt();
        c.setATK(atk);
        System.out.println("Character's ATK is now " + c.getATK());
        System.out.println("Stat pool: " + c.getStatPool());
    }

    // EFFECTS: prompts the player to set DEF
    private void enterDEF(Character c) throws StatLargerThanPoolException, InputtedNonIntException {
        System.out.println("Please enter the character's DEF value. Stat pool: " + c.getStatPool());
        if (!input.hasNextInt()) {
            throw new InputtedNonIntException();
        }
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
        String command;

        while (keepGoing) {
            displayMenu2();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("r")) {
                keepGoing = false;
            } else {
                processCommandManager(command);
            }
        }
    }

    // EFFECTS: allows player to view their characters
    private void viewCharacter() throws CharacterDoesntExistException {
        System.out.println("Please enter the name of the character you would like to view.");
        String name = input.next();
        Character character = list.getCharacter(name);
        System.out.println(character.printCharacter());
    }

    // EFFECTS: allows player to delete their characters
    private void deleteCharacter() {
        System.out.println(list.printCharacters());
        System.out.println("Please enter the name of the character you would like to delete.");
        String name = input.next();
        try {
            list.removeCharacter(list.getCharacter(name));
            System.out.println(name + " was successfully deleted.");
        } catch (CharacterDoesntExistException e) {
            System.err.println("That character does not exist.");
        }
    }

    // MODIFIES: this
    // EFFECTS: allows player to enter battle simulation
    private void battle() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayMenu3();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("r")) {
                keepGoing = false;
            } else {
                processCommandBattle(command);
            }
        }
    }


    // EFFECTS: prompts player to select two characters and have them fight until one dies
    private void characterVsCharacter() throws CharacterDoesntExistException {
        System.out.println(list.printCharacters());
        System.out.println("Please select fighter 1");
        String name1 = input.next();
        Character p1 = list.getCharacter(name1);
        System.out.println(name1 + " was chosen to be fighter 1");

        System.out.println("Please select fighter 2");
        String name2 = input.next();
        Character p2 = list.getCharacter(name2);
        System.out.println(name2 + " was chosen to be fighter 2");
        try {
            combatSimulation(p1, p2);
        } catch (StalemateException e) {
            System.out.println("They were evenly matched! The battle was a tie!");
        }
    }

    // EFFECTS: prompts player to select a character to fight a boss until one dies
    private void characterVsBoss() throws CharacterDoesntExistException {
        Boss boss = new Boss();
        System.out.println(list.printCharacters());
        System.out.println("Please select your fighter");
        String name = input.next();
        Character c = list.getCharacter(name);
        System.out.println(name + " was chosen to fight " + boss.getName());
        try {
            combatSimulation(c, boss);
        } catch (StalemateException e) {
            System.out.println("They were evenly matched! The battle was a tie!");
        }
    }

    // EFFECTS: Represents a fight between the two selected combatants
    private void combatSimulation(Combatant p1, Combatant p2) throws StalemateException {
        int damage1 = p1.getATK() - p2.getDEF();
        int damage2 = p2.getATK() - p1.getDEF();

        if ((damage1 <= 0) && (damage2 <= 0)) {
            throw new StalemateException();
        }

        System.out.println("\nLet the battle begin!");
        while (!p1.isDead() && !p2.isDead()) {
            System.out.println(p1.getName() + ": " + p1.getQuote());
            p2.attackedBy(p1);
            damageMessage(p1, p2, damage1);
            System.out.println(p2.getName() + " has " + p2.getHP() + " HP remaining!");
            if (!p2.isDead()) {
                System.out.println(p2.getName() + ": " + p2.getQuote());
                p1.attackedBy(p2);
                damageMessage(p2, p1, damage2);
                System.out.println(p1.getName() + " has " + p1.getHP() + " HP remaining!");
            }
        }
        if (p1.isDead()) {
            System.out.println(p1.getName() + " can no longer fight!  " + p2.getName() + " wins!");
        } else {
            System.out.println(p2.getName() + " can no longer fight! " + p1.getName() + " wins!");
        }
    }

    // EFFECTS: if damage is > 0, prints ordinary damage number in message, else prints 0 damage dealt
    private void damageMessage(Combatant p1, Combatant p2, int damage) {
        if (damage > 0) {
            System.out.println(p1.getName() + " attacked " + p2.getName() + " and did " + damage + " damage!");
        } else {
            System.out.println(p1.getName() + " attacked " + p2.getName() + " and did 0 damage!");
        }
    }
}
