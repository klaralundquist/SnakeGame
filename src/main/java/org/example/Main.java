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

        Monster m = new Monster();
        List<Position> monster = m.getMonster(terminal); // added monsters


        DescriptionText start = new DescriptionText("SNAKE GAME", "GAME OVER");
        start.startText(start.start, terminal); // created text


        KeyStroke latestKeyStroke = null;

        boolean continueReadingInput = true;
        while (continueReadingInput) {

            int index = 0;
            KeyStroke keyStroke = null;
            do {
                index++;
                if (index % 60 == 0) {
                    if (latestKeyStroke != null) {
                        handleSnake(snake, latestKeyStroke, terminal, start, fruit, monster);
                    }
                }

                Thread.sleep(3);
                keyStroke = terminal.pollInput();


            } while (keyStroke == null);
            latestKeyStroke = keyStroke;


        }
    }

    private static void handleSnake(List<Position> snake, KeyStroke keyStroke, Terminal terminal, DescriptionText start, List <Position> fruit,List<Position> monster ) throws Exception {
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

        //skapar ny frukt när snake äter en på random ställe:

        if (fruit.size() < 4) {
            Random r = new Random();
            fruit.add(new Position(r.nextInt(50),r.nextInt(25)));
            for (Position p : fruit) {
                terminal.setCursorPosition(p.x, p.y);
                terminal.putCharacter('F');
            }
        }
        String size = String.valueOf(snake.size()-3);
        for (int i = 0; i < size.length(); i++) {
            terminal.setCursorPosition(63 + i, 0);
            terminal.putCharacter('P');
            terminal.setCursorPosition(64 + i, 0);
            terminal.putCharacter('O');
            terminal.setCursorPosition(65 + i, 0);
            terminal.putCharacter('I');
            terminal.setCursorPosition(66 + i, 0);
            terminal.putCharacter('N');
            terminal.setCursorPosition(67 + i, 0);
            terminal.putCharacter('T');
            terminal.setCursorPosition(68 + i, 0);
            terminal.putCharacter('S');
            terminal.setCursorPosition(69 + i, 0);
            terminal.putCharacter(':');
            terminal.setCursorPosition(70 + i, 0);

            terminal.putCharacter(size.charAt(i));
        }

        for (Position monsters : monster) {
            terminal.setCursorPosition(monsters.x, monsters.y);
            terminal.putCharacter(' ');

            if (head.x > monsters.x) {
                monsters.x++;
            }
            else if (head.x < monsters.x) {
                monsters.x--;
            }
            if (head.y > monsters.y) {
                monsters.y++;
            }
            else if (head.y < monsters.y) {
                monsters.y--;
            }

            terminal.setCursorPosition(monsters.x, monsters.y);
            terminal.putCharacter('Ʌ');
        }
        terminal.flush();


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




    }}