package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VarExpTest {
    @Test
    void evaluatesDefinedVariable() throws Exception {
        MyDictionary<String, interpreter.model.value.IValue> symTable =
                new MyDictionary<>();
        symTable.put("x", new IntValue(7));

        VarExp expression = new VarExp("x");

        assertEquals(7, ((IntValue) expression.eval(symTable)).getVal());
    }

    @Test
    void undefinedVariableThrows() {
        VarExp expression = new VarExp("missing");

        assertThrows(
                ExpressionException.class,
                () -> expression.eval(new MyDictionary<>())
        );
    }
}