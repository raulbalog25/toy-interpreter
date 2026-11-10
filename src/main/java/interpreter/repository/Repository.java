package interpreter.repository;

import interpreter.exceptions.MyException;
import interpreter.exceptions.RepositoryException;
import interpreter.model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Pastreaza starea programului si scrie starile in fisierul log.
public class Repository implements IRepository {
    private final List<PrgState> programStates = new ArrayList<>();
    private final String logFilePath;

    public Repository(PrgState programState, String logFilePath) {
        programStates.add(programState);
        this.logFilePath = logFilePath;
    }

    @Override
    public PrgState getCrtPrg() {
        return programStates.get(0);
    }

    @Override
    public void logPrgStateExec() throws MyException {
        try (PrintWriter logFile = new PrintWriter(
                new BufferedWriter(new FileWriter(logFilePath, true))
        )) {
            logFile.println(getCrtPrg().toLogString());
        } catch (IOException e) {
            throw new RepositoryException(
                    "cannot write the log file " + logFilePath + ": " + e.getMessage()
            );
        }
    }
}