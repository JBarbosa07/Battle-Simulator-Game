package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BossTest {

    Boss boss;

    @BeforeEach
    public void runBefore() {
        boss = new Boss();
    }

    @Test
    public void testBoss() {
        assertEquals("Big Boss", boss.getName());
        assertEquals(500, boss.getHP());
        assertEquals(400, boss.getATK());
        assertEquals(200, boss.getDEF());
        assertEquals("Die, scum!", boss.getQuote());
    }
}
