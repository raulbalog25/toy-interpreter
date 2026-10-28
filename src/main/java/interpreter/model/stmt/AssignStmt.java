package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.IValue;

public class AssignStmt implements IStmt {
    private final String name;
    private final IExp expression;

    public AssignStmt(String name, IExp expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if (!state.getSymTable().isDefined(name)) {
            throw new StatementException("Variable " + name + " is not declared");
        }

        IValue value = expression.eval(state.getSymTable());
        IValue oldValue = state.getSymTable().lookup(name);

        if (!oldValue.getType().equals(value.getType())) {
            throw new StatementException("The value has a different type than variable " + name);
        }

        state.getSymTable().update(name, value);
        return state;
    }

    @Override
    public String toString() {
        return name + " = " + expression;
    }
}