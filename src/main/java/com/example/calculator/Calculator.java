package com.example.calculator;

import com.example.calculator.Exceptions.DivisionByZeroException;
import com.example.calculator.Exceptions.UnknownOperationException;
import com.example.calculator.Model.ArchivedOperation;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private static List<ArchivedOperation> history = new ArrayList<>();

    public static void preformOperation(char operator, List<Double> params) throws DivisionByZeroException {
        Double result = calculateResult(operator, params);
        history.add(new ArchivedOperation(operator, params, result));
        printCurrentOperation();
    }

    public static void printCurrentOperation() {
        System.out.println(history.get(history.size() - 1));
    }

    public static void printArchivedOperation() {
        if (history.size() == 0) {
            System.out.println("Your history is empty.");
        } else {
            for (ArchivedOperation operation : history) {
                System.out.println(operation);
            }
        }
    }

    public static char convertToOperation(String operationString) throws UnknownOperationException {
        char operationChar = ' ';
        if (operationString != null) {
            switch (operationString) {
                case "+":
                case "add":
                case "addition":
                    operationChar = '+';
                    break;
                case "-":
                case "sub":
                case "subtraction":
                    operationChar = '-';
                    break;
                case "*":
                case "mul":
                case "multiplication":
                    operationChar = '*';
                    break;
                case "/":
                case ":":
                case "div":
                case "division":
                    operationChar = '/';
                    break;
                default:
                    throw new UnknownOperationException("Unknown operator, type --help to get list of operators.");
            }
            return operationChar;
        }
        throw new UnknownOperationException("You need to declare operator, type --help to get list of operators.");

    }

    private static Double calculateResult(char operator, List<Double> params) throws DivisionByZeroException {
        Double result = 0.;
        switch (operator) {
            case '+':
                result = add(params);
                break;
            case '-':
                result = sub(params);
                break;
            case '*':
                result = mul(params);
                break;
            case '/':
                result = div(params);
                break;
        }
        return result;
    }

    private static double add(List<Double> params) {
        double result = params.get(0);
        for (int i = 1; i < params.size(); i++) {
            result += params.get(i);
        }
        return result;
    }

    private static double sub(List<Double> params) {
        double result = params.get(0);
        for (int i = 1; i < params.size(); i++) {
            result -= params.get(i);
        }
        return result;
    }

    private static double mul(List<Double> params) {
        double result = params.get(0);
        for (int i = 1; i < params.size(); i++) {
            result *= params.get(i);
        }
        return result;
    }

    private static double div(List<Double> params) throws DivisionByZeroException {
        double result = params.get(0);
        for (int i = 1; i < params.size(); i++) {
            Double divider = params.get(i);
            if (divider == 0) {
                throw new DivisionByZeroException("You can't divide by zero!!!");
            } else {
                result /= params.get(i);
            }
        }
        return result;
    }
}
