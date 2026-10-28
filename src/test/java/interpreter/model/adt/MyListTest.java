package interpreter.model.adt;
import interpreter.exceptions.ADTException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyListTest {
    @Test
    void addsGetsAndCountsElements() throws Exception {
        MyList<Integer> list = new MyList<>();
        list.add(10);
        list.add(20);

        assertEquals(2, list.size());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
    }

    @Test
    void gettingInvalidIndexThrows() {
        MyList<Integer> list = new MyList<>();

        assertThrows(ADTException.class, () -> list.get(0));
    }
}