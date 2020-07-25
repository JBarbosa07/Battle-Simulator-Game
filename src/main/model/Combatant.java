package model;

// Represents a combatant in the game
public abstract class Combatant {
    protected String name;
    protected int hp;
    protected int atk;
    protected int def;
    protected String quote;

    public Combatant() {
        this.name = null;
        this.hp = 0;
        this.atk = 0;
        this.def = 0;
        this.quote = null;
    }

    // MODIFIES: this
    // EFFECTS: decreases the character's hp by (opponent's ATK - character's def). If the damage dealt exceeds the hp
    // remaining, stop the hp decrease at 0.
    public void attackedBy(Combatant c) {
        int damage = (c.getATK() - def);
        if (damage < 0) {
            damage = 0;
        }
        if (hp - damage >= 0) {
            hp = hp - damage;
        } else {
            hp = 0;
        }
    }

    // EFFECTS: if combatant's hp is 0, returns true, otherwise returns false
    public boolean isDead() {
        return hp == 0;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getHP() {
        return hp;
    }

    public int getATK() {
        return atk;
    }

    public int getDEF() {
        return def;
    }

    public String getQuote() {
        return quote;
    }
}
