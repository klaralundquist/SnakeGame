package org.example;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {
    int size = 3;

    public List<Position> getBody(Terminal terminal) throws IOException {
        List<Position> snake = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            snake.add(new Position(10, 10+i));
        }
        for (Position p : snake) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter('Ϩ');
        }
        return snake;
    }
    public static void handleSnake(List<Position> snake, KeyStroke keyStroke, Terminal terminal, DescriptionText start, List <Position> fruit, List <Position> obsticles) throws Exception {
        Position head = new Position(snake.get(0).x, snake.get(0).y);
        Position tail = new Position(snake.get(snake.size() - 1).x, snake.get(snake.size() - 1).y);
        snake.add(0, head);

        switch (keyStroke.getKeyType()) {
            case ArrowDown -> snake.get(0).y += 1;
            case ArrowUp -> snake.get(0).y -= 1;
            case ArrowRight -> snake.get(0).x += 1;
            case ArrowLeft -> snake.get(0).x -= 1;
        }

        String size = String.valueOf(snake.size()-3);
        for (int i = 0; i < size.length(); i++) {
            terminal.setCursorPosition(62 + i, 1);
            terminal.putCharacter(' ');
            terminal.setCursorPosition(63 + i, 1);
            terminal.putCharacter('P');
            terminal.setCursorPosition(64 + i, 1);
            terminal.putCharacter('O');
            terminal.setCursorPosition(65 + i, 1);
            terminal.putCharacter('I');
            terminal.setCursorPosition(66 + i, 1);
            terminal.putCharacter('N');
            terminal.setCursorPosition(67 + i, 1);
            terminal.putCharacter('T');
            terminal.setCursorPosition(68 + i, 1);
            terminal.putCharacter('S');
            terminal.setCursorPosition(69 + i, 1);
            terminal.putCharacter(':');
            terminal.setCursorPosition(70 + i, 1);
            terminal.setCursorPosition(71 + i, 1);

            terminal.putCharacter(size.charAt(i));
        }

        boolean crashIntoObsticle = false;
        for (Position p : obsticles) {
            if ((p.x == snake.get(0).x) && (p.y == snake.get(0).y)) {
                crashIntoObsticle = true;

            }
        }
        if (crashIntoObsticle) {
            terminal.clearScreen();
            start.gameOverText(start.getGameOver(), terminal);
            terminal.flush();
            keyStroke.wait();
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

        //skapar ny frukt när snake äter en på random ställe:

        if (fruit.size() < 8) {
            Random r = new Random();
            fruit.add(new Position(r.nextInt(1, 75),r.nextInt(2, 23)));
            for (Position p : fruit) {
                for (Position s : snake) { //för att frukten inte ska hamna i ormen, funkar ej?
                    if (p.x != s.x && p.y != s.y) {
                        terminal.setCursorPosition(p.x, p.y);
                        terminal.putCharacter('Ѽ');
                    }
                }
            }
        }

        terminal.setCursorPosition(tail.x, tail.y);
        terminal.putCharacter(' ');
        snake.remove(snake.get(snake.size() - 1));

        terminal.setCursorPosition(head.x, head.y);
        terminal.putCharacter('Ƨ');

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
            terminal.flush();
            keyStroke.wait();

            Character c =keyStroke.getCharacter(); //funkar ej att stänga, error med keystroke.wait
            if (c == Character.valueOf('q')) {
                terminal.close();
            }
        }
    }
}

