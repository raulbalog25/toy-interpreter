package interpreter.model.type;

import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeTest {
    @Test
    void intTypeHasDefaultValueZero() {
        assertEquals(new IntValue(0), new IntType().defaultValue());
    }

    @Test
    void boolTypeHasDefaultValueFalse() {
        assertEquals(new BoolValue(false), new BoolType().defaultValue());
    }
}