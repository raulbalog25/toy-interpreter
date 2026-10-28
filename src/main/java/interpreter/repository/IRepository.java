package interpreter.repository;

import interpreter.model.PrgState;

public interface IRepository {
    PrgState getPrgState();

    void setPrgState(PrgState state);
}