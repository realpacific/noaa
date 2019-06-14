package com.realpacific.projectnoaa.readers;

import java.util.Scanner;

public class ConsoleReader implements Reader<String> {

    @Override
    public String read(String displayMessage) {
        Scanner scanner = new Scanner(System.in);
        if (displayMessage != null)
            System.out.print(displayMessage);
        return scanner.nextLine();
    }

}
