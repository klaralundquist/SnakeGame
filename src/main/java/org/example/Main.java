package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(true);

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
                        handleSnake(snake, latestKeyStroke, terminal);
                    }
                }

                Thread.sleep(3);
                keyStroke = terminal.pollInput();


            } while (keyStroke == null);
            latestKeyStroke = keyStroke;


        }
    }
    private static void handleSnake(List<Position> snake, KeyStroke keyStroke, Terminal terminal) throws Exception {
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
        //Random

        Random r = new Random();
        Position fruitPosition = new Position(r.nextInt(80), r.nextInt(24));
        terminal.setCursorPosition(fruitPosition.x,fruitPosition.y);
        terminal.putCharacter('O');
//Lägga till arrey list, loopar mot alla frukter

        if(head.x == fruitPosition.x && head.y == fruitPosition.y){
            terminal.setCursorPosition(tail.x, tail.y);
            terminal.putCharacter('X');
            snake.add(snake.get(snake.size()-1));
        }


        else {

            //Draw snake:

            terminal.setCursorPosition(tail.x, tail.y);
            terminal.putCharacter(' ');
            snake.remove(snake.get(snake.size() - 1));
        }

        terminal.setCursorPosition(head.x, head.y);
        terminal.putCharacter('X');

        terminal.flush();




    }
}