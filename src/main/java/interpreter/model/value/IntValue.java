package interpreter.model.value;

import interpreter.model.type.IType;
import interpreter.model.type.IntType;

public class IntValue implements IValue {
    private final int val;

    public IntValue(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntValue other)) {
            return false;
        }
        return val == other.val;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(val);
    }
}