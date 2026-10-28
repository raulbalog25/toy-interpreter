package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;

public class ArithExp implements IExp {
    private final ArithOp op;
    private final IExp left;
    private final IExp right;

    public ArithExp(ArithOp op, IExp left, IExp right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {
        IValue leftValue = left.eval(symTable);
        IValue rightValue = right.eval(symTable);

        if (!(leftValue instanceof IntValue first)) {
            throw new ExpressionException("first operand is not an integer");
        }
        if (!(rightValue instanceof IntValue second)) {
            throw new ExpressionException("second operand is not an integer");
        }

        return switch (op) {
            case ADD -> new IntValue(first.getVal() + second.getVal());
            case SUB -> new IntValue(first.getVal() - second.getVal());
            case MUL -> new IntValue(first.getVal() * second.getVal());
            case DIV -> {
                if (second.getVal() == 0) {
                    throw new ExpressionException("division by zero");
                }
                yield new IntValue(first.getVal() / second.getVal());
            }
        };
    }

    @Override
    public String toString() {
        return "(" + left + " " + op + " " + right + ")";
    }
}