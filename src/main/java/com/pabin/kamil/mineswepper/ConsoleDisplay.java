package com.pabin.kamil.mineswepper;

final class ConsoleDisplay implements Display {

    @Override
    public void display(MineFieldView view) {
        System.out.println(view);
    }
}
