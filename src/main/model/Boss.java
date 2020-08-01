package model;

// Represents a boss combatant in the game
public class Boss extends Combatant {
    private static final String bossName = "DESTROYER";
    private static int bossHP = 500;
    private static final int bossATK = 350;
    private static final int bossDEF = 200;
    private static final String bossQuote = "DIE, SCUM";

    public Boss() {
        super(bossName, bossHP, bossATK, bossDEF, bossQuote);
    }

}
