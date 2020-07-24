package model;

// Represents a character created by the player
public class Character extends Combatant {
    private int statPool;

    // Constructs a character with an initial stat pool of 100
    public Character() {
        super();
        statPool = 500;
    }

    // REQUIRES: char length is at least >= 1
    // MODIFIES: this
    // EFFECTS: sets the name of the character
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: hp > 0, hp <= stat pool
    // MODIFIES: this
    // EFFECTS: sets the HP stat of the character and subtracts the same amount from the stat pool
    public void setHP(int hp) {
        this.hp = hp;
        statPool = statPool - hp;
    }

    // REQUIRES: atk >= 1, atk <= stat pool
    // MODIFIES: this
    // EFFECTS: sets the ATK stat of the character and subtracts the same amount from the stat pool
    public void setATK(int atk) {
        this.atk = atk;
        statPool = statPool - atk;
    }

    // REQUIRES: def <= stat pool
    // MODIFIES: this
    // EFFECTS: sets the DEF stat of the character and subtracts the same amount from the stat pool
    public void setDEF(int def) {
        this.def = def;
        statPool = statPool - def;
    }

    // MODIFIES: this
    // EFFECTS: sets the battle quote of the character
    public void setQuote(String quote) {
        this.quote = quote;
    }

    // EFFECTS: lists the stats and attributes of the given character
    public String viewCharacter() {
        return name + " - " + "HP: " + hp + ", ATK: " + atk + ", DEF: " + def + ", Quote: " + quote;
    }

    // getters
    public int getStatPool() {
        return statPool;
    }
}
