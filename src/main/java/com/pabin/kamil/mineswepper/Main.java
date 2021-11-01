package com.pabin.kamil.mineswepper;

import java.security.SecureRandom;
import java.util.Arrays;

public final class Main {

    public static void main(String[] args) {
        int size = 5;
        int numberOfMines= 5;
        int[][] field = new int[5][5];

        SecureRandom generator = new SecureRandom();
        for(int i = 0; i< numberOfMines; i++) {
            int x = generator.nextInt(size);
            int y = generator.nextInt(size);
            if(field[x][y] == -1) {
                x = generator.nextInt(size);
                y = generator.nextInt(size);
            }
            field[x][y] = -1;
            for(int j = x - 1 ; j < x + 2; j++) {
                for(int k = y - 1 ; k < y + 2; k++) {
                    try {
                        if(field[j][k] != -1)
                        field[j][k]++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("On boundary");
                    }
                }
            }
        }
        for(int i = 0; i < size;i++) {
            System.out.println(Arrays.toString(field[i]));
        }
    }
}
