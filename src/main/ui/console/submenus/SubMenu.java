package ui.console.submenus;

import model.Character;
import model.CharacterList;
import model.exceptions.CharacterDoesntExistException;
import ui.console.BattleSimulator;

import java.util.Scanner;

// Represents a submenu branch of the main BattleSimulator
public abstract class SubMenu {
    protected Character character;
    protected CharacterList list;
    protected Scanner input;
    protected BattleSimulator battleSim;

    public SubMenu(CharacterList list, Scanner input) {
        this.list = list;
        this.input = input;
    }

    public void setBattleSim(BattleSimulator battleSim) {
        this.battleSim = battleSim;
    }

    public CharacterList getList() {
        return list;
    }

    public String getInputString() {
        input.useDelimiter("\n");
        return input.next();
    }

    // MODIFIES: this, battleSim
    // EFFECTS: if character list doesn't contain c, add c to list and battleSim's character list
    public void addCharacter(Character c) {
        if (!list.contains(c)) {
            list.addCharacter(c);
            battleSim.addCharacter(c);
        }
    }

    // MODIFIES: this, battleSim
    // EFFECTS: if character list contains c, remove c from list and battleSim's character list
    public void removeCharacter(Character c) throws CharacterDoesntExistException {
        if (list.contains(c)) {
            list.removeCharacter(c);
            battleSim.removeCharacter(c);
        }
    }
}
