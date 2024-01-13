import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import java.util.Objects;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisScreen extends JPanel implements KeyListener {

    // time delay for timer 1
    private int targetTimeMillis = 250;

    // timer for vertical movement
    private Timer timer;

    // timer for horizontal movement and rotations
    private Timer timer2;

    // if the game has started
    private boolean start = false;

    // if the game has started and not finished
    private boolean inGame = false;

    // random used to randomly select a piece from an array
    private Random r = new Random();

    // creates the board
    private Grid board;

    // brick that is currently controlled
    private Brick b;

    // score
    private int score;

    // number of lines completed
    private int lines;

    // image for starting screen
    private ImageIcon image;
    private Image startScreen;

    // array to randomly choose next brick from
    Brick[] array = {new BrickA(120, 15), new BrickB(120, 15), new BrickC(155, 15), new BrickD(120, 15), new BrickE(120, 15), new BrickF(155, 15)};

    // Constructor
    TetrisScreen() {
        // timer for vertical movement
        timer = new Timer(targetTimeMillis, (ActionEvent e) -> timeout());
        timer.setInitialDelay(0);
        timer.setCoalesce(true);

        // timer for horizontal/rotational movement
        timer2 = new Timer(0, (ActionEvent e) -> timeout2());
        timer.setInitialDelay(0);
        timer.setCoalesce(true);

        // keyListener
        this.addKeyListener(this);
        this.setFocusable(true);

        // adds everything else
        board = new Grid();
        int a = r.nextInt(array.length);
        b = array[a];
        score = 0;
        lines = 0;
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("StartScreen.jpg")));
        startScreen = image.getImage();
    }

    // paints the game
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        if(!start) {
            paintStartScreen(g);
        }
        else {
            paintGameScreen(g);
        }
    }

    // paints the start screen (image)
    public void paintStartScreen(Graphics g) {
        g.drawImage(startScreen, 100, 300, 500, 150, null);
    }

    // paints the game, including the board, brick, and score
    public void paintGameScreen(Graphics g) {
        g.fillRect(46, 46, 288, 638);
        board.paintGrid(g);
        b.paintBrick(g);
        paintScore(g);
        repaint();
    }

    // paints the score by drawing Strings with graphics
    public void paintScore(Graphics g) {
        g.setFont(new Font("IMPACT", Font.BOLD, 50));
        g.setColor(Color.WHITE);
        g.drawString("BLOCKS: " + score, 350, 100);
        g.drawString("LINES: " + lines, 350, 150);
        g.drawString("SCORE: " + ((lines * 10) + score), 350, 200);
    }

    // starts the game
    public void startGame() {
        start = true;
        inGame = true;
        timer.restart();
        timer2.restart();
        timer.setDelay(targetTimeMillis);
        timer2.setDelay(0);
    }

    // what happens during the vertical movement timer (timer)
    private void timeout() {
        if (inGame) {
            // if the brick reaches the bottom or a filled brick is beneath it, then set it down on the grid and spawn a new current brick
            if (b.getLowest() == 645) {
                for ( Tile x : b.getBrick()) {
                    board.setGrid(x);
                }
                int a = r.nextInt(array.length);
                b = array[a];
                array = new Brick[]{new BrickA(120, 15), new BrickB(120, 50), new BrickC(155, 50), new BrickD(120, 15), new BrickE(120, 15), new BrickF(155, 15)};
                score += 1;
            }
            else {
                for (Tile y : b.getBrick()) {
                    if (board.getGrid()[y.getXTile()][y.getYTile() + 1].getFilled()) {
                        for (Tile x : b.getBrick()) {
                            board.setGrid(x);
                        }
                        int a = r.nextInt(array.length);
                        b = array[a];
                        array = new Brick[]{new BrickA(120, 15), new BrickB(120, 50), new BrickC(155, 50), new BrickD(120, 15), new BrickE(120, 15), new BrickF(155, 15)};
                        score += 1;
                        break;
                    }
                }
            }
            // update board
            board.update();
            // update score
            if (board.getLine() != 0) {
                lines += board.getLine();
                board.setLine(0);
            }
            // update brick
            b.update();
            // end game if top is reached
            for (int i = 0; i < 8; i++) {
                if (board.getGrid()[i][0].getFilled()) {
                    inGame = false;
                    break;
                }
            }
            repaint();
        }
    }

    // uses timer2 to determine horizontal movement and rotation
    private void timeout2() {
        if (inGame) {
            b.move();
            b.rotate();
            repaint();
        }
    }

    // if the s key is held down, then move down faster
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 's') {
            timer.setDelay(75);
        }
    }

    // if a is pressed, move left
    // if d is pressed, move right
    // if w is pressed, rotate
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'a') {
            boolean moveable = true;
            for (Tile x : b.getBrick()) {
                if (x.getXTile() == 0) {
                    moveable = false;
                }
                else if (board.getGrid()[x.getXTile() - 1][x.getYTile()].getFilled()) {
                    moveable = false;
                }
            }
            if (moveable) {
                b.setLeft(true);
            }
        }
        if (e.getKeyChar() == 'd') {
            boolean moveable = true;
            for (Tile x : b.getBrick()) {
                if (x.getXTile() == 7) {
                    moveable = false;
                }
                else if (board.getGrid()[x.getXTile() + 1][x.getYTile()].getFilled()) {
                    moveable = false;
                }
            }
            if (moveable) {
                b.setRight(true);
            }
        }
        if (e.getKeyChar() == 'w') {
            b.setRotate(true);
        }
    }

    // if s is released, return to normal timer delay
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 's') {
            timer.setDelay(250);
        }
    }
}
