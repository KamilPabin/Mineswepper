package com.pabin.kamil.mineswepper.infrastructure;

import com.pabin.kamil.mineswepper.domain.Display;
import com.pabin.kamil.mineswepper.domain.MineFieldView;

public final class ConsoleDisplay implements Display {

    @Override
    public void display(MineFieldView view) {
        System.out.println(view);
    }
}
