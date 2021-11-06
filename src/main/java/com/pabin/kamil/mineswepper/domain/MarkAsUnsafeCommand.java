package com.pabin.kamil.mineswepper.domain;

public final class MarkAsUnsafeCommand implements Command {

    private final Coordinates coordinates;

    public MarkAsUnsafeCommand(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void execute(MineField mineField) {
        mineField.markAsUnsafe(coordinates);
    }
}
