import java.awt.Graphics;
import java.awt.Color;

// BrickA will be a brick that is four boxes in a straight line horizontally
public class BrickE extends Brick {

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

    BrickE(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        color = Color.ORANGE;
        brick = new Tile[4];
        brick[0] = new Tile(x, y, color);
        brick[1] = new Tile(x - 35, y + 35, color);
        brick[2] = new Tile(x, y + 35, color);
        brick[3] = new Tile(x + 35, y + 35, color);
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

    public int getHighest() {
        int highest = 1000;
        for (Tile t : brick) {
            if (highest > t.getY()) {
                highest = t.getY();
            }
        }
        return highest;
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
        if (rotate) {
            if (rotatePhase == 1) {
                rotatePhase = 2;
                int tempX = brick[0].getX();
                int tempY = brick[0].getY();
                brick[1].setX(tempX - 35);
                brick[1].setY(tempY - 35);
                brick[2].setX(tempX - 35);
                brick[2].setY(tempY);
                brick[3].setX(tempX - 35);
                brick[3].setY(tempY + 35);
                checkBounds();
            } else if (rotatePhase == 2) {
                rotatePhase = 3;
                int tempX = brick[0].getX();
                int tempY = brick[0].getY();
                brick[1].setX(tempX + 35);
                brick[1].setY(tempY - 35);
                brick[2].setX(tempX);
                brick[2].setY(tempY - 35);
                brick[3].setX(tempX - 35);
                brick[3].setY(tempY - 35);
                checkBounds();
            } else if (rotatePhase == 3) {
                rotatePhase = 4;
                int tempX = brick[0].getX();
                int tempY = brick[0].getY();
                brick[1].setX(tempX + 35);
                brick[1].setY(tempY + 35);
                brick[2].setX(tempX + 35);
                brick[2].setY(tempY);
                brick[3].setX(tempX + 35);
                brick[3].setY(tempY - 35);
                checkBounds();
            } else if (rotatePhase == 4) {
                rotatePhase = 1;
                int tempX = brick[0].getX();
                int tempY = brick[0].getY();
                brick[1].setX(tempX - 35);
                brick[1].setY(tempY + 35);
                brick[2].setX(tempX);
                brick[2].setY(tempY + 35);
                brick[3].setX(tempX + 35);
                brick[3].setY(tempY + 35);
                checkBounds();
            }
            rotate = false;
        }
    }

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

    public boolean getRotate() { return false; }

    public void setRotate(boolean rotate) { this.rotate = rotate; }

    public Tile[] getBrick() { return brick; }

    public Color getColor() { return color; }

    public void setRight(boolean right) { this.right = right; }

    public void setLeft(boolean left) { this.left = left; }
}
