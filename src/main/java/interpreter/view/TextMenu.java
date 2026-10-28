package interpreter.view;

import interpreter.controller.Controller;
import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.stmt.IStmt;
import interpreter.repository.Repository;

import java.util.List;
import java.util.Scanner;

public class TextMenu {
    private final Scanner scanner = new Scanner(System.in);
    private IStmt selectedProgram;
    private String selectedDescription;

    public void start() {
        while (true) {
            System.out.println();
            System.out.println("1. Select a program");
            System.out.println("2. Run the selected program");
            System.out.println("0. Exit");
            System.out.print("> ");

            if (!scanner.hasNextLine()) {
                return;
            }

            String option = scanner.nextLine().trim();

            switch (option) {
                case "1" -> selectProgram();
                case "2" -> runSelectedProgram();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid menu option.");
            }
        }
    }

    private void selectProgram() {
        List<IStmt> programs = Examples.getExamples();
        List<String> descriptions = Examples.getDescriptions();

        System.out.println("Choose a program:");
        for (int i = 0; i < descriptions.size(); i++) {
            System.out.println((i + 1) + ". " + descriptions.get(i));
        }
        System.out.print("> ");

        String input = scanner.nextLine().trim();

        try {
            int choice = Integer.parseInt(input);

            if (choice < 1 || choice > programs.size()) {
                System.out.println("There is no program with that number.");
                return;
            }

            selectedProgram = programs.get(choice - 1);
            selectedDescription = descriptions.get(choice - 1);
            System.out.println("Selected: " + selectedDescription);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        }
    }

    private void runSelectedProgram() {
        if (selectedProgram == null) {
            System.out.println("Select a program first.");
            return;
        }

        System.out.println("Running: " + selectedDescription);

        // Se creează o stare nouă la fiecare rulare.
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                selectedProgram
        );

        Controller controller = new Controller(new Repository(state), true);

        try {
            controller.allStep();
            System.out.println("Output: " + state.getOut());
        } catch (MyException e) {
            System.out.println("Interpreter error: " + e.getMessage());
        }
    }
}