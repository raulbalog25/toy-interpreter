package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArithExpTest {
    private final MyDictionary<String, IValue> symTable = new MyDictionary<>();

    private IExp num(int value) {
        return new ValueExp(new IntValue(value));
    }

    @Test
    void additionWorks() throws Exception {
        assertEquals(new IntValue(5),
                new ArithExp(ArithOp.ADD, num(2), num(3)).eval(symTable));
    }

    @Test
    void subtractionWorks() throws Exception {
        assertEquals(new IntValue(2),
                new ArithExp(ArithOp.SUB, num(5), num(3)).eval(symTable));
    }

    @Test
    void multiplicationWorks() throws Exception {
        assertEquals(new IntValue(6),
                new ArithExp(ArithOp.MUL, num(2), num(3)).eval(symTable));
    }

    @Test
    void divisionWorks() throws Exception {
        assertEquals(new IntValue(4),
                new ArithExp(ArithOp.DIV, num(8), num(2)).eval(symTable));
    }

    @Test
    void divisionByZeroThrows() {
        ArithExp expression = new ArithExp(ArithOp.DIV, num(1), num(0));

        assertThrows(ExpressionException.class, () -> expression.eval(symTable));
    }

    @Test
    void nonIntegerOperandThrows() {
        IExp bool = new ValueExp(new BoolValue(true));
        ArithExp expression = new ArithExp(ArithOp.ADD, bool, num(1));

        assertThrows(ExpressionException.class, () -> expression.eval(symTable));
    }
}