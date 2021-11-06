package com.pabin.kamil.mineswepper.domain;

final class UnfoldCommand implements Command {

    private final Coordinates coordinates;

    UnfoldCommand(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void execute(MineField mineField) {
        mineField.unfold(coordinates);
    }
}
