package ui.console.submenus;

import model.Boss;
import model.Character;
import model.CharacterList;
import model.Combatant;
import model.exceptions.CharacterDoesntExistException;
import ui.exceptions.StalemateException;

import java.util.Scanner;

// Represents the BattleSimulator's combat system
public class CombatSystem extends SubMenu {
    private Boss boss;

    public CombatSystem(CharacterList list, Scanner input) {
        super(list, input);
    }

    // EFFECTS: prompts player to select two characters and have them fight until one dies
    public void characterVsCharacter() throws CharacterDoesntExistException {
        System.out.println("\n" + list.printCharacters());

        System.out.println("\nPlease select fighter 1");
        String name1 = getInputString();
        Character p1 = list.getCharacter(name1);
        System.out.println("\n" + name1 + " was chosen to be fighter 1");

        System.out.println("Please select fighter 2");
        String name2 = getInputString();
        Character p2 = list.getCharacter(name2);
        System.out.println("\n" + name2 + " was chosen to be fighter 2");

        startBattle(p1, p2);
        p1.resetHP();
        p2.resetHP();
    }

    // EFFECTS: prompts player to select a character to fight a boss until one dies
    public void characterVsBoss() throws CharacterDoesntExistException {
        boss = new Boss();
        System.out.println("\n" + list.printCharacters());
        System.out.println("\nPlease select your fighter");
        String name = getInputString();
        Character c = list.getCharacter(name);
        System.out.println("\n" + name + " was chosen to fight " + boss.getName());

        startBattle(c, boss);
        c.resetHP();
    }

    // EFFECTS: starts the combat simulation and potentially catches a StalemateException
    private void startBattle(Combatant p1, Combatant p2) {
        try {
            combatSimulation(p1, p2);
        } catch (StalemateException e) {
            System.out.println("\nThey were evenly matched! The battle was a tie!");
        }
    }

    // EFFECTS: Represents a fight between the two selected combatants; if both combatants deal 0 damage to each other,
    // throws StalemateException
    private void combatSimulation(Combatant p1, Combatant p2) throws StalemateException {
        int damage1 = p1.getATK() - p2.getDEF();
        int damage2 = p2.getATK() - p1.getDEF();

        if ((damage1 <= 0) && (damage2 <= 0)) {
            throw new StalemateException();
        }

        System.out.println("\nLet the battle begin!");

        inCombat(p1, p2, damage1, damage2);

        battleResult(p1, p2);
    }

    // EFFECTS: while combatants are both alive, have them take turns dealing damage to each other. If p1 happens to
    // bring p2's hp to 0 before p2's next action, skip p2's next action
    private void inCombat(Combatant p1, Combatant p2, int damage1, int damage2) {
        while (!p1.isDead() && !p2.isDead()) {
            System.out.println("\n" + p1.getName() + ": " + p1.getQuote());
            p2.attackedBy(p1);
            damageMessage(p1, p2, damage1);
            System.out.println("\n" + p2.getName() + " has " + p2.getHP() + " HP remaining!");

            if (!p2.isDead()) {
                System.out.println("\n" + p2.getName() + ": " + p2.getQuote());
                p1.attackedBy(p2);
                damageMessage(p2, p1, damage2);
                System.out.println("\n" + p1.getName() + " has " + p1.getHP() + " HP remaining!");
            }
        }
    }

    // EFFECTS: If p1 is dead, print out that p2 wins. If p2 is dead, print out that p1 wins
    private void battleResult(Combatant p1, Combatant p2) {

        if (p1.isDead()) {
            System.out.println("\n" + p1.getName() + " can no longer fight!  " + p2.getName() + " wins!");
        } else {
            System.out.println("\n" + p2.getName() + " can no longer fight! " + p1.getName() + " wins!");
        }
    }

    // EFFECTS: if damage is > 0, prints ordinary damage number in message, else prints 0 damage dealt
    private void damageMessage(Combatant p1, Combatant p2, int damage) {
        if (damage > 0) {
            System.out.println("\n" + p1.getName() + " attacked " + p2.getName() + " and did " + damage + " damage!");
        } else {
            System.out.println("\n" + p1.getName() + " attacked " + p2.getName() + " and did 0 damage!");
        }
    }
}
