package com.pabin.kamil.mineswepper;

import com.pabin.kamil.mineswepper.domain.*;
import com.pabin.kamil.mineswepper.infrastructure.ConsoleDisplay;
import com.pabin.kamil.mineswepper.infrastructure.ConsoleInputHandler;

import java.security.SecureRandom;
import java.util.Scanner;

public final class Main {

    public static void main(String[] args) {
        int size = 10;
        int numberOfMines = 10;
        SecureRandom generator = new SecureRandom();
        CommandFactory commandFactory = new CommandFactory();
        Scanner scanner = new Scanner(System.in);

        Display display = new ConsoleDisplay();
        InputHandler handler = new ConsoleInputHandler(scanner, commandFactory);

        MineFieldFactory factory = new MineFieldFactory(generator);
        MineField mineField = factory.create(size, numberOfMines);

        while (true) {
            Command command = handler.readCommand();
            command.execute(mineField);
            display.show(mineField.view());
            System.out.println(mineField.view());
        }
    }
}

