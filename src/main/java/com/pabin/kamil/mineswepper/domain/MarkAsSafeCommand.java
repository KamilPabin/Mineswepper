package com.pabin.kamil.mineswepper.domain;

final class MarkAsSafeCommand implements Command {

    private final Coordinates coordinates;

    MarkAsSafeCommand(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void execute(MineField mineField) {
        mineField.markFieldSafe(coordinates);
    }
}
