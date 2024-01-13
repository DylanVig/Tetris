import java.awt.Graphics;
import java.awt.Color;

// BrickA will be a brick that is four boxes in a straight line horizontally
public class BrickC extends Brick {

    private int x;
    private int y;

    private boolean right;
    private boolean left;

    // determines if this brick is the brick that is currently being controlled
    private boolean current;

    private boolean rotate;

    private int rotatePhase;

    private Color color;

    private Tile[] brick;

    BrickC(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        color = Color.YELLOW;
        brick = new Tile[4];
        for (int i = 0; i < 2; i++) {
            brick[i] = new Tile(x + 35 * i, y, color);
            brick[i + 2] = new Tile(x + 35 * i, y + 35, color);
        }
        current = true;
        rotatePhase = 1;
        rotate = false;
    }

    public void paintBrick(Graphics g) {
        for (Tile t : brick) {
            t.paintTile(g);
        }
    }

    public void update() {
        if (current) {
            for (Tile t : brick) {
                t.changeY(35);
            }
        }
        int lowest = 0;
        for (Tile t : brick) {
            if (lowest < t.getY()) {
                lowest = t.getY();
            }
        }
        if (lowest == 645) {
            current = false;
        }
    }

    public void move() {
        if (current && right) {
            for (Tile t : brick) {
                t.changeX(35);
                right = false;
            }
        }
        if (current && left) {
            for (Tile t : brick) {
                t.changeX(-35);
                left = false;
            }
        }
    }

    public int getLowest() {
        int lowest = 0;
        for (Tile t : brick) {
            if (lowest < t.getY()) {
                lowest = t.getY();
            }
        }
        return lowest;
    }

    public int getLeftMost() {
        int lowest = 1000;
        for (Tile t : brick) {
            if (lowest > t.getX()) {
                lowest = t.getX();
            }
        }
        return lowest;
    }

    public int getRightMost() {
        int highest = 0;
        for (Tile t : brick) {
            if (highest < t.getX()) {
                highest = t.getX();
            }
        }
        return highest;
    }

    public void rotate() {

    }

    public void checkBounds() {

    }

    public boolean getRotate() { return false; }

    public void setRotate(boolean rotate) { this.rotate = rotate; }

    public Tile[] getBrick() { return brick; }

    public Color getColor() { return color; }

    public void setRight(boolean right) { this.right = right; }

    public void setLeft(boolean left) { this.left = left; }
}