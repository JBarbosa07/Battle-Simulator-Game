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
        assertEquals(1000, boss.getHP());
        assertEquals(500, boss.getATK());
        assertEquals(400, boss.getDEF());
        assertEquals("DIE SCUM", boss.getQuote());
    }
}
