package model;

import model.exceptions.CharacterDoesntExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterListTest {
    CharacterList list;
    Character character;
    Character otherCharacter;

    @BeforeEach
    public void runBefore() {
        list = new CharacterList();
        character = new Character();
        otherCharacter = new Character();

        character.setName("Name");
        otherCharacter.setName("Name2");
    }

    @Test
    public void testCharacterList() {
        assertEquals(0, list.getSize());
    }

    @Test
    public void testAddCharacter() {
        list.addCharacter(character);
        assertEquals(1, list.getSize());
    }

    @Test
    public void testAddTwoCharacters() {
        list.addCharacter(character);
        list.addCharacter(otherCharacter);
        assertEquals(2, list.getSize());
    }

    @Test
    public void testRemoveCharacter() {
        list.addCharacter(character);
        try {
            list.removeCharacter(character);
        } catch (CharacterDoesntExistException e) {
            fail("Unexpected exception call");
        }
        assertEquals(0, list.getSize());
    }

    @Test
    public void testRemoveCharacterDoesntExist() {
        list.addCharacter(character);
        try {
            list.removeCharacter(otherCharacter);
        } catch (CharacterDoesntExistException e) {
            // expected
        }
        assertEquals(1, list.getSize());
    }

    @Test
    public void testRemoveTwoCharacters() throws CharacterDoesntExistException {
        list.addCharacter(character);
        list.addCharacter(otherCharacter);
        list.removeCharacter(character);
        assertEquals(1, list.getSize());
        list.removeCharacter(otherCharacter);
        assertEquals(0, list.getSize());
    }

    @Test
    public void testGetCharacter() {
        list.addCharacter(character);
        try {
            assertEquals(character, list.getCharacter("Name"));
        } catch (CharacterDoesntExistException e) {
            fail("Unexpected exception call");
        }
    }

    @Test
    public void testGetCharacterDoesntExist() {
        list.addCharacter(character);
        try {
            list.getCharacter("Name2");
            fail("Should fail");
        } catch (CharacterDoesntExistException e) {
            //expect this
        }
    }

    @Test
    public void testGetCharacterFromMultiList() throws CharacterDoesntExistException {
        list.addCharacter(character);
        list.addCharacter(otherCharacter);
        assertEquals(otherCharacter, list.getCharacter("Name2"));
    }

    @Test
    public void testPrintCharacters() {
        list.addCharacter(character);
        list.addCharacter(otherCharacter);
        assertEquals("Here are your current characters: Name, Name2," , list.printCharacters());
    }

    @Test
    public void testAddRemoveThenPrintCharacters() throws CharacterDoesntExistException {
        list.addCharacter(character);
        list.addCharacter(otherCharacter);
        list.removeCharacter(otherCharacter);
        assertEquals("Here are your current characters: Name," , list.printCharacters());
    }

    @Test
    public void testisNameTakenTrue() {
        list.addCharacter(character);
        assertTrue(list.isNameTaken("Name"));
    }

    @Test
    public void testisNameTakenFalse() {
        list.addCharacter(character);
        assertFalse(list.isNameTaken("Name2"));
    }
}
