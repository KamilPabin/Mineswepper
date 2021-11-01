package com.pabin.kamil.mineswepper;

final class Field {
    private final int value;

    Field(int value) {
        if (!(value < 9 && value > -2)) {
            throw new IllegalArgumentException(String.format("Field has incorrect value %d", value));
        }
        this.value = value;
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

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    static Field mine() {
        return new Field(-1);
    }

    static Field empty() {
        return new Field(0);
    }
}
