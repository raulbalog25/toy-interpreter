package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;

public class PrintStmt implements IStmt {
    private final IExp expression;

    public PrintStmt(IExp expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        state.getOut().add(expression.eval(state.getSymTable()));
        return state;
    }

    @Override
    public String toString() {
        return "print(" + expression + ")";
    }
}