package com.pabin.kamil.mineswepper;

import com.pabin.kamil.mineswepper.domain.*;
import com.pabin.kamil.mineswepper.infrastructure.ConsoleDisplay;
import com.pabin.kamil.mineswepper.infrastructure.ConsoleInputHandler;

import java.security.SecureRandom;
import java.util.Scanner;

public final class Main {

    public static void main(String[] args) {
        int size = 8;
        int numberOfMines = 10;
        SecureRandom generator = new SecureRandom();
        Display display = new ConsoleDisplay();
        InputHandler handler = new ConsoleInputHandler(new Scanner(System.in), new CommandFactory());

        MineFieldFactory factory = new MineFieldFactory(generator);
        MineField mineField = factory.create(size, numberOfMines);

        while (true) {
            Command command = handler.readCommand();
            command.execute(mineField);
            display.display(mineField.view());
        }

    }
}

