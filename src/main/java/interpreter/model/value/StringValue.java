package interpreter.model.value;

import interpreter.model.type.IType;
import interpreter.model.type.StringType;

public class StringValue implements IValue {
    private final String val;

    public StringValue(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public String toString() {
        return "\"" + val + "\"";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StringValue other)) {
            return false;
        }
        return val.equals(other.val);
    }

    @Override
    public int hashCode() {
        return val.hashCode();
    }
}