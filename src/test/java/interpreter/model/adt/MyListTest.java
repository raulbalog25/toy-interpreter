package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyListTest {
    @Test
    void addGetAndSizeWork() throws ADTException {
        MyList<String> list = new MyList<>();
        list.add("first");
        list.add("second");

        assertEquals(2, list.size());
        assertEquals("first", list.get(0));
        assertEquals("second", list.get(1));
    }

    @Test
    void getOutsideListThrows() {
        MyList<String> list = new MyList<>();

        assertThrows(ADTException.class, () -> list.get(0));
    }
}