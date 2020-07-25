package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    Character character;
    Character otherCharacter;


    @BeforeEach
    public void runBefore() {
        character = new Character();
        otherCharacter = new Character();
    }

    @Test
    public void testCharacter() {
        assertEquals(500, character.getStatPool());
    }

    @Test
    public void testSetNameValid() {
        character.setName("Name");
        assertEquals("Name", character.getName());
    }

    @Test
    public void testSetHPValid() {
        character.setHP(250);
        assertEquals(250, character.getHP());
        assertEquals(500-250, character.getStatPool());
    }

    @Test
    public void testSetATKValid() {
        character.setATK(150);
        assertEquals(150, character.getATK());
        assertEquals(500-150, character.getStatPool());
    }

    @Test
    public void testSetDEFValid() {
        character.setDEF(100);
        assertEquals(100, character.getDEF());
        assertEquals(500-100, character.getStatPool());
    }

    @Test
    public void testSetAllStatsValid() {
        character.setHP(250);
        character.setATK(150);
        character.setDEF(100);
        assertEquals(0, character.getStatPool());
    }

    @Test
    public void testSetQuote() {
        character.setQuote("Hiyah!");
        assertEquals("Hiyah!", character.getQuote());
    }

    @Test
    public void testViewCharacter() {
        character.setName("Name");
        character.setHP(250);
        character.setATK(150);
        character.setDEF(100);
        character.setQuote("Hyah!");
        assertEquals("Name - HP: 250, ATK: 150, DEF: 100, Quote: Hyah!",
                character.printCharacter());
    }

    @Test
    public void testAttacked() {
        character.setHP(50);
        character.setDEF(30);

        otherCharacter.setATK(40);

        character.attackedBy(otherCharacter);
        assertEquals(50 - (40 - 30),character.getHP());
    }

    @Test
    public void testAttackedOverkill() {
        character.setHP(10);

        otherCharacter.setATK(50);

        character.attackedBy(otherCharacter);
        assertEquals(0, character.getHP());
    }

    @Test
    public void testIsDeadTrue() {
        assertTrue(character.isDead());
    }

    @Test
    public void testIsDeadFalse() {
        character.setHP(1);
        assertFalse(character.isDead());
    }
}