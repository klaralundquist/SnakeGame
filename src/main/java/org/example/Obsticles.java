package org.example;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Obsticles {


    private List<Position> obsticles = new ArrayList<>();

    public List<Position> getob (Terminal terminal) throws IOException {

        final char block = '\u2588';




        for (int i = 0; i < 30; i++) {
            obsticles.add(new Position(0, 0 + i));
        }

        for (int i = 0; i < 79; i++) {
            obsticles.add(new Position(0 + i, 0));
        }

        for (int i = 0; i < 30; i++) {
            obsticles.add(new Position(79, 0 + i));
        }

        for (int i = 0; i < 79; i++) {
            obsticles.add(new Position(0 +i,23));
        }

        for (Position p : obsticles) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(block);
        }

        terminal.flush();
        return obsticles;
    }


}