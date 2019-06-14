package com.realpacific.projectnoaa.readers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultiInputConsoleReader implements Reader<List<String>> {
    private String[] inputName;

    public MultiInputConsoleReader(String... inputName) {
        this.inputName = inputName;
    }

    @Override
    public List<String> read(String displayMessage) {
        if (inputName.length < 1) {
            throw new RuntimeException("MultiInputConsoleReader must have at least one input argument.");
        }
        Scanner scanner = new Scanner(System.in);
        List<String> userInputs = new ArrayList<>();
        for (String s : inputName) {
            System.out.print("Enter value for " + s + ": ");
            userInputs.add(scanner.nextLine());
        }
        return userInputs;
    }
}
