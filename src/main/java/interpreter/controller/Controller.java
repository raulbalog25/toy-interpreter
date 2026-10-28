package interpreter.controller;

import interpreter.exceptions.MyException;
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

    public PrgState oneStep() throws MyException {
        PrgState state = repository.getPrgState();

        if (state.getExeStack().isEmpty()) {
            throw new MyException("Execution stack is empty");
        }

        IStmt statement = state.getExeStack().pop();
        PrgState updatedState = statement.execute(state);
        repository.setPrgState(updatedState);

        return updatedState;
    }

    public void allStep() throws MyException {
        PrgState state = repository.getPrgState();

        while (!state.getExeStack().isEmpty()) {
            if (displayFlag) {
                System.out.println(state);
            }

            state = oneStep();
        }

        if (displayFlag) {
            System.out.println(state);
        }
    }
}