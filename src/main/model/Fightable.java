package model;

public interface Fightable {

    void attackedBy(Combatant c);

    boolean isDead();
}
