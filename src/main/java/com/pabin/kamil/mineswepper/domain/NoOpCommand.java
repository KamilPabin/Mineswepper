package com.pabin.kamil.mineswepper.domain;

public final class NoOpCommand implements Command {

    public static final Command INSTANCE = new NoOpCommand();

    private NoOpCommand() {
    }

    @Override
    public void execute(MineField mineField) {

    }
}
