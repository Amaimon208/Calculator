package com.example.calculator.JCommanderClasses;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class OperationValidator implements IParameterValidator {

    @Override
    public void validate(String name, String text) throws ParameterException {
        if (text == null) {
            throw new ParameterException("You cannot create empty operation.");
        }
        char operator = text.charAt(text.length() - 1);
        if (operator == '*' || operator == ':' || operator == '/' || operator == '+' || operator == '-') {
            throw new ParameterException("You cannot leaf operator at the end of operation.");
        }
        if (text.matches("[0-9]+")) {
            throw new ParameterException("Inappropriate value in operation");
        }
    }
}
