package com.example.calculator;

import com.example.calculator.Exceptions.DivisionByZeroException;
import com.example.calculator.Model.ArchivedOperation;
import com.example.calculator.Model.Operation;

import java.util.ArrayList;
import java.util.List;

public class History {

    private static List<ArchivedOperation> historyList = new ArrayList();

    public static void executeOperation(Operation operation) throws DivisionByZeroException {
        if (operation != null) {
            List<String> steps = operation.calculate();
            if (steps != null) {
                historyList.add(new ArchivedOperation(operation, steps));
                printCurrentOperation();
            }
        }
    }

    public static void printCurrentOperation() {
        if (!historyList.isEmpty()) {
            System.out.println(historyList.get(historyList.size() - 1));
        }
    }

    public static void printDetailedCurrentOperation() {
        if (!historyList.isEmpty()) {
            System.out.println(historyList.get(historyList.size() - 1).detailedToString());
        }
    }

    public static void printDetailedOperation() {
        if (historyList.size() == 0) {
            System.out.println("Your history is empty.");
        } else {
            for (ArchivedOperation operation : historyList) {
                System.out.println(operation.detailedToString());
            }
        }
    }

    public static void printArchivedOperation() {
        if (historyList.size() == 0) {
            System.out.println("Your history is empty.");
        } else {
            for (ArchivedOperation operation : historyList) {
                System.out.println(operation);
            }
        }
    }
}
