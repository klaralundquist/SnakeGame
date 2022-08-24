
package org.example;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.*;

public class Fruit {

    public List<Position> getFruits(Terminal terminal) throws IOException {
        List<Position> fruits = new ArrayList<>();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //Random

                Random r = new Random();

                fruits.add(new Position(r.nextInt(20),r.nextInt(24)));

                for (Position p : fruits) {
                    try {
                        terminal.setCursorPosition(p.x, p.y);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        terminal.putCharacter('¤');
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, 5, 1000);


        return fruits;
    }
}