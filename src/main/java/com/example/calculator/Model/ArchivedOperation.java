package com.example.calculator.Model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ArchivedOperation {
    private static BigInteger idCounter
            = BigInteger.ONE;

    private final BigInteger id;
    private final Operation operation;
    private final List<String> steps;
    private final Double result;

    public ArchivedOperation(Operation operation, List<String> steps) {
        this.id = idCounter;
        this.operation = operation;
        this.steps = steps;
        if(steps.size()>0){
            this.result = Double.valueOf(steps.get(steps.size()-1));
        }else{
            this.result=0.;
        }
        idCounter = idCounter.add(BigInteger.ONE);
    }

    @Override
    public String toString() {
        String operationString = id + ". \t";
        operationString += operation.toString();
        operationString += " = " + result.toString();
        return operationString;
    }

    public String detailedToString() {
        String operationString = id + ". \t";
        operationString += operation.toString()+"\n\t";
        for(String step: steps){
            operationString += step+"\n\t";
        }
        return operationString;
    }
}
