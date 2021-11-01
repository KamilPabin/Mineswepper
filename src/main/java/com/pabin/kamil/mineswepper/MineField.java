package com.pabin.kamil.mineswepper;

import java.util.Arrays;
import java.util.stream.Collectors;

final class MineField {

    private final Field[][] minefield;

    MineField(Field[][] minefield) {
        this.minefield = minefield;
    }

    void unfold(Coordinates coordinates) {
        minefield[coordinates.x][coordinates.y] = minefield[coordinates.x][coordinates.y].unfold();
    }

    @Override
    public String toString() {
        return Arrays.stream(minefield).map(Arrays::toString).collect(Collectors.joining("\n"));
    }
}
