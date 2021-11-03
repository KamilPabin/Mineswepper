package com.pabin.kamil.mineswepper;

import java.security.SecureRandom;

public final class Main {

    public static void main(String[] args) {
        int size = 20;
        int numberOfMines = 100;
        SecureRandom generator = new SecureRandom();
        MineFieldFactory factory = new MineFieldFactory(generator);
        MineField mineField = factory.create(size, numberOfMines);
        mineField.unfold(Coordinates.of(10, 10));
        mineField.markAsUnsafe(Coordinates.of(8, 8));
        System.out.println(mineField);
    }
}

