package com.example.calculator.Model;

import java.math.BigInteger;
import java.util.List;

public class ArchivedOperation {
    private static BigInteger idCounter
            = BigInteger.ONE;

    private final BigInteger id;
    private final char operator;
    private final List<Double> operands;
    private final Double result;

    public ArchivedOperation(char operator, List<Double> operands, Double result) {
        this.id = idCounter;
        this.operator = operator;
        this.operands = operands;
        this.result = result;
        idCounter = idCounter.add(BigInteger.ONE);
    }

    @Override
    public String toString() {
        String operationString = id + ". \t";
        ;
        operationString += operands.get(0).toString() + " ";
        for (int i = 1; i < operands.size(); i++) {
            operationString += operator + " ";
            operationString += operands.get(i).toString() + " ";
        }
        operationString += "= " + result.toString();
        return operationString;
    }
}
