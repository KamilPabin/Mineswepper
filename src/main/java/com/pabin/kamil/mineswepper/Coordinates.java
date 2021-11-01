package com.pabin.kamil.mineswepper;

import static java.lang.String.format;

final class Coordinates {
    public final int x;
    public final int y;

    Coordinates(int x, int y) {
        validateCoordinate(x);
        validateCoordinate(y);
        this.x = x;
        this.y = y;
    }

    static Coordinates of(int x, int y) {
        return new Coordinates(x,y);
    }

    private void validateCoordinate(int coordinate) {
        if (coordinate < 0) {
            throw new IllegalArgumentException(format("Coordinate cannot be smaller than 0: %d", coordinate));
        }
    }
}
