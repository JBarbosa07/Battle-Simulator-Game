package persistence;

import com.sun.source.tree.AssertTree;
import model.Character;
import model.CharacterList;
import model.exceptions.CharacterDoesntExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {
    Character p1;
    Character p2;
    Character p3;
    Character p4;

    @BeforeEach
    void runBefore() {
        p1 = new Character();
        p2 = new Character();
        p3 = new Character();
        p4 = new Character();
        p1.setName("Guy");
        p2.setName("Dude");
        p3.setName("Mynthra");
        p4.setName("Weak");
    }

    @Test
    void testParseAccountsFile1() throws CharacterDoesntExistException {
        try {
            CharacterList list = Reader.readList(new File("./data/testCharacterList2.txt"));
            Character p1Copy = list.getCharacter("Guy");
            Character p2Copy = list.getCharacter("Dude");

            assertTrue(list.isContained(p1));
            assertTrue(list.isContained(p2));

            assertEquals("Guy", p1Copy.getName());
            assertEquals(500, p1Copy.getHP());
            assertEquals(500, p1Copy.getATK());
            assertEquals(0, p1Copy.getDEF());
            assertEquals("Bruh", p1Copy.getQuote());
            assertEquals(0, p1Copy.getStatPool());

            assertEquals("Dude", p2Copy.getName());
            assertEquals(400, p2Copy.getHP());
            assertEquals(300, p2Copy.getATK());
            assertEquals(300, p2Copy.getDEF());
            assertEquals("En garde!", p2Copy.getQuote());
            assertEquals(0, p2Copy.getStatPool());
        } catch (IOException e) {
            fail("Unexpected call to exception");
        }
    }

    @Test
    void testParseAccountsFile2() throws CharacterDoesntExistException {
        try {
            CharacterList list = Reader.readList(new File("./data/testCharacterList3.txt"));
            Character p3Copy = list.getCharacter("Mynthra");
            Character p4Copy = list.getCharacter("Weak");

            assertTrue(list.isContained(p3));
            assertTrue(list.isContained(p4));

            assertEquals("Mynthra", p3Copy.getName());
            assertEquals(300, p3Copy.getHP());
            assertEquals(450, p3Copy.getATK());
            assertEquals(250, p3Copy.getDEF());
            assertEquals("How annoying", p3Copy.getQuote());
            assertEquals(0, p3Copy.getStatPool());

            assertEquals("Weak", p4Copy.getName());
            assertEquals(1, p4Copy.getHP());
            assertEquals(0, p4Copy.getATK());
            assertEquals(0, p4Copy.getDEF());
            assertEquals("I suck", p4Copy.getQuote());
            assertEquals(999, p4Copy.getStatPool());
        } catch (IOException e) {
            fail("Unexpected call to exception");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readList(new File("./path/does/not/exist/testAccount.txt"));
            fail("should fail");
        } catch (IOException e) {
            // expected
        }
    }



}
