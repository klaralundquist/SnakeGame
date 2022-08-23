package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Main {
    public static void main(String[] args) throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        Position player = new Position(13,13);
        terminal.setCursorPosition(player.x, player.y);
        terminal.putCharacter('\u263a');

        KeyStroke latestKeyStroke = null;

        boolean continueReadingInput = true;
        while (continueReadingInput) {

            int index = 0;
            KeyStroke keyStroke = null;
            do {
                index++;
                if (index % 100 == 0) {
                    if (latestKeyStroke != null) {
                        handlePlayer(player, latestKeyStroke, terminal);
                    }
                }

                Thread.sleep(5); // might throw InterruptedException
                keyStroke = terminal.pollInput();


            } while (keyStroke == null);
            latestKeyStroke = keyStroke;


        }
    }

    private static void handlePlayer (Position player, KeyStroke keyStroke, Terminal terminal) throws Exception {
        // Handle player
        Position oldPosition = new Position(player.x, player.y);
        switch (keyStroke.getKeyType()) {
            case ArrowDown:
                player.y += 1;
                break;
            case ArrowUp:
                player.y -= 1;
                break;
            case ArrowRight:
                player.x += 1;
                break;
            case ArrowLeft:
                player.x -= 1;
                break;
        }

        // Draw player
        terminal.setCursorPosition(oldPosition.x, oldPosition.y);
        terminal.putCharacter(' ');

        terminal.setCursorPosition(player.x, player.y);
        terminal.putCharacter('\u263a');

        terminal.flush();
    }
}