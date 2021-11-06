package com.pabin.kamil.mineswepper.infrastructure;

import com.pabin.kamil.mineswepper.domain.Command;
import com.pabin.kamil.mineswepper.domain.CommandFactory;
import com.pabin.kamil.mineswepper.domain.Coordinates;
import com.pabin.kamil.mineswepper.domain.InputHandler;

import java.util.Scanner;

public final class ConsoleInputHandler implements InputHandler {

    private final Scanner scanner;
    private final CommandFactory commandFactory;

    public ConsoleInputHandler(Scanner scanner, CommandFactory commandFactory) {
        this.scanner = scanner;
        this.commandFactory = commandFactory;
    }

    @Override
    public Command readCommand() {
        String command = scanner.nextLine();
        String[] values = command.split(" ");
        if (values.length < 3) {
            return commandFactory.noOpCommand();
        }
        int x = Integer.parseInt(values[1]);
        int y = Integer.parseInt(values[2]);
        Coordinates coordinates = Coordinates.of(x, y);
        return commandFactory.fromString(values[0], coordinates);
    }
}
