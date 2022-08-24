package org.example;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fruit {

    public List<Position> getFruits(Terminal terminal) throws IOException {
        List<Position> fruits = new ArrayList<>();
        fruits.add(new Position(20, 5));
        fruits.add(new Position(5, 5));
        fruits.add(new Position(10, 5));
        fruits.add(new Position(30, 5));

        for (Position p : fruits) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter('F');
        }
        return fruits;
    }
}
