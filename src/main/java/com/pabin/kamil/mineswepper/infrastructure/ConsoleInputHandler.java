package com.pabin.kamil.mineswepper.infrastructure;

import com.pabin.kamil.mineswepper.domain.*;

import java.util.Scanner;

public final class ConsoleInputHandler implements InputHandler {

    private final Scanner scanner;

    public ConsoleInputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Command readCommand() {
        String command = scanner.nextLine();
        String[] values = command.split(" ");
        if (values.length < 3) {
            return NoOpCommand.INSTANCE;
        }
        int x = Integer.parseInt(values[1]);
        int y = Integer.parseInt(values[2]);
        Coordinates coordinates = Coordinates.of(x, y);
        return switch (values[0]) {
            case "unfold" -> new UnfoldCommand(coordinates);
            case "unsafe" -> new MarkAsUnsafeCommand(coordinates);
            case "safe" -> new MarkAsSafeCommand(coordinates);
            default -> NoOpCommand.INSTANCE;
        };
    }
}
