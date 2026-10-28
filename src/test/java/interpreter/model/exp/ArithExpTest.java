package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArithExpTest {
    private IExp number(int value) {
        return new ValueExp(new IntValue(value));
    }

    private IDictionary<String, interpreter.model.value.IValue> emptySymTable() {
        return new MyDictionary<>();
    }

    @Test
    void addsIntegers() throws Exception {
        IExp expression = new ArithExp(ArithOp.ADD, number(8), number(3));

        assertEquals(11, ((IntValue) expression.eval(emptySymTable())).getVal());
    }

    @Test
    void subtractsIntegers() throws Exception {
        IExp expression = new ArithExp(ArithOp.SUB, number(8), number(3));

        assertEquals(5, ((IntValue) expression.eval(emptySymTable())).getVal());
    }

    @Test
    void multipliesIntegers() throws Exception {
        IExp expression = new ArithExp(ArithOp.MUL, number(8), number(3));

        assertEquals(24, ((IntValue) expression.eval(emptySymTable())).getVal());
    }

    @Test
    void dividesIntegers() throws Exception {
        IExp expression = new ArithExp(ArithOp.DIV, number(8), number(2));

        assertEquals(4, ((IntValue) expression.eval(emptySymTable())).getVal());
    }

    @Test
    void divisionByZeroThrows() {
        IExp expression = new ArithExp(ArithOp.DIV, number(8), number(0));

        assertThrows(
                ExpressionException.class,
                () -> expression.eval(emptySymTable())
        );
    }

    @Test
    void nonIntegerFirstOperandThrows() {
        IExp expression = new ArithExp(
                ArithOp.ADD,
                new ValueExp(new BoolValue(true)),
                number(1)
        );

        assertThrows(
                ExpressionException.class,
                () -> expression.eval(emptySymTable())
        );
    }

    @Test
    void nonIntegerSecondOperandThrows() {
        IExp expression = new ArithExp(
                ArithOp.ADD,
                number(1),
                new ValueExp(new BoolValue(true))
        );

        assertThrows(
                ExpressionException.class,
                () -> expression.eval(emptySymTable())
        );
    }
}