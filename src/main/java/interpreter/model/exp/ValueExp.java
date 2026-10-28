package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.IValue;

public class ValueExp implements IExp {
    private final IValue value;

    public ValueExp(IValue value) {
        this.value = value;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}