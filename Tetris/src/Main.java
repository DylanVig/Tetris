import java.awt.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) { createGui(); }

    public static void createGui() {

        // Creates the window
        GameFrame frame = new GameFrame();

        TetrisScreen tScreen = new TetrisScreen();

        // Creates the button
        JButton startButton = new JButton("Start");
        Font fButton = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        startButton.setFont(fButton);
        frame.add(startButton, BorderLayout.PAGE_END);
        frame.add(tScreen);

        // Starts the game when the button is clicked
        startButton.addActionListener(e -> {
            tScreen.startGame();
            startButton.setVisible(false);
        });

        frame.setVisible(true);

    }
}