package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;

public class IfStmt implements IStmt {
    private final IExp condition;
    private final IStmt thenStatement;
    private final IStmt elseStatement;

    public IfStmt(IExp condition, IStmt thenStatement, IStmt elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        IValue value = condition.eval(state.getSymTable());

        if (!(value instanceof BoolValue)) {
            throw new StatementException("The condition of if must be boolean");
        }

        if (((BoolValue) value).getVal()) {
            state.getExeStack().push(thenStatement);
        } else {
            state.getExeStack().push(elseStatement);
        }

        return state;
    }

    @Override
    public String toString() {
        return "if (" + condition + ") then (" + thenStatement
                + ") else (" + elseStatement + ")";
    }
}