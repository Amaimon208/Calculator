package com.example.calculator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.example.calculator.Exceptions.DivisionByZeroException;
import com.example.calculator.JCommanderClasses.OperationParameters;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static com.example.calculator.History.*;

@SpringBootApplication
public class CalculatorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }

    private OperationParameters parameters = new OperationParameters();
    private JCommander jCommander;
    private String[] params;

    @Override
    public void run(String... args) {
        Scanner scan = new Scanner(System.in);
        jCommander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        jCommander.setProgramName("calculator");
        welcomeMessage();
        while (true) {
            commandsScanner(scan);
            handleInput(parameters, jCommander);
        }
    }

    public static void welcomeMessage() {
        System.out.println("Welcome in console calculator app!\n" +
                "To produce a list of available commands type:\n" +
                "-h or --help");
    }

    private void commandsScanner(Scanner scan) {
        jCommander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        params = scan.nextLine().split(" ");
        try {
            jCommander.parse(params);
        } catch (ParameterException e) {
            System.out.println("Given command was inappropriet, type --help to get list of operators.");
        }
    }

    private void handleInput(OperationParameters parameters, JCommander jCommander) {
        if (parameters.isExit()) {
            System.exit(0);
        } else if (parameters.isHelp()) {
            jCommander.usage();
        } else if (parameters.isHistory()) {
            printArchivedOperation();
        } else if (parameters.isDetails()) {
            printDetailedOperation();
        } else {
            try {
                parameters.runOperation();
            } catch (IllegalStateException e) {
                System.out.println("Inappropriate expression, type --help to get list of operators.");
            } catch (DivisionByZeroException e) {
                System.out.println(e.getMessage());
            }
        }
        parameters.reset();
    }
}


