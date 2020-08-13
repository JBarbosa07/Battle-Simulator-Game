package ui.gui.submenus;

import model.Character;
import model.CharacterList;
import model.Combatant;
import model.exceptions.CharacterDoesntExistException;
import model.exceptions.InvalidInputException;
import model.exceptions.StatLargerThanPoolException;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

// NOTE: I referenced the Java swing demo files for the implementation of my GUI

// The RPG battle simulator application
public class MainMenuGUI extends JPanel implements ActionListener {
    private static final String LIST_FILE = "./data/characterList.txt";
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private CharacterList list;
    private JLabel title;
    private JLabel intro;
    private JLabel confirmStatSet;
    private JLabel characters;
    private JTextField textField;
    private JButton submitButton;
    private JLabel errorLabel;
    private JButton goBack;
    private Character character;
    private Character playerOne;
    private static GridLayout gridLayout = new GridLayout(0, 1);

    // EFFECTS: runs the application
    public MainMenuGUI(JPanel cardPanel, CardLayout cardLayout, CharacterList list) {
        this.setLayout(gridLayout);
        this.setPreferredSize(new Dimension(600, 400));
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.list = list;
        initializeMenu();
    }

    private void initializeMenu() {
        instantiateTitle(MainMenuGUI.this, "Battle Simulator");
        instantiateIntro(this, "Welcome to the Battle Simulator! Please choose an option below.");
        initializeMenuInstructions();
        setVisible(true);
    }

    private void initializeMenuInstructions() {
        JButton create = new JButton("Create new character");
        create.setActionCommand("create");
        create.addActionListener(this);

        JButton manage = new JButton("Manage characters");
        manage.setActionCommand("manage");
        manage.addActionListener(this);

        JButton battle = new JButton("Battle");
        battle.setActionCommand("battle");
        battle.addActionListener(this);

        JButton save = new JButton("Save your data");
        save.setActionCommand("save");
        save.addActionListener(this);

        JButton quit = new JButton("Quit the game");
        quit.setActionCommand("quit");
        quit.addActionListener(this);

        add(create);
        add(manage);
        add(battle);
        add(save);
        add(quit);
    }

    private void initializeCreate() {
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
        list.addCharacter(character);

        JPanel finishMenu = new JPanel();
        addToPanel(finishMenu, "Finish");
        setBoxLayout(finishMenu);

        instantiateConfirmStatSetString(finishMenu, "Character's quote is now " + character.getQuote() + ".");

        instantiateIntro(finishMenu, character.getName() + " was successfully created!");

        instantiateGoBack(finishMenu, "return");
    }

    private void initializeManage() {
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
        list.removeCharacter(character);
        JPanel deleteSuccessMenu = new JPanel();
        addToPanel(deleteSuccessMenu, "View Success");
        setBoxLayout(deleteSuccessMenu);

        instantiateIntro(deleteSuccessMenu,character.getName() + " was successfully deleted!");

        instantiateGoBack(deleteSuccessMenu, "returnManage");
    }

    private void initializeBattle() {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        menuCommands(e);
        createCommands(e);
        manageCommands(e);
        battleCommands(e);
        returnCommands(e);
    }

    private void returnCommands(ActionEvent e) {
        if (e.getActionCommand().equals("return")) {
            cardLayout.show(cardPanel, "Menu");
        } else if (e.getActionCommand().equals("returnManage")) {
            initializeManage();
        } else if (e.getActionCommand().equals("returnBattle")) {
            cardLayout.show(cardPanel, "Battle");
        }
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

    private void combatSimulation(Combatant p1, Combatant p2) {

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

    private void menuCommands(ActionEvent e) {
        if (e.getActionCommand().equals("create")) {
            initializeCreate();
        }
        if (e.getActionCommand().equals("manage")) {
            initializeManage();
        }
        if (e.getActionCommand().equals("battle")) {
            initializeBattle();
        }
        if (e.getActionCommand().equals("save")) {
            saveList();
            playSound("completed-ding-3.wav");
        }
        if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        }
    }

    // EFFECTS: saves character list to LIST_FILE
    public void saveList() {
        try {
            Writer writer = new Writer(new File(LIST_FILE));
            writer.write(list);
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to save characters to " + LIST_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // Code for playSound referenced from http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    // Helpers
    private void addToPanel(JPanel nameMenu, String name) {
        cardPanel.add(nameMenu, name);
        cardLayout.show(cardPanel, name);
    }

    private void setBoxLayout(JPanel nameMenu) {
        nameMenu.setLayout(new BoxLayout(nameMenu, BoxLayout.Y_AXIS));
    }

    private void setGridLayout(JPanel manageMenu) {
        GridLayout gridLayout = new GridLayout(0, 1);
        manageMenu.setLayout(gridLayout);
    }

    private void instantiateTitle(JPanel panel, String s) {
        title = new JLabel(s, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.ITALIC, 30));
        panel.add(title);
        title.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void instantiateIntro(JPanel panel, String s) {
        intro = new JLabel(s, SwingConstants.CENTER);
        panel.add(intro);
        intro.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void instantiateConfirmStatSetString(JPanel hpMenu, String s) {
        confirmStatSet = new JLabel(s);
        hpMenu.add(confirmStatSet);
        confirmStatSet.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void instantiateConfirmStatSetInt(JPanel panel, String s, int stat) {
        confirmStatSet = new JLabel(s + stat);
        panel.add(confirmStatSet);
        confirmStatSet.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void instantiatePrintCharacters(JPanel panel) {
        characters = new JLabel(list.printCharacters(), SwingConstants.CENTER);
        panel.add(characters);
        characters.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void instantiateTextField(JPanel panel) {
        textField = new JTextField(10);
        textField.setMaximumSize(textField.getPreferredSize());
        panel.add(textField);
        textField.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void instantiateSubmitButton(JPanel panel, String s) {
        submitButton = new JButton("Submit");
        submitButton.setActionCommand(s);
        submitButton.addActionListener(this);
        panel.add(submitButton);
        submitButton.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void instantiateErrorLabel(JPanel panel) {
        errorLabel = new JLabel("");
        panel.add(errorLabel);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void instantiateGoBack(JPanel panel, String returnString) {
        goBack = new JButton("Return to previous menu");
        goBack.setActionCommand(returnString);
        goBack.addActionListener(this);
        panel.add(goBack);
        goBack.setAlignmentX(CENTER_ALIGNMENT);
    }
}
