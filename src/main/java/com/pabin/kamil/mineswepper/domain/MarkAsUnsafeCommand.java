package com.pabin.kamil.mineswepper.domain;

final class MarkAsUnsafeCommand implements Command {

    private final Coordinates coordinates;

    MarkAsUnsafeCommand(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void execute(MineField mineField) {
        mineField.markAsUnsafe(coordinates);
    }
}
