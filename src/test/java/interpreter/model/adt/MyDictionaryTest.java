package interpreter.model.adt;
import interpreter.exceptions.ADTException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyDictionaryTest {
    @Test
    void putsLooksUpAndUpdatesValue() throws Exception {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();
        dictionary.put("x", 1);

        assertEquals(1, dictionary.lookup("x"));

        dictionary.update("x", 2);

        assertEquals(2, dictionary.lookup("x"));
    }

    @Test
    void lookingUpMissingKeyThrows() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        assertThrows(ADTException.class, () -> dictionary.lookup("missing"));
    }
}