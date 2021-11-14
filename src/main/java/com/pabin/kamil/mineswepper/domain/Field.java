package com.pabin.kamil.mineswepper.domain;

import static com.pabin.kamil.mineswepper.domain.FieldState.*;

public final class Field {
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

    static Field mine() {
        return new Field(-1);
    }

    static Field empty() {
        return new Field(0);
    }


    public boolean containsMine() {
        return value == -1;
    }

    public boolean isUnfolded() {
        return fieldState == UNFOLDED;
    }

    public int value() {
        return value;
    }

    public boolean isMarkedUnsafe() {
        return fieldState == UNSAFE;
    }

    public boolean isHidden() {
        return fieldState == HIDDEN;
    }

    boolean isEmpty() {
        return value == 0;
    }

    Field incrementMinesAroundCount() {
        if (!containsMine()) {
            return new Field(this.value + 1);
        }
        return this;
    }

    Field unfold() {
        if (fieldState != HIDDEN) {
            return this;
        }
        return new Field(value, FieldState.UNFOLDED);
    }

    Field markedAsUnsafe() {
        if (fieldState != HIDDEN) {
            return this;
        }
        return new Field(value, UNSAFE);
    }

    Field markedAsSafe() {
        if (fieldState != UNSAFE) {
            return this;
        }
        return new Field(value, HIDDEN);
    }

    @Override
    public String toString() {
        if (fieldState == HIDDEN) {
            return " ";
        }
        if (fieldState == UNSAFE) {
            return "🚩";
        }
        return value == -1 ? "*" : Integer.toString(value);
    }
}
