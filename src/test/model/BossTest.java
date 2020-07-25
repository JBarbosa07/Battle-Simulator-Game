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
        assertEquals("DESTROYER", boss.getName());
        assertEquals(500, boss.getHP());
        assertEquals(350, boss.getATK());
        assertEquals(200, boss.getDEF());
        assertEquals("DIE, SCUM", boss.getQuote());
    }
}
