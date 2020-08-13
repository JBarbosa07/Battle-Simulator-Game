package ui.gui.submenus;

import com.sun.tools.javac.util.List;
import model.Character;
import model.CharacterList;
import model.exceptions.CharacterDoesntExistException;
import ui.console.submenus.SubMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

// NOTE: I referenced the Java swing demo files for the implementation of my GUI

public abstract class MenuGUI extends JPanel implements ActionListener {
    protected static GridLayout gridLayout = new GridLayout(0, 1);
    protected final JPanel cardPanel;
    protected final CardLayout cardLayout;
    protected Character character;
    protected CharacterList list;
    protected JTextField textField;
    protected JLabel errorLabel;
    protected MenuGUI mainMenu;
    protected ArrayList<JPanel> panels;
    private JLabel title;
    private JLabel intro;
    private JLabel confirmStatSet;
    private JLabel characters;
    private JButton submitButton;
    private JButton goBack;


    public MenuGUI(JPanel cardPanel, CardLayout cardLayout, CharacterList list) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
        this.list = list;
        this.panels = new ArrayList<>();
    }

    protected void setMainMenu(MenuGUI m) {
        this.mainMenu = m;
    }

    protected void addCharacter(Character c) {
        if (!list.contains(c)) {
            list.addCharacter(c);
            mainMenu.addCharacter(c);
        }
    }

    protected void removeCharacter(Character c) throws CharacterDoesntExistException {
        if (list.contains(c)) {
            list.removeCharacter(c);
            mainMenu.removeCharacter(c);
        }
    }

    // Helpers
    protected void addToPanel(JPanel panel, String name) {
        cardPanel.add(panel, name);
        cardLayout.show(cardPanel, name);
    }

    protected void setBoxLayout(JPanel nameMenu) {
        nameMenu.setLayout(new BoxLayout(nameMenu, BoxLayout.Y_AXIS));
    }

    protected void setGridLayout(JPanel manageMenu) {
        GridLayout gridLayout = new GridLayout(0, 1);
        manageMenu.setLayout(gridLayout);
    }

    protected void instantiateTitle(JPanel panel, String s) {
        title = new JLabel(s, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.ITALIC, 30));
        panel.add(title);
        title.setAlignmentX(CENTER_ALIGNMENT);
    }

    protected void instantiateIntro(JPanel panel, String s) {
        intro = new JLabel(s, SwingConstants.CENTER);
        panel.add(intro);
        intro.setAlignmentX(CENTER_ALIGNMENT);
    }

    protected void instantiateConfirmStatSetString(JPanel hpMenu, String s) {
        confirmStatSet = new JLabel(s);
        hpMenu.add(confirmStatSet);
        confirmStatSet.setAlignmentX(CENTER_ALIGNMENT);
    }

    protected void instantiateConfirmStatSetInt(JPanel panel, String s, int stat) {
        confirmStatSet = new JLabel(s + stat);
        panel.add(confirmStatSet);
        confirmStatSet.setAlignmentX(CENTER_ALIGNMENT);
    }

    protected void instantiatePrintCharacters(JPanel panel) {
        characters = new JLabel(list.printCharacters(), SwingConstants.CENTER);
        panel.add(characters);
        characters.setAlignmentX(CENTER_ALIGNMENT);
    }

    protected void instantiateTextField(JPanel panel) {
        textField = new JTextField(10);
        textField.setMaximumSize(textField.getPreferredSize());
        panel.add(textField);
        textField.setAlignmentX(CENTER_ALIGNMENT);
    }

    protected void instantiateSubmitButton(JPanel panel, String s) {
        submitButton = new JButton("Submit");
        submitButton.setActionCommand(s);
        submitButton.addActionListener(this);
        panel.add(submitButton);
        submitButton.setAlignmentX(CENTER_ALIGNMENT);
    }

    protected void instantiateErrorLabel(JPanel panel) {
        errorLabel = new JLabel("");
        panel.add(errorLabel);
        errorLabel.setAlignmentX(CENTER_ALIGNMENT);
    }

    protected void instantiateGoBack(JPanel panel, String returnString) {
        goBack = new JButton("Return to previous menu");
        goBack.setActionCommand(returnString);
        goBack.addActionListener(this);
        panel.add(goBack);
        goBack.setAlignmentX(CENTER_ALIGNMENT);
    }

    protected void returnCommands(ActionEvent e) {
        if (e.getActionCommand().equals("return")) {
            cardLayout.show(cardPanel, "Menu");
        }
    }
}
