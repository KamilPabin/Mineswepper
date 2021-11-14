package com.pabin.kamil.mineswepper.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class MineField {

    private final Field[][] minefield;

    MineField(Field[][] minefield) {
        this.minefield = minefield;
    }

    public void unfold(Coordinates coordinates) {
        Field unfoldedField = minefield[coordinates.x][coordinates.y] = minefield[coordinates.x][coordinates.y].unfold();

        if (unfoldedField.isEmpty()) {
            unfoldAdjacentFields(coordinates);
        }
    }

    public void markAsUnsafe(Coordinates coordinates) {
        minefield[coordinates.x][coordinates.y] = minefield[coordinates.x][coordinates.y].markedAsUnsafe();
    }

    public void markFieldSafe(Coordinates coordinates) {
        minefield[coordinates.x][coordinates.y] = minefield[coordinates.x][coordinates.y].markedAsSafe();
    }

    public MineFieldView view() {
        return new MineFieldView(this.minefield);
    }

    private void unfoldAdjacentFields(Coordinates coordinates) {
        traverseRows(coordinates);
    }

    private void traverseRows(Coordinates coordinates) {
        for (int x = coordinates.x - 1; x < coordinates.x + 2; x++) {
            traverseColumns(coordinates, x);
        }
    }

    private void traverseColumns(Coordinates coordinates, int x) {
        List<Coordinates> coordinatesToTraverse = new ArrayList<>();
        for (int y = coordinates.y -1 ; y < coordinates.y + 2; y++) {
            Field fieldToUnfold;
            Coordinates coordinatesToUnfold;
            try {
                coordinatesToUnfold = Coordinates.of(x, y);
                if (coordinates.equals(coordinatesToUnfold)) {
                    continue;
                }
                fieldToUnfold = minefield[x][y];
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                continue;
            }

            if (!fieldToUnfold.containsMine() && !fieldToUnfold.isUnfolded()) {
                minefield[x][y] = fieldToUnfold.unfold();
                if (fieldToUnfold.isEmpty()) {
                    coordinatesToTraverse.add(coordinatesToUnfold);
                }
            }
        }
        coordinatesToTraverse.forEach(this::unfoldAdjacentFields);
    }

    public boolean isDisarmed() {
        return Arrays.stream(this.minefield).flatMap(Arrays::stream)
                .filter(Field::containsMine)
                .allMatch(Field::isMarkedUnsafe) ||
                Arrays.stream(this.minefield).flatMap(Arrays::stream)
                        .filter(f -> !f.containsMine())
                        .allMatch(Field::isUnfolded);

    }

    public boolean anyMineExploded() {
        return Arrays.stream(this.minefield).flatMap(Arrays::stream)
                .filter(Field::containsMine)
                .anyMatch(Field::isUnfolded);
    }
}
