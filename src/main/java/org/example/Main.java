package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        Snake s = new Snake();
        List<Position> snake = s.getBody(terminal); //get snake printed

        Fruit f = new Fruit();
        List<Position> fruit = f.getFruits(terminal); // added fruits


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
                        handleSnake(snake, latestKeyStroke, terminal, start, fruit);
                    }
                }

                Thread.sleep(5);
                keyStroke = terminal.pollInput();


            } while (keyStroke == null);
            latestKeyStroke = keyStroke;


        }
    }

    private static void handleSnake(List<Position> snake, KeyStroke keyStroke, Terminal terminal, DescriptionText start, List <Position> fruit) throws Exception {
        Position head = new Position(snake.get(0).x, snake.get(0).y);
        Position tail = new Position(snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y);
        snake.add(0, head);

        switch (keyStroke.getKeyType()) {
            case ArrowDown -> snake.get(0).y += 1;
            case ArrowUp -> snake.get(0).y -= 1;
            case ArrowRight -> snake.get(0).x += 1;
            case ArrowLeft -> snake.get(0).x -= 1;
        }

        List<Position> toRemove = new ArrayList<>(); //array för att ta bort frukt som äts

        for (Position p : fruit) {
            if (p.x == head.x && p.y == head.y) {
                terminal.setCursorPosition(tail.x, tail.y);
                terminal.putCharacter('X');
                snake.add(tail);

                toRemove.add(p);
            }
        }
        fruit.removeAll(toRemove); //tar bort frukt

        terminal.setCursorPosition(tail.x, tail.y);
        terminal.putCharacter(' ');
        snake.remove(snake.get(snake.size() - 1));

        terminal.setCursorPosition(head.x, head.y);
        terminal.putCharacter('X');

        terminal.flush();

        boolean crashIntoSnake = false;

        for (int i = 0; i < snake.size(); i++) {
            if (i > 0) {
                if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                    crashIntoSnake = true;
                }
            }
        }
        if (crashIntoSnake) {
            terminal.clearScreen();
            start.gameOverText(start.getGameOver(), terminal);
            keyStroke.wait();
            terminal.flush();
        }
    }
}
