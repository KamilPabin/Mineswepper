package com.pabin.kamil.mineswepper.infrastructure;

import com.pabin.kamil.mineswepper.domain.Coordinates;
import com.pabin.kamil.mineswepper.domain.Display;
import com.pabin.kamil.mineswepper.domain.Field;
import com.pabin.kamil.mineswepper.domain.MineFieldView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ArrayBlockingQueue;

public final class SwingDisplay implements Display {

    private final JFrame frame;
    private final ArrayBlockingQueue<FieldSelectedEvent> events;
    private final JButton[][] fields;

    public SwingDisplay(int boardSize, ArrayBlockingQueue<FieldSelectedEvent> events) {
        this.events = events;
        this.frame = new JFrame();
        this.frame.setSize(500, 500);
        this.fields = new JButton[boardSize][boardSize];
        this.frame.setIgnoreRepaint(true);

        GridLayout layout = new GridLayout(boardSize, boardSize);
        layout.setHgap(0);
        layout.setVgap(0);
        this.frame.setLayout(layout);
        buildLayout(boardSize);
        this.frame.setVisible(true);
        this.frame.repaint();
    }

    private void buildLayout(int boardSize) {
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                JButton button = new JButton();
                button.addMouseListener(new MouseListenerImpl(x, y));
                this.frame.add(button);
                this.fields[x][y] = button;
            }
        }
    }

    @Override
    public void show(MineFieldView view) {
        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields.length; y++) {
                Field field = view.fieldAt(Coordinates.of(x, y));
                if (field.isUnfolded()) {
                    if (field.containsMine()) {
                        fields[x][y].setText("*");
                    } else {
                        fields[x][y].setText(Integer.toString(field.value()));
                    }
                }
                if (field.isMarkedUnsafe()) {
                    fields[x][y].setText("<|");
                }
                if (field.isHidden()) {
                    fields[x][y].setText("");
                }
            }
        }
        this.frame.repaint();
    }

    @Override
    public void show(String text) {

    }

    private class MouseListenerImpl implements MouseListener {
        private final int x;
        private final int y;

        private MouseListenerImpl(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            try {
                events.put(new FieldSelectedEvent(x, y, e));
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
