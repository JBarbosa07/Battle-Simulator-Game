package model;

// Represents a boss combatant in the game
public class Boss extends Combatant {
    private static final String bossName = "Big Boss";
    private static int bossHP = 500;
    private static final int bossATK = 400;
    private static final int bossDEF = 200;
    private static final String bossQuote = "Die, scum!";

    public Boss() {
        super(bossName, bossHP, bossATK, bossDEF, bossQuote);
    }

}
