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
        for (int i = 0; i < 4; i ++) {
            fruits.add(new Position(r.nextInt(80),r.nextInt(80)));
        }

        for (Position p : fruits) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter('F');
        }
        return fruits;
    }
}
