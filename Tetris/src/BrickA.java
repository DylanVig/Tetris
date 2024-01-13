import java.awt.Graphics;
import java.awt.Color;

// BrickA will be a brick that is four boxes in a straight line horizontally
public class BrickA extends Brick {

    // coordinates used to determine all the block placements in the brick
    private int x;
    private int y;

    // if it is moving right or left
    private boolean right;
    private boolean left;

    // determines if this brick is the brick that is currently being controlled
    private boolean current;

    // if it is rotating
    private boolean rotate;

    // which rotation state it is in
    private int rotatePhase;

    // what color it is
    private Color color;

    // the array of tiles that make up the brick
    private Tile[] brick;

    // Constructor to create the brick
    BrickA(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        color = Color.RED;
        brick = new Tile[4];
        for (int i = 0; i < 4; i++) {
            brick[i] = new Tile(x + 35 * i, y, color);
        }
        current = true;
        rotatePhase = 1;
        rotate = false;
    }

    // paints the brick by individually painting each tile
    public void paintBrick(Graphics g) {
        for (Tile t : brick) {
            t.paintTile(g);
        }
    }

    // updates the brick based on vertical movement and timer
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

    // updates the brick based on horizontal movement and timer2
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

    // determines the lowest coordinate the brick is at
    public int getLowest() {
        int lowest = 0;
        for (Tile t : brick) {
            if (lowest < t.getY()) {
                lowest = t.getY();
            }
        }
        return lowest;
    }

    // determines the highest coordinate the brick is at
    public int getHighest() {
        int highest = 1000;
        for (Tile t : brick) {
            if (highest > t.getY()) {
                highest = t.getY();
            }
        }
        return highest;
    }

    // determines the left most coordinate the brick is at
    public int getLeftMost() {
        int lowest = 1000;
        for (Tile t : brick) {
            if (lowest > t.getX()) {
                lowest = t.getX();
            }
        }
        return lowest;
    }

    // determines the right most coordinate the brick is at
    public int getRightMost() {
        int highest = 0;
        for (Tile t : brick) {
            if (highest < t.getX()) {
                highest = t.getX();
            }
        }
        return highest;
    }

    // rotates the brick
    public void rotate() {
        // if rotate is true, rotate
        if (rotate) {
            // rotate from phase 1 to 2
            if (rotatePhase == 1) {
                rotatePhase = 2;
                int tempX = brick[0].getX();
                int tempY = brick[0].getY();
                for (int i = 0; i < 4; i++) {
                    brick[i].setX(tempX + 35);
                    brick[i].setY(tempY - 70 + (35 * i));
                }
                // check if it violates any bounds and goes outside the grid
                checkBounds();
            }
            // rotate from phase 2 to 3
            else if (rotatePhase == 2) {
                rotatePhase = 3;
                int tempX = brick[0].getX();
                int tempY = brick[0].getY();
                for (int i = 0; i < 4; i++) {
                    brick[i].setX(tempX - 35 + (35 * i));
                    brick[i].setY(tempY - 35);
                }
                checkBounds();
            }
            // rotate from phase 3 to 4
            else if (rotatePhase == 3) {
                rotatePhase = 4;
                int tempX = brick[0].getX();
                int tempY = brick[0].getY();
                for (int i = 0; i < 4; i++) {
                    brick[i].setX(tempX + 70);
                    brick[i].setY(tempY - 35 + (35 * i));
                }
                checkBounds();
            }
            // rotate from phase 4 to 1
            else if (rotatePhase == 4) {
                rotatePhase = 1;
                int tempX = brick[0].getX();
                int tempY = brick[0].getY();
                for (int i = 0; i < 4; i++) {
                    brick[i].setX(tempX - 70 + (35 * i));
                    brick[i].setY(tempY + 70);
                }
                checkBounds();
            }
            // set rotate to false
            rotate = false;
        }
    }

    // checks if the brick goes out of bounds
    public void checkBounds() {
        if (getLowest() == 680) {
            for (Tile t : brick) {
                t.changeY(-35);
            }
        }
        else if (getLowest() == 715) {
            for (Tile t : brick) {
                t.changeY(-70);
            }
        }
        if (getHighest() == 15) {
            for (Tile t : brick) {
                t.changeY(35);
            }
        }
        else if (getHighest() == -20) {
            for (Tile t : brick) {
                t.changeY(70);
            }
        }
        if (getRightMost() == 330) {
            for (Tile t : brick) {
                t.changeX(-35);
            }
        }
        else if (getRightMost() == 365) {
            for (Tile t : brick) {
                t.changeX(-70);
            }
        }
        if (getLeftMost() == 15) {
            for (Tile t : brick) {
                t.changeX(35);
            }
        }
        else if (getLeftMost() == -20) {
            for (Tile t : brick) {
                t.changeX(70);
            }
        }
    }

    // get and set methods
    public boolean getRotate() { return rotate; }

    public void setRotate(boolean rotate) { this.rotate = rotate; }

    public Tile[] getBrick() { return brick; }

    public Color getColor() { return color; }

    public void setRight(boolean right) { this.right = right; }

    public void setLeft(boolean left) { this.left = left; }
}
