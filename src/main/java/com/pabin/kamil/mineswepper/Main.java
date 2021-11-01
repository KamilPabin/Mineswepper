package com.pabin.kamil.mineswepper;

import java.security.SecureRandom;

public final class Main {

    public static void main(String[] args) {
        int size = 5;
        int numberOfMines = 5;
        SecureRandom generator = new SecureRandom();
        MineFieldFactory factory = new MineFieldFactory(generator);
        MineField mineField = factory.create(size, numberOfMines);
        System.out.println(mineField);
    }
}

