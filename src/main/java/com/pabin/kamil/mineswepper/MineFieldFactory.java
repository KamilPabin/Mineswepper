package com.pabin.kamil.mineswepper;

import java.security.SecureRandom;

import static java.lang.String.format;

final class MineFieldFactory {

    private static final int MINIMUM_REQUIRED_SIZE = 2;

    private final SecureRandom generator;

    MineFieldFactory(SecureRandom generator) {
        this.generator = generator;
    }

    MineField create(int size, int mines) {
        if (size < MINIMUM_REQUIRED_SIZE) {
            throw new IllegalArgumentException(format("Minefield cannot be smaller than %d", MINIMUM_REQUIRED_SIZE));
        }

        if (size * size < mines) {
            throw new IllegalArgumentException(format("There cannot be more mines %d than fields in minefield %d", mines, size * size));
        }

        return new MineField(createField(size, mines));
    }

    private Field[][] createField(int size, int mines) {
        Field[][] mineField = emptyField(size);
        for (int i = 0; i < mines; i++) {
            Coordinates mineCoordinates = generateMineCoordinates(size, mineField);
            mineField[mineCoordinates.x][mineCoordinates.y] = Field.mine();
            markSurroundingsWithMineInformation(mineField, mineCoordinates);
        }
        return mineField;
    }

    private void markSurroundingsWithMineInformation(Field[][] mineField, Coordinates coordinates) {
        for (int x = coordinates.x - 1; x < coordinates.x + 2; x++) {
            for (int y = coordinates.y - 1; y < coordinates.y + 2; y++) {
                try {
                    mineField[x][y] = mineField[x][y].countedMinesAround();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("On boundary");
                }
            }
        }
    }

    private Coordinates generateMineCoordinates(int size, Field[][] mineField) {
        Coordinates coordinates = generateCoordinates(size);
        while (mineField[coordinates.x][coordinates.y].isMine()) {
            coordinates = generateCoordinates(size);
        }
        return coordinates;
    }

    private Coordinates generateCoordinates(int size) {
        return new Coordinates(generator.nextInt(size), generator.nextInt(size));
    }

    private Field[][] emptyField(int size) {
        Field[][] mineField = new Field[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mineField[i][j] = Field.empty();
            }
        }
        return mineField;
    }

}
