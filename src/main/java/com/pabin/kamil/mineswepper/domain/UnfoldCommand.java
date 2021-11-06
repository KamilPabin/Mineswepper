package com.pabin.kamil.mineswepper.domain;

public final class UnfoldCommand implements Command {

    private final Coordinates coordinates;

    public UnfoldCommand(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void execute(MineField mineField) {
        mineField.unfold(coordinates);
    }
}
