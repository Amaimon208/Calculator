package com.example.calculator.JCommanderClasses;

import com.beust.jcommander.IStringConverter;
import com.example.calculator.Model.Operation;

import java.util.ArrayList;
import java.util.List;

public class OperationConverter implements IStringConverter<Operation> {

    @Override
    public Operation convert(String s) {
        Operation operation;
        String[] numbersList = s.split("[*/:+-]");
        List<Double> doubleList = new ArrayList<>();
        for (String numberString : numbersList) {
            doubleList.add(Double.valueOf(numberString));
        }
        s.replaceAll("[0-9]", "");
        s.replaceAll(".", "");
        String charsList = s.replaceAll("[0-9]", "");
        charsList = charsList.replaceAll("\\.", "");
        operation = new Operation(charsList, doubleList);
        return operation;
    }

}
