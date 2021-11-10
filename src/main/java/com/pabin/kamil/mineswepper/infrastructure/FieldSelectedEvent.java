package com.pabin.kamil.mineswepper.infrastructure;

import java.awt.event.MouseEvent;

public final class FieldSelectedEvent {
    final int x;
    final int y;
    final MouseEvent mouseEvent;

    FieldSelectedEvent(int x, int y, MouseEvent mouseEvent) {
        this.x = x;
        this.y = y;
        this.mouseEvent = mouseEvent;
    }
}
