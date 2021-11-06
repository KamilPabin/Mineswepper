package com.pabin.kamil.mineswepper.domain;

public final class MarkAsSafeCommand implements Command {

    private final Coordinates coordinates;

    public MarkAsSafeCommand(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void execute(MineField mineField) {
        mineField.markFieldSafe(coordinates);
    }
}
