package model;

import java.util.Objects;

// Represents a combatant in the game
public abstract class Combatant {
    protected String name;
    protected int hp;
    protected int atk;
    protected int def;
    protected String quote;

    public Combatant(String name, int hp, int atk, int def, String quote) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.quote = quote;
    }

    // MODIFIES: this
    // EFFECTS: decreases the character's hp by (opponent's ATK - character's def). If the damage dealt exceeds the hp
    // remaining, stop the hp decrease at 0.
    public void attackedBy(Combatant c) {
        int damage = (c.getATK() - def);
        if (damage < 0) {
            damage = 0;
        }
        hp = Math.max(hp - damage, 0);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Combatant combatant = (Combatant) o;
        return Objects.equals(name, combatant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
