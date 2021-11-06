package com.pabin.kamil.mineswepper;

final class MineField {

    private final Field[][] minefield;

    MineField(Field[][] minefield) {
        this.minefield = minefield;
    }

    void unfold(Coordinates coordinates) {
        Field unfoldedField = minefield[coordinates.x][coordinates.y] = minefield[coordinates.x][coordinates.y].unfold();

        if (unfoldedField.isEmpty()) {
            unfoldAdjacentFields(coordinates);
        }
    }

    void markAsUnsafe(Coordinates coordinates) {
        minefield[coordinates.x][coordinates.y] = minefield[coordinates.x][coordinates.y].markedAsUnsafe();
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
                if(fieldToUnfold.isEmpty()) {
                    unfoldAdjacentFields(coordinatesToUnfold);
                }
            }
        }
    }

    public void markFieldSafe(Coordinates coordinates) {
        minefield[coordinates.x][coordinates.y] = minefield[coordinates.x][coordinates.y].markedAsSafe();
    }

    MineFieldView view() {
        return new MineFieldView(this.minefield);
    }
}
