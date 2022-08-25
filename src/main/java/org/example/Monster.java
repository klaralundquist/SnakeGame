package org.example;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster {

    public List<Position> getMonster(Terminal terminal) throws IOException {
        List<Position> monsters = new ArrayList<>();
        Random r = new Random();
        monsters.add(new Position(r.nextInt(50),r.nextInt(25)));
        monsters.add(new Position(r.nextInt(50),r.nextInt(25)));

        return monsters;
    }}
