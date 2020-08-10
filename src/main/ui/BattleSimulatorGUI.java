package ui;

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
public class BattleSimulatorGUI extends JPanel implements ActionListener {
    private static final String LIST_FILE = "./data/characterList.txt";
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private CharacterList list;
    static JLabel intro;
    static JLabel confirmStatSet;
    static JTextField t;
    static JButton b;
    static JLabel l;
    private Character character;
    private Character playerOne;
    private static GridLayout gridLayout = new GridLayout(0, 1);

    // EFFECTS: runs the application
    public BattleSimulatorGUI(JPanel cardPanel, CardLayout cardLayout, CharacterList list) {
        this.setLayout(gridLayout);
        this.setPreferredSize(new Dimension(600, 400));
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.list = list;
        initializeMenu();
    }

    private void initializeMenu() {
        JLabel title = new JLabel("Battle Simulator", SwingConstants.CENTER);
        title.setFont(new java.awt.Font("Arial", Font.ITALIC, 30));
        add(title);
        JLabel intro = new JLabel("Welcome to the Battle Simulator! Please choose an option below.",
                SwingConstants.CENTER);
        add(intro);
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
        JLabel title = new JLabel("Create A Character");
        title.setFont(new java.awt.Font("Arial", Font.ITALIC, 30));
        createMenu.add(title);
        title.setAlignmentX(CENTER_ALIGNMENT);
        intro = new JLabel("It's time to create a new character!");
        createMenu.add(intro);
        intro.setAlignmentX(CENTER_ALIGNMENT);
        JButton start = new JButton("Let's start!");
        start.addActionListener(this);
        start.setAlignmentX(CENTER_ALIGNMENT);
        createMenu.add(start);
        JButton goBack = new JButton("Return to previous menu");
        goBack.setActionCommand("return");
        goBack.addActionListener(this);
        createMenu.add(goBack);
        goBack.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void setName() {
        JPanel nameMenu = new JPanel();
        addToPanel(nameMenu, "Name");
        setBoxLayout(nameMenu);

        intro = new JLabel("Please enter the character's name.");

        l = new JLabel("");

        b = new JButton("Submit Name");
        b.addActionListener(this);

        t = new JTextField(10);

        nameMenu.add(intro);
        nameMenu.add(t);
        nameMenu.add(b);
        nameMenu.add(l);
        intro.setAlignmentX(CENTER_ALIGNMENT);
        l.setAlignmentX(CENTER_ALIGNMENT);
        b.setAlignmentX(CENTER_ALIGNMENT);
        t.setAlignmentX(CENTER_ALIGNMENT);
        t.setMaximumSize(t.getPreferredSize());

    }

    private void addToPanel(JPanel nameMenu, String name) {
        cardPanel.add(nameMenu, name);
        cardLayout.show(cardPanel, name);
    }

    private void setBoxLayout(JPanel nameMenu) {
        nameMenu.setLayout(new BoxLayout(nameMenu, BoxLayout.Y_AXIS));
    }

    private void setHP() {
        JPanel hpMenu = new JPanel();
        addToPanel(hpMenu, "HP");
        setBoxLayout(hpMenu);
        confirmStatSet = new JLabel("Character's name is " + character.getName());

        intro = new JLabel("Please enter the character's HP value. Stat pool: " + character.getStatPool());
        hpMenu.add(confirmStatSet);
        hpMenu.add(intro);

        l = new JLabel("");

        b = new JButton("Submit HP");
        b.addActionListener(this);
        t = new JTextField(10);
        t.setMaximumSize(t.getPreferredSize());

        hpMenu.add(t);
        hpMenu.add(b);
        hpMenu.add(l);

        confirmStatSet.setAlignmentX(CENTER_ALIGNMENT);
        intro.setAlignmentX(CENTER_ALIGNMENT);
        l.setAlignmentX(CENTER_ALIGNMENT);
        b.setAlignmentX(CENTER_ALIGNMENT);
        t.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void setATK() {
        JPanel atkMenu = new JPanel();
        addToPanel(atkMenu, "ATK");
        setBoxLayout(atkMenu);
        confirmStatSet = new JLabel("Character's HP is now " + character.getHP());
        intro = new JLabel("Please enter the character's ATK value. Stat pool: " + character.getStatPool());
        atkMenu.add(confirmStatSet);
        atkMenu.add(intro);

        l = new JLabel("");
        b = new JButton("Submit ATK");
        b.addActionListener(this);
        t = new JTextField(10);
        t.setMaximumSize(t.getPreferredSize());

        atkMenu.add(t);
        atkMenu.add(b);
        atkMenu.add(l);
        confirmStatSet.setAlignmentX(CENTER_ALIGNMENT);
        intro.setAlignmentX(CENTER_ALIGNMENT);
        l.setAlignmentX(CENTER_ALIGNMENT);
        b.setAlignmentX(CENTER_ALIGNMENT);
        t.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void setDEF() {
        JPanel defMenu = new JPanel();
        addToPanel(defMenu, "DEF");
        setBoxLayout(defMenu);

        confirmStatSet = new JLabel("Character's ATK is now " + character.getATK());
        intro = new JLabel("Please enter the character's DEF value. Stat pool: " + character.getStatPool());
        defMenu.add(confirmStatSet);
        defMenu.add(intro);
        l = new JLabel("");
        b = new JButton("Submit DEF");
        b.addActionListener(this);
        t = new JTextField(10);
        t.setMaximumSize(t.getPreferredSize());

        defMenu.add(t);
        defMenu.add(b);
        defMenu.add(l);

        confirmStatSet.setAlignmentX(CENTER_ALIGNMENT);
        intro.setAlignmentX(CENTER_ALIGNMENT);
        l.setAlignmentX(CENTER_ALIGNMENT);
        b.setAlignmentX(CENTER_ALIGNMENT);
        t.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void setQuote() {
        JPanel quoteMenu = new JPanel();
        addToPanel(quoteMenu, "Quote");
        setBoxLayout(quoteMenu);

        confirmStatSet = new JLabel("Character's DEF is now " + character.getDEF());
        intro = new JLabel("Please enter the character's battle quote.");

        l = new JLabel("");
        b = new JButton("Submit Quote");
        b.addActionListener(this);
        t = new JTextField(10);
        t.setMaximumSize(t.getPreferredSize());

        quoteMenu.add(confirmStatSet);
        quoteMenu.add(intro);
        quoteMenu.add(t);
        quoteMenu.add(b);
        quoteMenu.add(l);

        confirmStatSet.setAlignmentX(CENTER_ALIGNMENT);
        intro.setAlignmentX(CENTER_ALIGNMENT);
        l.setAlignmentX(CENTER_ALIGNMENT);
        b.setAlignmentX(CENTER_ALIGNMENT);
        t.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void finishCharacter() {
        confirmStatSet = new JLabel("Character's quote is now " + character.getQuote() + ".");
        JPanel finishMenu = new JPanel();
        addToPanel(finishMenu, "Finish");
        setBoxLayout(finishMenu);
        intro = new JLabel(character.getName() + " was successfully created!");
        finishMenu.add(confirmStatSet);
        finishMenu.add(intro);

        JButton goBack = new JButton("Return to main menu");
        goBack.addActionListener(this);
        finishMenu.add(goBack);

        confirmStatSet.setAlignmentX(CENTER_ALIGNMENT);
        intro.setAlignmentX(CENTER_ALIGNMENT);
        goBack.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void initializeManage() {
        JPanel manageMenu = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 1);
        manageMenu.setLayout(gridLayout);
        addToPanel(manageMenu, "Manage");
        JLabel title = new JLabel("Manage Your Characters", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.ITALIC, 30));
        manageMenu.add(title);

        JLabel intro = new JLabel("What would you like to do?", SwingConstants.CENTER);
        manageMenu.add(intro);

        JLabel characters = new JLabel(list.printCharacters(), SwingConstants.CENTER);
        manageMenu.add(characters);

        JButton view = new JButton("View a character");
        view.setActionCommand("view");
        view.addActionListener(this);

        JButton delete = new JButton("Delete a character");
        delete.setActionCommand("delete");
        delete.addActionListener(this);

        JButton goBack = new JButton("Return to previous menu");
        goBack.setActionCommand("return");
        goBack.addActionListener(this);

        manageMenu.add(view);
        manageMenu.add(delete);
        manageMenu.add(goBack);
    }

    private void viewCharacter() {
        JPanel viewMenu = new JPanel();
        addToPanel(viewMenu, "View");
        setBoxLayout(viewMenu);
        JLabel characters = new JLabel(list.printCharacters());
        viewMenu.add(characters);
        intro = new JLabel("Please enter the name of the character you would like to view.");
        viewMenu.add(intro);

        l = new JLabel("");
        b = new JButton("Submit Character");
        b.setActionCommand("View character");
        b.addActionListener(this);
        t = new JTextField(10);
        t.setMaximumSize(t.getPreferredSize());

        JButton goBack = new JButton("Return to previous menu");
        goBack.setActionCommand("returnManage");
        goBack.addActionListener(this);

        addButtons(viewMenu, goBack);

        characters.setAlignmentX(CENTER_ALIGNMENT);
        intro.setAlignmentX(CENTER_ALIGNMENT);
        l.setAlignmentX(CENTER_ALIGNMENT);
        b.setAlignmentX(CENTER_ALIGNMENT);
        t.setAlignmentX(CENTER_ALIGNMENT);
        goBack.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void addButtons(JPanel viewMenu, JButton goBack) {
        viewMenu.add(t);
        viewMenu.add(b);
        viewMenu.add(goBack);
        viewMenu.add(l);
    }

    private void viewCharacterSuccess(Character character) {
        JPanel viewSuccessMenu = new JPanel();
        addToPanel(viewSuccessMenu, "View Success");
        setBoxLayout(viewSuccessMenu);
        intro = new JLabel(character.printCharacter());
        viewSuccessMenu.add(intro);

        JButton goBack = new JButton("Return to previous menu");
        goBack.setActionCommand("returnManage");
        goBack.addActionListener(this);
        viewSuccessMenu.add(goBack);

        intro.setAlignmentX(CENTER_ALIGNMENT);
        goBack.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void deleteCharacter() {
        JPanel deleteMenu = new JPanel();
        addToPanel(deleteMenu, "Delete");
        setBoxLayout(deleteMenu);
        JLabel characters = new JLabel(list.printCharacters());
        deleteMenu.add(characters);
        intro = new JLabel("Please enter the name of the character you would like to delete.");
        deleteMenu.add(intro);

        l = new JLabel("");
        b = new JButton("Submit Character");
        b.setActionCommand("Delete character");
        b.addActionListener(this);
        t = new JTextField(10);
        t.setMaximumSize(t.getPreferredSize());

        JButton goBack = new JButton("Return to previous menu");
        goBack.setActionCommand("returnManage");
        goBack.addActionListener(this);

        addButtons(deleteMenu, goBack);

        characters.setAlignmentX(CENTER_ALIGNMENT);
        intro.setAlignmentX(CENTER_ALIGNMENT);
        l.setAlignmentX(CENTER_ALIGNMENT);
        b.setAlignmentX(CENTER_ALIGNMENT);
        t.setAlignmentX(CENTER_ALIGNMENT);
        goBack.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void deleteCharacterSuccess(Character character) throws CharacterDoesntExistException {
        list.removeCharacter(character);
        JPanel deleteSuccessMenu = new JPanel();
        addToPanel(deleteSuccessMenu, "View Success");
        setBoxLayout(deleteSuccessMenu);

        intro = new JLabel(character.getName() + " was successfully deleted!");
        JButton goBack = new JButton("Return to previous menu");
        deleteSuccessMenu.add(intro);

        goBack.setActionCommand("returnManage");
        goBack.addActionListener(this);
        deleteSuccessMenu.add(goBack);

        intro.setAlignmentX(CENTER_ALIGNMENT);
        goBack.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void initializeBattle() {
        JPanel battleMenu = new JPanel();
        GridLayout gridLayout = new GridLayout(0, 1);
        battleMenu.setLayout(gridLayout);
        addToPanel(battleMenu, "Battle");
        JLabel title = new JLabel("Battle", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.ITALIC, 30));
        battleMenu.add(title);
        intro = new JLabel("What would you like to do?", SwingConstants.CENTER);
        battleMenu.add(intro);

        JButton pvp = new JButton("Have two characters fight each other");
        pvp.setActionCommand("pvp");
        pvp.addActionListener(this);

        JButton pvb = new JButton("Have a character fight a boss");
        pvb.setActionCommand("pvb");
        pvb.addActionListener(this);

        JButton goBack = new JButton("Return to previous menu");
        goBack.setActionCommand("return");
        goBack.addActionListener(this);

        battleMenu.add(pvp);
        battleMenu.add(pvb);
        battleMenu.add(goBack);
    }

    private void characterVsCharacterSelectFighterOne() {
        JPanel pvpMenu = new JPanel();
        addToPanel(pvpMenu, "PvP");
        setBoxLayout(pvpMenu);
        JLabel characters = new JLabel(list.printCharacters());
        pvpMenu.add(characters);
        intro = new JLabel("Please select fighter 1");
        pvpMenu.add(intro);

        l = new JLabel("");
        b = new JButton("Submit Character");
        b.setActionCommand("Fighter 1 PvP");
        b.addActionListener(this);
        t = new JTextField(10);
        t.setMaximumSize(t.getPreferredSize());

        JButton goBack = new JButton("Return to previous menu");
        goBack.setActionCommand("returnBattle");
        goBack.addActionListener(this);

        addButtons(pvpMenu, goBack);

        characters.setAlignmentX(CENTER_ALIGNMENT);
        intro.setAlignmentX(CENTER_ALIGNMENT);
        l.setAlignmentX(CENTER_ALIGNMENT);
        b.setAlignmentX(CENTER_ALIGNMENT);
        t.setAlignmentX(CENTER_ALIGNMENT);
        goBack.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void characterVsCharacterSelectFighterTwo() {
        JPanel pvp2Menu = new JPanel();
        addToPanel(pvp2Menu, "PvP2");
        setBoxLayout(pvp2Menu);
        JLabel characters = new JLabel(list.printCharacters());
        pvp2Menu.add(characters);
        intro = new JLabel("Please select fighter 2");
        pvp2Menu.add(intro);

        l = new JLabel("");
        b = new JButton("Submit Character");
        b.setActionCommand("Fighter 2 PvP");
        b.addActionListener(this);
        t = new JTextField(10);
        t.setMaximumSize(t.getPreferredSize());

        JButton goBack = new JButton("Return to previous menu");
        goBack.setActionCommand("returnBattle");
        goBack.addActionListener(this);

        addButtons(pvp2Menu, goBack);

        characters.setAlignmentX(CENTER_ALIGNMENT);
        intro.setAlignmentX(CENTER_ALIGNMENT);
        l.setAlignmentX(CENTER_ALIGNMENT);
        b.setAlignmentX(CENTER_ALIGNMENT);
        t.setAlignmentX(CENTER_ALIGNMENT);
        goBack.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void characterVsBossSelectFighterOne() {
        JPanel pvbMenu = new JPanel();
        addToPanel(pvbMenu, "PvB");
        setBoxLayout(pvbMenu);
        JLabel characters = new JLabel(list.printCharacters());
        pvbMenu.add(characters);
        intro = new JLabel("Please select fighter 1.");
        pvbMenu.add(intro);

        l = new JLabel("");
        b = new JButton("Submit Character");
        b.setActionCommand("Fighter 1 PvB");
        b.addActionListener(this);
        t = new JTextField(10);
        t.setMaximumSize(t.getPreferredSize());

        JButton goBack = new JButton("Return to previous menu");
        goBack.setActionCommand("returnBattle");
        goBack.addActionListener(this);

        addButtons(pvbMenu, goBack);

        characters.setAlignmentX(CENTER_ALIGNMENT);
        intro.setAlignmentX(CENTER_ALIGNMENT);
        l.setAlignmentX(CENTER_ALIGNMENT);
        b.setAlignmentX(CENTER_ALIGNMENT);
        t.setAlignmentX(CENTER_ALIGNMENT);
        goBack.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void combatSimulation(Combatant p1, Combatant p2) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        menuCommands(e);
        createCommands(s);
        manageCommands(e);
        battleCommands(e);
        returnCommands(e, s);
    }

    private void returnCommands(ActionEvent e, String s) {
        if (s.equals("Return to main menu")) {
            cardLayout.show(cardPanel, "Menu");
        }
        if (e.getActionCommand().equals("return")) {
            cardLayout.show(cardPanel, "Menu");
        }
        if (e.getActionCommand().equals("returnManage")) {
            initializeManage();
        }
        if (e.getActionCommand().equals("returnBattle")) {
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
                playerOne = list.getCharacter(t.getText());
                characterVsCharacterSelectFighterTwo();
            } catch (CharacterDoesntExistException characterDoesntExistException) {
                l.setText("That character does not exist");
            }
        }
        if (e.getActionCommand().equals("Fighter 2 PvP") || e.getActionCommand().equals("Fighter 1 PvB")) {
            try {
                Character p1 = playerOne;
                Character p2 = list.getCharacter(t.getText());
                combatSimulation(p1, p2);
            } catch (CharacterDoesntExistException characterDoesntExistException) {
                l.setText("That character does not exist");
            }
        }
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
                Character c = list.getCharacter(t.getText());
                viewCharacterSuccess(c);
            } catch (CharacterDoesntExistException characterDoesntExistException) {
                l.setText("That character does not exist");
            }
        }
        if (e.getActionCommand().equals("Delete character")) {
            try {
                Character c = list.getCharacter(t.getText());
                deleteCharacterSuccess(c);
            } catch (CharacterDoesntExistException characterDoesntExistException) {
                l.setText("That character does not exist");
            }
        }
    }

    private void createCommands(String s) {
        if (s.equals("Let's start!")) {
            setName();
        }
        checkNameButton(s);
        checkHPButton(s);
        checkAtkButton(s);
        checkDefButton(s);
        if (s.equals("Submit Quote")) {
            String inputString = t.getText();
            if (inputString.equals("")) {
                l.setText("Please enter at least one character");
            } else {
                character.setQuote(inputString);
                list.addCharacter(character);
                finishCharacter();
            }
        }
    }

    private void checkDefButton(String s) {
        if (s.equals(("Submit DEF"))) {
            try {
                int i = Integer.parseInt((t.getText()));
                character.setDEF(i);
                setQuote();
            } catch (NumberFormatException nfe) {
                l.setText("Please enter an integer value");
            } catch (StatLargerThanPoolException statLargerThanPoolException) {
                l.setText("Inputted value exceeds the stat pool.");
            }
        }
    }

    private void checkAtkButton(String s) {
        if (s.equals(("Submit ATK"))) {
            try {
                int i = Integer.parseInt((t.getText()));
                character.setATK(i);
                setDEF();
            } catch (NumberFormatException nfe) {
                l.setText("Please enter an integer value");
            } catch (StatLargerThanPoolException statLargerThanPoolException) {
                l.setText("Inputted value exceeds the stat pool.");
            }
        }
    }

    private void checkHPButton(String s) {
        if (s.equals("Submit HP")) {
            try {
                int i = Integer.parseInt(t.getText());
                character.setHP(i);
                setATK();
            } catch (NumberFormatException nfe) {
                l.setText("Please enter an integer value");
            } catch (InvalidInputException invalidInputException) {
                l.setText("HP cannot be zero");
            } catch (StatLargerThanPoolException statLargerThanPoolException) {
                l.setText("Inputted value exceeds the stat pool.");
            }
        }
    }

    private void checkNameButton(String s) {
        if (s.equals("Submit Name")) {
            String inputString = t.getText();
            if (inputString.equals("")) {
                l.setText("Please enter at least one character");
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
            System.out.println("Unable to save characters to " + LIST_FILE);
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
}
