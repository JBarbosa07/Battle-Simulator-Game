package ui.console;

import model.Character;
import model.CharacterList;
import model.exceptions.CharacterDoesntExistException;
import model.exceptions.InvalidInputException;
import model.exceptions.StatLargerThanPoolException;
import ui.console.submenus.CharacterCreator;
import ui.console.submenus.CharacterManager;
import ui.console.submenus.CombatSystem;
import ui.console.submenus.SubMenu;
import ui.exceptions.CharacterAlreadyExistsException;
import ui.exceptions.InputtedNonIntException;
import persistence.Reader;
import persistence.Writer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// NOTE: I referenced the TellerApp for the implementation of the Scanner code and file saving function in the ui

// The RPG battle simulator application
public class BattleSimulator {
    private static final String LIST_FILE = "./data/characterList.txt";
    private CharacterList list;
    private Scanner input;
    private String menuKey;
    private List<SubMenu> subMenus;
    private CharacterCreator creator;
    private CharacterManager manager;
    private CombatSystem combatSystem;
    private Writer writer;
    private Reader reader;
    private Boolean keepGoing;

    // EFFECTS: runs the application
    public BattleSimulator() {
        menuKey = "start";
        menuControl(menuKey);
    }

    // REQUIRES: menuKey is one of start, menu, manage, or battle
    // MODIFIES: this
    // EFFECTS: processes the player's input and brings up the appropriate screen/action. Instantiates the character
    // list and submenus when menuKey is "start"
    private void menuControl(String menuKey) {
        keepGoing = true;
        String command;
        input = new Scanner(System.in);

        if (menuKey.equals("start")) {
            loadList();
            setUpSubMenus();
            menuKey = "menu";
        }

        while (keepGoing) {
            generateDisplay(menuKey);

            command = getInputString();
            command = command.toLowerCase();

            processCommand(command, menuKey);
        }
        System.out.println("\nThanks for playing!");
        System.exit(0);
    }

    // EFFECTS: instantiates sub menus and establishes bidirectional relationships with BattleSimulator
    private void setUpSubMenus() {
        subMenus = new ArrayList<>();

        creator = new CharacterCreator(list, input);
        manager = new CharacterManager(list, input);
        combatSystem = new CombatSystem(list, input);

        addSubMenu(creator);
        addSubMenu(manager);
        addSubMenu(combatSystem);

        for (SubMenu s : subMenus) {
            s.setBattleSim(this);
        }
    }

    // EFFECTS: generates the screen display based on key
    private void generateDisplay(String menuKey) {
        if ("menu".equals(menuKey)) {
            displayMainMenu();
        } else if ("manage".equals(menuKey)) {
            displayManagerMenu();
        } else if ("battle".equals(menuKey)) {
            displayBattleMenu();
        }
    }

    // EFFECTS: displays main menu options to player
    private void displayMainMenu() {
        System.out.println("\nWelcome to the Battle Simulator! Please choose an option below.");
        System.out.println("\tc -> create new character");
        System.out.println("\tm -> manage characters");
        System.out.println("\tb -> battle");
        System.out.println("\ts -> save your data");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays character manager options to player
    private void displayManagerMenu() {
        System.out.println("\n" + list.printCharacters());
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tv -> view a character");
        System.out.println("\td -> delete a character");
        System.out.println("\tr -> return to previous menu");
    }

    // EFFECTS: displays character manager options to player
    private void displayBattleMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tpvp -> have two characters fight each other");
        System.out.println("\tpvb -> have a character fight a boss");
        System.out.println("\tr -> return to previous menu");
    }

    // EFFECTS: handles the player's input based on key
    private void processCommand(String command, String menuKey) {
        if ("menu".equals(menuKey)) {
            processMainCommand(command);
        } else if ("manage".equals(menuKey)) {
            processManageCommand(command);
        } else if ("battle".equals(menuKey)) {
            processBattleCommand(command);
        }
    }

