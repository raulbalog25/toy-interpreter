package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VarExpTest {
    @Test
    void evaluatesToTheVariableValue() throws Exception {
        MyDictionary<String, IValue> symTable = new MyDictionary<>();
        symTable.put("x", new IntValue(12));

        assertEquals(new IntValue(12), new VarExp("x").eval(symTable));
    }

    @Test
    void undefinedVariableThrows() {
        MyDictionary<String, IValue> symTable = new MyDictionary<>();

        assertThrows(
                ExpressionException.class,
                () -> new VarExp("missing").eval(symTable)
        );
    }
}