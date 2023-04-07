package com.example.calculator.JCommanderClasses;

import com.beust.jcommander.Parameter;
import com.example.calculator.Exceptions.DivisionByZeroException;
import com.example.calculator.Model.Operation;

import static com.example.calculator.History.executeOperation;

public class OperationParameters {
    @Parameter(
            names = {"-h", "--help", "help"},
            help = true,
            order = 0,
            description = "Display help information."
    )
    private boolean help;

    @Parameter(
            names = {"-d", "--details", "details"},
            order = 1,
            description = "Show detailed history of preformed operations."
    )
    private boolean details;

    @Parameter(
            names = {"-his", "--history", "history"},
            order = 2,
            description = "Show history of preformed operations."
    )
    private boolean history;

    @Parameter(
            names = {"-o", "--operation","operation"},
            order = 3,
            validateWith = OperationValidator.class,
            converter = OperationConverter.class,
            description = "Arithmetic operation declared without spaces and using only basic arithmetic operation."
    )
    private Operation operation;


    @Parameter(
            names = {"-e", "--exit", "--end", "exit", "end"},
            order = 4,
            description = "Exit the application."
    )
    private boolean exit;

    public void runOperation() throws DivisionByZeroException {
        executeOperation(operation);
    }

    public boolean isHelp() {
        return help;
    }

    public boolean isHistory() {
        return history;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isDetails() {
        return details;
    }

    public void reset() {
        help = false;
        history = false;
        details = false;
        exit = false;
        operation = new Operation();
    }
}
