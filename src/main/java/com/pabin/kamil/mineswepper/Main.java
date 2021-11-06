package com.pabin.kamil.mineswepper;

import com.pabin.kamil.mineswepper.domain.*;
import com.pabin.kamil.mineswepper.infrastructure.ConsoleDisplay;
import com.pabin.kamil.mineswepper.infrastructure.ConsoleInputHandler;

import java.security.SecureRandom;
import java.util.Scanner;

public final class Main {

    public static void main(String[] args) {
        int size = 5;
        int numberOfMines = 0;
        SecureRandom generator = new SecureRandom();
        Display display = new ConsoleDisplay();
        InputHandler handler = new ConsoleInputHandler(new Scanner(System.in), new CommandFactory());

        MineFieldFactory factory = new MineFieldFactory(generator);
        MineField mineField = factory.create(size, numberOfMines);

        while (!mineField.isDisarmed() ^ mineField.anyMineExploded()) {
            Command command = handler.readCommand();
            command.execute(mineField);
            display.show(mineField.view());
        }
        if (mineField.isDisarmed()) {
            display.show("You securely disarmed this minefield, congrats!");
        } else if (mineField.anyMineExploded()) {
            display.show("We will sent condolence letter to your wife");
        }
    }
}

