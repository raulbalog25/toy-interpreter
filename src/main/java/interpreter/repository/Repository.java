package interpreter.repository;

import interpreter.model.PrgState;

public class Repository implements IRepository {
    private PrgState prgState;

    public Repository(PrgState prgState) {
        this.prgState = prgState;
    }

    @Override
    public PrgState getPrgState() {
        return prgState;
    }

    @Override
    public void setPrgState(PrgState state) {
        this.prgState = state;
    }
}