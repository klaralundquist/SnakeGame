package org.example;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class DescriptionText {
    private String start;
    private String gameOver;
    private String level;

    public DescriptionText(String start, String gameOver, String level) {
        this.start = start;
        this.gameOver = gameOver;
        this.level = level;
    }
    public String getStart() {
        return start;
    }
    public String getGameOver() {
        return gameOver;
    }
    public String getLevel() {
        return level;
    }

    public void gameOverText(String string, Terminal terminal) throws IOException {
        for (int i = 0; i < string.length(); i++) {
            terminal.setCursorPosition(30+i, 15);
            terminal.putCharacter(string.charAt(i));
        }
        terminal.flush();
    }
    public void startText(String string, Terminal terminal) throws IOException {
        for (int i = 0; i < string.length(); i++) {
            terminal.setCursorPosition(0+i, 0);
            terminal.putCharacter(string.charAt(i));
        }
        terminal.flush();
    }
    public void displayLevel (String level, Terminal terminal) throws IOException {
        for (int i = 0; i < level.length(); i++) {
            terminal.setCursorPosition(12, 0);
            terminal.putCharacter(level.charAt(i));
        }

    }
}
