package com.pabin.kamil.mineswepper.infrastructure;

import com.pabin.kamil.mineswepper.domain.Command;
import com.pabin.kamil.mineswepper.domain.CommandFactory;
import com.pabin.kamil.mineswepper.domain.Coordinates;
import com.pabin.kamil.mineswepper.domain.InputHandler;

import java.util.concurrent.ArrayBlockingQueue;

import static javax.swing.SwingUtilities.*;

public final class SwingInputHandler implements InputHandler {

    private final ArrayBlockingQueue<FieldSelectedEvent> events;
    private final CommandFactory factory;

    public SwingInputHandler(ArrayBlockingQueue<FieldSelectedEvent> events, CommandFactory factory) {
        this.events = events;
        this.factory = factory;
    }

    @Override
    public Command readCommand() {
        FieldSelectedEvent event;
        try {
            event = events.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return factory.noOpCommand();
        }
        if (isLeftMouseButton(event.mouseEvent)) {
            return factory.fromString("unfold", Coordinates.of(event.x, event.y));
        }
        if (isRightMouseButton(event.mouseEvent)) {
            return factory.fromString("unsafe", Coordinates.of(event.x, event.y));
        }
        if (isMiddleMouseButton(event.mouseEvent)) {
            return factory.fromString("safe", Coordinates.of(event.x, event.y));
        }
        return factory.noOpCommand();
    }
}
