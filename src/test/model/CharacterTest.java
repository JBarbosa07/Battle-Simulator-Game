package model;

import model.exceptions.InvalidInputException;
import model.exceptions.StatLargerThanPoolException;
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
        assertEquals(1000, character.getStatPool());
    }

    @Test
    public void testSetNameValid() {
        character.setName("Name");
        assertEquals("Name", character.getName());
    }

    @Test
    public void testSetHPValid() {
        try {
            character.setHP(340);
        } catch (InvalidInputException e) {
           fail("Unexpected exception call");
        } catch (StatLargerThanPoolException e) {
            fail("Unexpected exception call");
        }
        assertEquals(340, character.getHP());
        assertEquals(1000-340, character.getStatPool());
    }

    @Test
    public void testSetHPInvalid() {
        try {
            character.setHP(0);
            fail("Should fail");
        } catch (InvalidInputException e) {
            // expected
        } catch (StatLargerThanPoolException e) {
            fail("Unexpected exception call");
        }
        assertEquals(0, character.getHP());
        assertEquals(1000, character.getStatPool());
    }

    @Test
    public void testSetHPExceedsPool() {
        try {
            character.setHP(1100);
            fail("Should fail");
        } catch (InvalidInputException e) {
            fail("Unexpected exception call");
        } catch (StatLargerThanPoolException e) {
            // expected
        }
        assertEquals(0, character.getHP());
        assertEquals(1000, character.getStatPool());
    }

    @Test
    public void testSetATKValid() {
        try {
            character.setATK(440);
        } catch (StatLargerThanPoolException e) {
            fail("Unexpected exception call");
        }
        assertEquals(440, character.getATK());
        assertEquals(1000-440, character.getStatPool());
    }

    @Test
    public void testSetATKInvalid() {
        try {
            character.setATK(1100);
            fail("Should fail");
        } catch (StatLargerThanPoolException e) {
            // expected
        }
        assertEquals(0, character.getATK());
        assertEquals(1000, character.getStatPool());
    }

    @Test
    public void testSetDEFValid() {
        try {
            character.setDEF(220);
        } catch (StatLargerThanPoolException e) {
            fail("Unexpected exception call");
        }
        assertEquals(220, character.getDEF());
        assertEquals(1000-220, character.getStatPool());
    }

    @Test
    public void testSetDEFInvalid() {
        try {
            character.setDEF(1100);
            fail("Should fail");
        } catch (StatLargerThanPoolException e) {
            //expect
        }
        assertEquals(0, character.getDEF());
        assertEquals(1000, character.getStatPool());
    }

    @Test
    public void testSetAllStatsValid() throws InvalidInputException {
        try {
            character.setHP(340);
            character.setATK(440);
            character.setDEF(220);
        } catch (StatLargerThanPoolException e) {
            fail("Unexpected exception call");
        }
        assertEquals(0, character.getStatPool());
    }

    @Test
    public void testSetQuote() {
        character.setQuote("Hiyah!");
        assertEquals("Hiyah!", character.getQuote());
    }

    @Test
    public void testViewCharacter() throws InvalidInputException, StatLargerThanPoolException {
        character.setName("Name");
        character.setHP(340);
        character.setATK(440);
        character.setDEF(220);
        character.setQuote("Hyah!");
        assertEquals("Name - HP: 340, ATK: 440, DEF: 220, Quote: Hyah!",
                character.printCharacter());
    }

    @Test
    public void testAttacked() throws InvalidInputException, StatLargerThanPoolException {
        character.setHP(50);
        character.setDEF(30);

        otherCharacter.setATK(40);

        character.attackedBy(otherCharacter);
        assertEquals(50 - (40 - 30),character.getHP());
    }

    @Test
    public void testAttackedOverkill() throws InvalidInputException, StatLargerThanPoolException {
        character.setHP(10);

        otherCharacter.setATK(50);

        character.attackedBy(otherCharacter);
        assertEquals(0, character.getHP());
    }

    @Test
    public void testAttackedZero() throws InvalidInputException, StatLargerThanPoolException {
        character.setHP(10);
        character.setDEF(500);

        otherCharacter.setATK(50);

        character.attackedBy(otherCharacter);
        assertEquals(10, character.getHP());
    }

    @Test
    public void testIsDeadTrue() {
        assertTrue(character.isDead());
    }

    @Test
    public void testIsDeadFalse() throws InvalidInputException, StatLargerThanPoolException {
        character.setHP(1);
        assertFalse(character.isDead());
    }
}