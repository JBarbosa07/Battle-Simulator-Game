package model;

import model.exceptions.InvalidInputException;
import model.exceptions.StatLargerThanPoolException;

// Represents a character created by the player
public class Character extends Combatant {
    private int statPool;

    // Constructs a character with an initial stat pool of 1000
    public Character() {
        super(null, 0, 0, 0, null);
        statPool = 1000;
    }

    // MODIFIES: this
    // EFFECTS: sets the name of the character
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: sets the HP stat of the character and subtracts the same amount from the stat pool; if hp <= 0, throws
    // InvalidInputException; if hp > stat pool, throws StatLargerThanPoolException
    public void setHP(int hp) throws InvalidInputException, StatLargerThanPoolException {
        if (hp < 1) {
            throw new InvalidInputException();
        }
        checkWithinStatPool(hp);
        this.hp = hp;
        statPool -= hp;
    }

    // MODIFIES: this
    // EFFECTS: sets the ATK stat of the character and subtracts the same amount from the stat pool; if atk > stat pool,
    // throws StatLargerThanPoolException
    public void setATK(int atk) throws StatLargerThanPoolException {
        checkWithinStatPool(atk);
        this.atk = atk;
        statPool -= atk;
    }

    // MODIFIES: this
    // EFFECTS: sets the DEF stat of the character and subtracts the same amount from the stat pool; if def > stat pool,
    // throws StatLargerThanPoolException
    public void setDEF(int def) throws StatLargerThanPoolException {
        checkWithinStatPool(def);
        this.def = def;
        statPool -= def;
    }

    private void checkWithinStatPool(int stat) throws StatLargerThanPoolException {
        if (stat > statPool) {
            throw new StatLargerThanPoolException();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the battle quote of the character
    public void setQuote(String quote) {
        this.quote = quote;
    }

    // EFFECTS: lists the stats and attributes of the given character
    public String printCharacter() {
        return name + " - " + "HP: " + hp + ", ATK: " + atk + ", DEF: " + def + ", Quote: " + quote;
    }

    // getters
    public int getStatPool() {
        return statPool;
    }
}