    // EFFECTS: handles player inputs in the main menu
    private void processMainCommand(String command) {
        if ("c".equals(command)) {
            performCreate();
        } else if ("m".equals(command)) {
            menuControl("manage");
        } else if ("b".equals(command)) {
            menuControl("battle");
        } else if ("s".equals(command)) {
            saveList();
        } else if ("q".equals(command)) {
            keepGoing = false;
        } else {
            System.err.println("Selection not valid");
        }
    }

    // EFFECTS: handles player inputs in the manage menu
    private void processManageCommand(String command) {
        if ("v".equals(command)) {
            performView();
        } else if ("d".equals(command)) {
            performDelete();
        } else if ("r".equals(command)) {
            menuControl("menu");
        } else {
            System.err.println("Selection not valid");
        }
    }

    // EFFECTS: handles player inputs in the battle menu
    private void processBattleCommand(String command) {
        if ("pvp".equals(command)) {
            performPvP();
        } else if ("pvb".equals(command)) {
            performPvB();
        } else if ("r".equals(command)) {
            menuControl("menu");
        } else {
            System.err.println("Selection not valid");
        }
    }

    // EFFECTS: allows player to create a new character
    private void performCreate() {
        try {
            creator.createNewCharacter();
        } catch (CharacterAlreadyExistsException e) {
            System.err.println("That character already exists");
        } catch (InvalidInputException e) {
            System.err.println("The HP stat cannot be 0 or less");
        } catch (InputtedNonIntException e) {
            System.err.println("Must input an integer");
            input.next();
        } catch (StatLargerThanPoolException e) {
            System.err.println("The inputted value must not exceed stat pool");
        }
    }

    // EFFECTS: allows player to view characters
    private void performView() {
        try {
            manager.viewCharacter();
        } catch (CharacterDoesntExistException e) {
            System.err.println("That character does not exist");
        }
    }

    // EFFECTS: allows player to remove characters
    private void performDelete() {
        manager.deleteCharacter();
    }

    // EFFECTS: allows player to set up a PvP match
    private void performPvP() {
        try {
            combatSystem.characterVsCharacter();
        } catch (CharacterDoesntExistException e) {
            System.err.println("That character does not exist");
        }
    }

    // EFFECTS: allows player to set up a PvB match
    private void performPvB() {
        try {
            combatSystem.characterVsBoss();
        } catch (CharacterDoesntExistException e) {
            System.err.println("That character does not exist");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads character list from LIST_FILE, if that file exists;
    // otherwise initializes empty list
    private void loadList() {
        try {
            reader = new Reader();
            list = reader.readList(new File(LIST_FILE));
        } catch (IOException e) {
            list = new CharacterList();
        }
    }

    // EFFECTS: saves character list to LIST_FILE
    private void saveList() {
        try {
            writer = new Writer(new File(LIST_FILE));
            writer.write(list);
            writer.close();
            System.out.println("\nCharacters have been saved to " + LIST_FILE);
        } catch (FileNotFoundException e) {
            System.err.println("Unable to save characters to " + LIST_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // MODIFIES: this
    // EFFECTS: adds SubMenu to subMenu list
    public void addSubMenu(SubMenu subMenu) {
        this.subMenus.add(subMenu);
    }

    // MODIFIES: this, subMenus
    // EFFECTS: if character list doesn't contain c, add c to list then add it to sub menus
    public void addCharacter(Character c) {
        for (SubMenu s : subMenus) {
            if (!list.contains(c)) {
                list.addCharacter(c);
                s.addCharacter(c);
            }
        }
    }

    // MODIFIES: this, subMenus
    // EFFECTS: if character list contains c, remove c from list then remove it from sub menus
    public void removeCharacter(Character c) throws CharacterDoesntExistException {
        for (SubMenu s : subMenus) {
            if (list.contains(c)) {
                list.removeCharacter(c);
                s.removeCharacter(c);
            }
        }
    }

    public String getInputString() {
        input.useDelimiter("\n");
        return input.next();
    }
}
