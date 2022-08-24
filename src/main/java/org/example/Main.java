package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        Position player = new Position(13,13);
        terminal.setCursorPosition(player.x, player.y);
        terminal.putCharacter('\u263a');

        final char block = '\u2588';

        List<Position> obsticles = new ArrayList<>();


        for(int i = 0;i<100;i++){
            obsticles.add(new Position(100, 0+i));
        }

        for(int i = 0;i<100;i++){
            obsticles.add(new Position(0+i, 100));
        }

        for (Position p : obsticles) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(block);
        }

        terminal.flush();




        Snake s = new Snake();
        List<Position> snake = s.getBody(terminal); //get snake printed

        Position food = new Position(25, 25); // added food
        terminal.setCursorPosition(food.x, food.y);
        terminal.putCharacter('O');

        DescriptionText start = new DescriptionText("SNAKE GAME", "GAME OVER");
        start.startText(start.start, terminal); // created text

        KeyStroke latestKeyStroke = null;

        boolean continueReadingInput = true;
        while (continueReadingInput) {

            int index = 0;
            KeyStroke keyStroke = null;
            do {
                index++;
                if (index % 100 == 0) {
                    if (latestKeyStroke != null) {
                        handleSnake(snake, latestKeyStroke, terminal,obsticles);
                    }
                }

                Thread.sleep(5);
                keyStroke = terminal.pollInput();


            } while (keyStroke == null);
            latestKeyStroke = keyStroke;


        }
    }
    private static void handleSnake(List<Position> snake, KeyStroke keyStroke, Terminal terminal,List<Position> obsticles) throws Exception {
        Position head = new Position(snake.get(0).x, snake.get(0).y);
        Position tail = new Position(snake.get(snake.size()-1).x, snake.get(snake.size()-1).y);
        snake.add(0, head);


        switch (keyStroke.getKeyType()) {
            case ArrowDown:
                snake.get(0).y += 1;
                break;
            case ArrowUp:
                snake.get(0).y -= 1;
                break;
            case ArrowRight:
                snake.get(0).x += 1;
                break;
            case ArrowLeft:
                snake.get(0).x -= 1;
                break;
        }


        boolean crashIntoObsticle = false;
        for (Position p : obsticles) {
            if (p.x == snake.get(0).x && p.y == snake.get(0).y) {
                crashIntoObsticle = true;
            }
        }

        if (crashIntoObsticle) {
            terminal.close();

        }

        else {
            terminal.setCursorPosition(snake.get(0).x, snake.get(0).y);
            terminal.putCharacter(' ');
            terminal.setCursorPosition(snake.get(0).x, snake.get(0).y);
            terminal.putCharacter('\u263a');
        }

        terminal.flush();


        //Draw snake:

        terminal.setCursorPosition(tail.x, tail.y);
        terminal.putCharacter(' ');
        snake.remove(snake.get(snake.size()-1));

        terminal.setCursorPosition(head.x, head.y);
        terminal.putCharacter('X');

        terminal.flush();

    }
}