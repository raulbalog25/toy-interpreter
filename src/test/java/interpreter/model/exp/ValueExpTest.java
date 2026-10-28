package interpreter.model.exp;

import interpreter.model.adt.MyDictionary;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValueExpTest {
    @Test
    void evaluatesToItsStoredValue() throws Exception {
        ValueExp expression = new ValueExp(new IntValue(7));

        assertEquals(
                new IntValue(7),
                expression.eval(new MyDictionary<>())
        );
    }
}