package org.example;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fruit {

    public List<Position> getFruits(Terminal terminal) throws IOException {
        List<Position> fruits = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            fruits.add(new Position(r.nextInt(1, 75),r.nextInt(2, 23)));
        }

        for (Position p : fruits) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter('Ѽ');
        }
        return fruits;
    }
}
