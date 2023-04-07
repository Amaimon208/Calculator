package com.example.calculator;

import com.beust.jcommander.Parameter;
import com.example.calculator.Exceptions.DivisionByZeroException;
import com.example.calculator.Exceptions.ToLittleOperandsException;
import com.example.calculator.Exceptions.UnknownOperationException;

import java.util.ArrayList;
import java.util.List;

import static com.example.calculator.Calculator.convertToOperation;
import static com.example.calculator.Calculator.preformOperation;

public class Parameters {

    @Parameter(
            names = {"-h", "--help", "help"},
            help = true,
            order = 0,
            description = "Display help information."
    )
    private boolean help;

    @Parameter(
            names = {"-his", "--history", "history"},
            order = 1,
            description = "Show history of preformed operations."
    )
    private boolean history;

    @Parameter(
            names = {"-o", "--operator", "operator"},
            order = 2,
            // required = true,
            description = "Specify operation from list:\n" +
                    "\t  Addition: + or add or addition\n" +
                    "\t  Subtraction: - or sub or subtraction\n" +
                    "\t  Multiplication: * or mul or multiplication\n" +
                    "\t  Division: / or : or div or division\n"
    )
    private String operationString;

    @Parameter(
            names = {"-p", "--parameters", "--operands", "parameters", "operands"},
            order = 3,
            // required = true,
            description = "List of operands, must contain at least 2 elements."
    )
    private List<Double> parameters;

    @Parameter(
            names = {"-e", "--exit", "--end", "exit", "end"},
            order = 4,
            description = "Exit the application."
    )
    private boolean exit;

    public void calculate() throws ToLittleOperandsException, DivisionByZeroException, UnknownOperationException {
        char operation = convertToOperation(operationString);
        if (parameters != null) {
            if (parameters.size() > 1) {
                preformOperation(operation, parameters);
            } else {
                throw new ToLittleOperandsException("You need to specify at least 2 operands to preform calculation.");
            }
        } else {
            throw new ToLittleOperandsException("You need to specify at least 2 operands to preform calculation.");
        }
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

    public void reset() {
        help = false;
        history = false;
        exit = false;
        operationString = "";
        parameters = new ArrayList();
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "help=" + help +
                ", history=" + history +
                ", exit=" + exit +
                ", operationString='" + operationString + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
