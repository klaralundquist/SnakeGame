package org.example;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Snake {
    int size = 3;

    public Position[] getBody(Terminal terminal) throws IOException {
        Position[] snake = new Position[size];
        for (int i = 0; i < snake.length; i++) {
            snake[i] = new Position(10, 10+i);
        }
        for (Position p : snake) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter('X');
        }
        return snake;
    }
}
