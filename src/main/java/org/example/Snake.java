package org.example;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    int size = 3;

    public List<Position> getBody(Terminal terminal) throws IOException {
        List<Position> snake = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            snake.add(new Position(10, 10+i));
        }
        for (Position p : snake) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter('X');
        }
        return snake;
    }
}
