package ui.gui.submenus;

import model.Character;
import model.CharacterList;
import model.exceptions.CharacterDoesntExistException;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

// NOTE: I referenced the Java swing demo files for the implementation of my GUI

public class MainMenuGUI extends MenuGUI {
    private static final String LIST_FILE = "./data/characterList.txt";

    private ArrayList<MenuGUI> menus;
    private CreateMenuGUI createMenu;
    private ManageMenuGUI manageMenu;
    private BattleMenuGUI battleMenu;

    public MainMenuGUI(JPanel cardPanel, CardLayout cardLayout, CharacterList list) {
        super(cardPanel, cardLayout, list);
        createMenu = new CreateMenuGUI(cardPanel, cardLayout, list);
        manageMenu = new ManageMenuGUI(cardPanel, cardLayout, list);
        battleMenu = new BattleMenuGUI(cardPanel, cardLayout, list);
        menus = new ArrayList<>();
        menus.add(createMenu);
        menus.add(manageMenu);
        menus.add(battleMenu);
        for (MenuGUI m : menus) {
            m.setMainMenu(this);
        }
        this.setLayout(gridLayout);
        this.setPreferredSize(new Dimension(600, 400));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        menuCommands(e);
        returnCommands(e);
    }

    private void menuCommands(ActionEvent e) {
        if (e.getActionCommand().equals("create")) {
            createMenu.initializeCreate();
        }
        if (e.getActionCommand().equals("manage")) {
            manageMenu.initializeManage();
        }
        if (e.getActionCommand().equals("battle")) {
            battleMenu.initializeBattle();
        }
        if (e.getActionCommand().equals("save")) {
            saveList();
            playSound("completed-ding-3.wav");
            saveConfirm();
        }
        if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        }
    }

    private void saveConfirm() {
        JPanel saveConfirm = new JPanel();
        addToPanel(saveConfirm, "save");
        setBoxLayout(saveConfirm);

        instantiateIntro(saveConfirm, "Character data has been saved to" + LIST_FILE);
        instantiateGoBack(saveConfirm, "return");
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

    @Override
    public void addCharacter(Character c) {
        for (MenuGUI m : menus) {
            if (!list.contains(c)) {
                list.addCharacter(c);
                m.addCharacter(c);
            }
        }
    }

    @Override
    public void removeCharacter(Character c) throws CharacterDoesntExistException {
        for (MenuGUI m : menus) {
            if (list.contains(c)) {
                list.removeCharacter(c);
                m.removeCharacter(c);
            }
        }
    }
}
