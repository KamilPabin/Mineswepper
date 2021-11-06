package com.pabin.kamil.mineswepper.domain;

final class NoOpCommand implements Command {

    static final Command INSTANCE = new NoOpCommand();

    private NoOpCommand() {
    }

    @Override
    public void execute(MineField mineField) {

    }
}
