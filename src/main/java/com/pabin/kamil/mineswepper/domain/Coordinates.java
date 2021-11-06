package com.pabin.kamil.mineswepper.domain;

import java.util.Objects;

import static java.lang.String.format;

public final class Coordinates {
    public final int x;
    public final int y;

    private Coordinates(int x, int y) {
        validateCoordinate(x);
        validateCoordinate(y);
        this.x = x;
        this.y = y;
    }

    public static Coordinates of(int x, int y) {
        return new Coordinates(x,y);
    }

    private void validateCoordinate(int coordinate) {
        if (coordinate < 0) {
            throw new IllegalArgumentException(format("Coordinate cannot be smaller than 0: %d", coordinate));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
