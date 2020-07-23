package model;

// Represents a combatant in the game
public abstract class Combatant {
//    protected String name;
//    protected String colour;
//    protected int hp;
//    protected int atk;
//    protected int def;
//    protected String quote;

    public Combatant() {
//        this.name = null;
//        this.colour = null;
//        this.hp = 0;
//        this.atk = 0;
//        this.def = 0;
//        this.quote = null;
    }

    // MODIFIES: this
    // EFFECTS: decreases the character's hp by (opponent's ATK - character's def). If the damage dealt exceeds the hp
    // remaining, stop the hp decrease at 0.
    public void attacked(Combatant c) {

    }

    // EFFECTS: if combatant's hp is 0, returns true, otherwise returns false
    public boolean isDead() {
        return false;
    }

    //getters
    public String getName() {
        return "";
    }

    public String getColour() {
        return "";
    }

    public int getHP() {
        return 0;
    }

    public int getATK() {
        return 0;
    }

    public int getDEF() {
        return 0;
    }

    public String getQuote() {
        return "";
    }
}
