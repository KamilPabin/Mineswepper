package com.pabin.kamil.mineswepper.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class MineFieldView {
    private final Field[][] fields;

    MineFieldView(Field[][] fields) {
        this.fields = fields;
    }

    Field fieldAt(Coordinates coordinates) {
        return fields[coordinates.x][coordinates.y];
    }

    @Override
    public String toString() {
        return Arrays.stream(fields).map(Arrays::toString).collect(Collectors.joining("\n"));
    }
}
