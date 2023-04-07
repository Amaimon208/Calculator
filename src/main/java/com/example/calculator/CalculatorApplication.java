package com.example.calculator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static com.example.calculator.Calculator.printArchivedOperation;

@SpringBootApplication
public class CalculatorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }

    private Parameters parameters = new Parameters();
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
            System.out.println(e.getMessage());
        }
    }

    private void handleInput(Parameters parameters, JCommander jCommander) {
        if (parameters.isExit()) {
            System.exit(0);
        } else if (parameters.isHelp()) {
            jCommander.usage();
        } else if (parameters.isHistory()) {
            printArchivedOperation();
        } else {
            try {
                parameters.calculate();
            } catch (IllegalStateException e) {
                System.out.println("Inappropriate expression, type --help to get list of operators.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        parameters.reset();
    }
}


