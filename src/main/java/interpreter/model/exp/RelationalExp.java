package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;

public class RelationalExp implements IExp {
    private final RelOp op;
    private final IExp left;
    private final IExp right;

    public RelationalExp(RelOp op, IExp left, IExp right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {
        IValue leftValue = left.eval(symTable);

        if (!(leftValue instanceof IntValue first)) {
            throw new ExpressionException("first operand is not an integer");
        }

        IValue rightValue = right.eval(symTable);

        if (!(rightValue instanceof IntValue second)) {
            throw new ExpressionException("second operand is not an integer");
        }

        return new BoolValue(switch (op) {
            case LT -> first.getVal() < second.getVal();
            case LE -> first.getVal() <= second.getVal();
            case EQ -> first.getVal() == second.getVal();
            case NE -> first.getVal() != second.getVal();
            case GT -> first.getVal() > second.getVal();
            case GE -> first.getVal() >= second.getVal();
        });
    }

    @Override
    public String toString() {
        return "(" + left + " " + op + " " + right + ")";
    }
}