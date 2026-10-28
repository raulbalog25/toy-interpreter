package interpreter.controller;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.stmt.IStmt;
import interpreter.repository.IRepository;

public class Controller {
    private final IRepository repository;
    private final boolean displayFlag;

    public Controller(IRepository repository, boolean displayFlag) {
        this.repository = repository;
        this.displayFlag = displayFlag;
    }

    public PrgState oneStep(PrgState state) throws MyException {
        if (state.getExeStack().isEmpty()) {
            throw new StatementException("program state stack is empty");
        }

        IStmt statement = state.getExeStack().pop();
        return statement.execute(state);
    }

    public void allStep() throws MyException {
        PrgState state = repository.getCrtPrg();
        display(state);

        while (!state.getExeStack().isEmpty()) {
            oneStep(state);
            display(state);
        }
    }

    private void display(PrgState state) {
        if (displayFlag) {
            System.out.println(state);
        }
    }
}