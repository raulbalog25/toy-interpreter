package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;

public class LogicExp implements IExp {
    private final LogicOp op;
    private final IExp left;
    private final IExp right;

    public LogicExp(LogicOp op, IExp left, IExp right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {
        IValue leftValue = left.eval(symTable);
        IValue rightValue = right.eval(symTable);

        if (!(leftValue instanceof BoolValue first)) {
            throw new ExpressionException("first operand is not a boolean");
        }
        if (!(rightValue instanceof BoolValue second)) {
            throw new ExpressionException("second operand is not a boolean");
        }

        return switch (op) {
            case AND -> new BoolValue(first.getVal() && second.getVal());
            case OR -> new BoolValue(first.getVal() || second.getVal());
        };
    }

    @Override
    public String toString() {
        return "(" + left + " " + op + " " + right + ")";
    }
}