package com.example.calculator.Model;

import com.example.calculator.Exceptions.DivisionByZeroException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class Operation {
    private String operatorsList;
    private List<Double> operandsList;

    public Operation() {
        this.operatorsList = null;
        this.operandsList = null;
    }

    public Operation(String operatorsList, List<Double> operandsList) {
        this.operatorsList = operatorsList;
        this.operandsList = operandsList;
    }

    public List<String> calculate() throws DivisionByZeroException {
        String savedOperators = operatorsList;
        List<Double> savedOperands = new ArrayList<>();
        List<String> steps = new ArrayList<>();
        if (operandsList != null && operandsList != null) {
            for (int i = 0; i < operandsList.size(); i++) {
                savedOperands.add(operandsList.get(i));
            }
            List<String> stepsMul = updateMul();
            for (String step : stepsMul) {
                steps.add(step);
            }
            List<String> stepsDiv = updateDiv();
            for (String step : stepsDiv) {
                steps.add(step);
            }
            List<String> stepsAdd = updateAdd();
            for (String step : stepsAdd) {
                steps.add(step);
            }
            List<String> stepsSub = updateSub();
            for (String step : stepsSub) {
                steps.add(step);
            }
            steps.add(String.valueOf(operandsList.get(0)));
            this.operatorsList = savedOperators;
            this.operandsList = savedOperands;
        }
        return steps;
    }

    private List<String> updateMul() {
        List<String> steps = new ArrayList<>();
        int indexOfClosestOperation = operatorsList.indexOf("*");
        while (indexOfClosestOperation != -1) {
            Double result = mul(List.of(operandsList.get(indexOfClosestOperation), operandsList.get(indexOfClosestOperation + 1)));
            steps.add(createStep(indexOfClosestOperation, '*', result));
            updateOperation(indexOfClosestOperation, result);
            indexOfClosestOperation = operatorsList.indexOf("*");
        }
        return steps;
    }

    private List<String> updateDiv() throws DivisionByZeroException {
        List<String> steps = new ArrayList<>();
        int indexOfClosestOperation;
        indexOfClosestOperation = indexOfClosesDiv();
        while (indexOfClosestOperation != -1) {
            Double result = div(List.of(operandsList.get(indexOfClosestOperation),
                    operandsList.get(indexOfClosestOperation + 1)));
            steps.add(createStep(indexOfClosestOperation, '/', result));
            updateOperation(indexOfClosestOperation, result);
            indexOfClosestOperation = indexOfClosesDiv();
        }
        return steps;
    }

    private int indexOfClosesDiv() {
        int indexOfClosestOperation;
        int indexOfFirstOperation = operatorsList.indexOf("/");
        int indexOfSecondOperation = operatorsList.indexOf(":");
        min(indexOfFirstOperation, indexOfSecondOperation);
        if (indexOfFirstOperation == -1) {
            indexOfClosestOperation = indexOfSecondOperation;
        } else if (indexOfSecondOperation == -1) {
            indexOfClosestOperation = indexOfFirstOperation;
        } else {
            indexOfClosestOperation = Math.min(indexOfFirstOperation, indexOfSecondOperation);
        }
        return indexOfClosestOperation;
    }

    private List<String> updateAdd() {
        List<String> steps = new ArrayList<>();
        int indexOfClosestOperation = operatorsList.indexOf("+");
        while (indexOfClosestOperation != -1) {
            Double result = add(List.of(operandsList.get(indexOfClosestOperation),
                    operandsList.get(indexOfClosestOperation + 1)));
            steps.add(createStep(indexOfClosestOperation, '+', result));
            updateOperation(indexOfClosestOperation, result);
            indexOfClosestOperation = operatorsList.indexOf("+");
        }
        return steps;
    }

    private List<String> updateSub() {
        List<String> steps = new ArrayList<>();
        int indexOfClosestOperation = operatorsList.indexOf("-");
        while (indexOfClosestOperation != -1) {
            Double result = sub(List.of(operandsList.get(indexOfClosestOperation),
                    operandsList.get(indexOfClosestOperation + 1)));
            steps.add(createStep(indexOfClosestOperation, '-', result));
            updateOperation(indexOfClosestOperation, result);
            indexOfClosestOperation = operatorsList.indexOf("-");
        }
        return steps;
    }

    private String createStep(int index, char sign, Double result) {
        return operandsList.get(index) + " "
                + sign + " " +
                operandsList.get(index + 1) + " = " +
                result;
    }

    private void updateOperation(int indexOfOperation, Double result) {
        operatorsList = operatorsList.substring(0, indexOfOperation) + operatorsList.substring(indexOfOperation + 1);
        operandsList.remove(indexOfOperation + 1);
        operandsList.remove(indexOfOperation);
        operandsList.add(indexOfOperation, result);
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

    @Override
    public String toString() {
        if (operatorsList != null && operandsList != null) {
            return getArithmeticModel(operatorsList, operandsList);
        } else {
            return "Empty operation.";
        }
    }

    private String getArithmeticModel(String operators, List<Double> operands) {
        String operationString = "";
        if (operands != null) {
            for (int i = 0; i < operands.size() - 1; i++) {
                operationString += operands.get(i)
                        + " " + operators.charAt(i) + " ";
            }
            operationString += operands.get(operands.size() - 1);
            return operationString;
        }
        return "Empty operation.";
    }
}