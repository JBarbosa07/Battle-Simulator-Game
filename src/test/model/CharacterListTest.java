package model;

import model.exceptions.CharacterDoesntExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.IOException;

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
        assertTrue(list.contains(character));
        assertEquals(1, list.getSize());
    }

    @Test
    public void testAddTwoCharacters() {
        list.addCharacter(character);
        list.addCharacter(otherCharacter);
        assertTrue(list.contains(character));
        assertTrue(list.contains(otherCharacter));
        assertEquals(2, list.getSize());
    }

    @Test
    public void testRemoveCharacter() {
        list.addCharacter(character);
        assertTrue(list.contains(character));
        assertEquals(1, list.getSize());
        try {
            list.removeCharacter(character);
        } catch (CharacterDoesntExistException e) {
            fail("Unexpected exception call");
        }
        assertFalse(list.contains(character));
        assertEquals(0, list.getSize());
    }

    @Test
    public void testRemoveCharacterDoesntExist() {
        list.addCharacter(character);
        assertTrue(list.contains(character));
        assertEquals(1, list.getSize());
        try {
            list.removeCharacter(otherCharacter);
            fail("should fail");
        } catch (CharacterDoesntExistException e) {
            // expected
        }
        assertTrue(list.contains(character));
        assertEquals(1, list.getSize());
    }

    @Test
    public void testRemoveTwoCharacters() {
        list.addCharacter(character);
        list.addCharacter(otherCharacter);
        assertTrue(list.contains(character));
        assertTrue(list.contains(otherCharacter));
        assertEquals(2, list.getSize());
        try {
            list.removeCharacter(character);
            list.removeCharacter(otherCharacter);
        } catch (CharacterDoesntExistException e) {
            fail("Unexpected exception call");
        }
        assertFalse(list.contains(character));
        assertFalse(list.contains(otherCharacter));
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
    public void testPrintCharactersEmpty() {
        assertEquals("Here are your current characters: You currently do not have any characters",
                list.printCharacters());
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

    @Test
    public void testSave() throws IOException {
        String TEST_FILE = "./data/testCharacterList4.txt";
        Writer testWriter = new Writer((new File(TEST_FILE)));
        list.addCharacter(character);
        list.addCharacter(otherCharacter);
        testWriter.write(list);
        testWriter.close();
        CharacterList expectedList = Reader.readList(new File(TEST_FILE));
        assertTrue(expectedList.contains(character));
        assertTrue(expectedList.contains(otherCharacter));
    }
}
