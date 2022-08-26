package org.example;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class DescriptionText {
    public String start;
    public String gameOver;

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

    public void gameOverText(String string, Terminal terminal) throws IOException {
        for (int i = 0; i < string.length(); i++) {
            terminal.setCursorPosition(35+i, 12);
            terminal.putCharacter(string.charAt(i));
        }
        terminal.flush();
    }
    public void startText(String string, Terminal terminal) throws IOException {
        for (int i = 0; i < string.length(); i++) {
            terminal.setCursorPosition(1+i, 1);
            terminal.putCharacter(string.charAt(i));
        }
        terminal.flush();
    }
}
