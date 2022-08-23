package org.example;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class DescriptionText {
    private String start;
    private String gameOver;

    public DescriptionText(String start, String gameOver) {
        this.start = start;
        this.gameOver = gameOver;
    }
    public String getStart() {
        return start;
    }
    public String getGameOver() {
        return gameOver;
    }

    public void description(String string) throws IOException {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();

        for (int i = 0; i < string.length(); i++) {
            terminal.setCursorPosition(30+i, 15);
            terminal.putCharacter(string.charAt(i));
        }
        terminal.flush();
    }
}
