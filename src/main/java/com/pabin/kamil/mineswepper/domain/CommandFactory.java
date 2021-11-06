package com.pabin.kamil.mineswepper.domain;

public final class CommandFactory {

    public Command fromString(String commandName, Coordinates coordinates) {
        return switch (commandName) {
            case "unfold" -> new UnfoldCommand(coordinates);
            case "unsafe" -> new MarkAsUnsafeCommand(coordinates);
            case "safe" -> new MarkAsSafeCommand(coordinates);
            default -> NoOpCommand.INSTANCE;
        };
    }

    public Command noOpCommand() {
        return NoOpCommand.INSTANCE;
    }
}
