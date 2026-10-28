package interpreter.model.exp;

import interpreter.model.adt.MyDictionary;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValueExpTest {
    @Test
    void evaluatesConstantValue() throws Exception {
        ValueExp expression = new ValueExp(new IntValue(12));

        IntValue result = (IntValue) expression.eval(new MyDictionary<>());

        assertEquals(12, result.getVal());
    }
}