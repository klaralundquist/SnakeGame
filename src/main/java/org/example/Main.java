package org.example;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
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
        terminal.setBackgroundColor(TextColor.ANSI.BLACK);
        terminal.setForegroundColor(TextColor.ANSI.GREEN);
        terminal.enableSGR(SGR.BOLD);
        terminal.setCursorVisible(false);
        TextGraphics textGraphics = terminal.newTextGraphics();

        Snake s = new Snake();
        List<Position> snake = s.getBody(terminal); //get snake printed

        Obsticles o = new Obsticles();
        List<Position>obsticles =o.getob(terminal); //print obstacles

        Fruit f = new Fruit();
        List<Position> fruit = f.getFruits(terminal); // added fruits

        DescriptionText text = new DescriptionText("SNAKE GAME", "GAME OVER");
        text.startText(text.start, terminal); // created text

        KeyStroke latestKeyStroke = null;

        boolean continueReadingInput = true;
        while (continueReadingInput) {

            int index = 0;
            KeyStroke keyStroke = null;
            do {
                index++;
                if (index % 60 == 0) {
                    if (latestKeyStroke != null) {
                        s.handleSnake(snake, latestKeyStroke, terminal, text, fruit, obsticles);
                    }
                }
                Thread.sleep(3);
                keyStroke = terminal.pollInput();

            } while (keyStroke == null);
            latestKeyStroke = keyStroke;
        }
    }
}
