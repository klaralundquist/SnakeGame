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

        Snake s = new Snake();
        Position[] snake = s.getBody(terminal); //get snake printed

        Position food = new Position(25, 25); // added food
        terminal.setCursorPosition(food.x, food.y);
        terminal.putCharacter('O');

        DescriptionText start = new DescriptionText("SNAKE GAME", "GAME OVER");
        start.startText(start.start, terminal); // created text

        KeyStroke latestKeyStroke = null;

        boolean continueReadingInput = true;
        while (continueReadingInput) {

            int index = 0;
            KeyStroke keyStroke = null;
            do {
                index++;
                if (index % 100 == 0) {
                    if (latestKeyStroke != null) {
                        handleSnake(snake, latestKeyStroke, terminal);
                    }
                }

                Thread.sleep(5);
                keyStroke = terminal.pollInput();


            } while (keyStroke == null);
            latestKeyStroke = keyStroke;


        }
    }
    private static void handleSnake(Position[] snake, KeyStroke keyStroke, Terminal terminal) throws Exception {
        Position head = new Position(snake[0].x, snake[0].y);
        Position tail = new Position (snake[2].x, snake[2].y);

        switch (keyStroke.getKeyType()) {
            case ArrowDown:
                snake[0].y += 1;
                break;
            case ArrowUp:
                snake[0].y -= 1;
                break;
            case ArrowRight:
                snake[0].x += 1;
                break;
            case ArrowLeft:
                snake[0].x -= 1;
                break;
        }

        //Draw snake:

        terminal.setCursorPosition(tail.x, tail.y);
        terminal.putCharacter(' ');

        terminal.setCursorPosition(head.x, head.y);
        terminal.putCharacter('X');

        terminal.flush();
    }
}