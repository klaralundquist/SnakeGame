package org.example;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        List<Position> snake = new ArrayList<>(); // added snake array
        for (int i = 0; i < 4; i++) {
            snake.add(new Position(15, 15+i));
        }
        for (Position p : snake) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter('X');
        }

        Position player = new Position(13,13);
        terminal.setCursorPosition(player.x, player.y);
        terminal.putCharacter('\u263a');

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
                        handlePlayer(player, food, latestKeyStroke, terminal);
                        //handleSnake(snake, food, latestKeyStroke, terminal);
                    }
                }

                Thread.sleep(5); // might throw InterruptedException
                keyStroke = terminal.pollInput();


            } while (keyStroke == null);
            latestKeyStroke = keyStroke;


        }
    }

    private static void handlePlayer (Position player, Position food, KeyStroke keyStroke, Terminal terminal) throws Exception {

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

        if (player.x != food.x && player.y != food.y) {
            terminal.setCursorPosition(oldPosition.x, oldPosition.y);
            terminal.putCharacter(' ');
        } else {
            terminal.setCursorPosition(oldPosition.x, oldPosition.y);
            terminal.putCharacter('\u263a');

        }

        terminal.setCursorPosition(player.x, player.y);
        terminal.putCharacter('\u263a');

        terminal.flush();
    }
    private static void handleSnake (List<Position> snake, Position food, KeyStroke keyStroke, Terminal terminal) throws Exception {

        Position firstSnake = new Position(snake.get(0).x, snake.get(0).y);
        Position oldSnake = new Position(snake.get(snake.size()-1).x, snake.get(snake.size()-1).y);
        switch (keyStroke.getKeyType()) {
            case ArrowDown:
                snake.get(0).y += 1;
                break;
            case ArrowUp:
                snake.get(0).y -= 1;
                snake.get(3).y -= 1;
                break;
            case ArrowRight:
                snake.get(0).x += 1;
                snake.get(1).y -= 1;
                if (snake.get(1).x == snake.get(0).x) {
                    snake.get(1).x += 1;
                }
                snake.get(2).y -= 1;
                if (snake.get(2).x == snake.get(1).x) {
                    snake.get(2).x += 1;
                }
                snake.get(3).y -= 1;
                if (snake.get(3).x == snake.get(2).x) {
                    snake.get(3).x += 1;
                }
                if (snake.get(3).x == snake.get(0).x) {
                    terminal.putCharacter(' ');
                }
                break;
            case ArrowLeft:
                snake.get(0).x -= 1;
                snake.get(1).y -= 1;
                if (snake.get(1).x == snake.get(0).x) {
                    snake.get(1).x += 1;
                }
                snake.get(2).y -= 1;
                if (snake.get(2).x == snake.get(1).x) {
                    snake.get(2).x += 1;
                }
                snake.get(3).y -= 1;
                if (snake.get(3).x == snake.get(2).x) {
                    snake.get(3).x += 1;
                }
                break;
        }

        // Draw snake

        //if (snake.get(0).x != food.x && snake.get(0).y != food.y) {
            terminal.setCursorPosition(snake.get(snake.size()-1).x, snake.get(snake.size()-1).y);
            terminal.putCharacter(' ');
        //} else {
            terminal.setCursorPosition(oldSnake.x, oldSnake.y);
            snake.add(new Position(snake.get(3).x, snake.get(3).y));

        //}

        terminal.setCursorPosition(snake.get(0).x, snake.get(0).y);
        terminal.putCharacter('X');

        terminal.flush();
    }
}