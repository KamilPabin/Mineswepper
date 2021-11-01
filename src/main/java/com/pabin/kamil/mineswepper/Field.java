package com.pabin.kamil.mineswepper;

import static com.pabin.kamil.mineswepper.FieldState.HIDDEN;

final class Field {
    private final int value;
    private final FieldState fieldState;

    Field(int value) {
        this(value, HIDDEN);
    }

    private Field(int value, FieldState fieldState) {
        if (!(value < 9 && value > -2)) {
            throw new IllegalArgumentException(String.format("Field has incorrect value %d", value));
        }
        this.value = value;
        this.fieldState = fieldState;
    }

    boolean isMine() {
        return value == -1;
    }

     Field countedMinesAround() {
        if(!isMine()) {
            return new Field(this.value + 1);
        }
        return this;
    }

    Field unfold() {
        return new Field(value, FieldState.UNFOLDED);
    }

    @Override
    public String toString() {
        if (fieldState == HIDDEN) {
            return " ";
        }
        return value == -1 ? "*" : Integer.toString(value);
    }

    static Field mine() {
        return new Field(-1);
    }

    static Field empty() {
        return new Field(0);
    }
}
