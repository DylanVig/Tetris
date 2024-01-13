import java.awt.Color;
import java.awt.Graphics;

// Abstract class that all other brick classes extend
// All subclassed of Brick are the same (excluding the initial constructor and the rotate method, so
// only BrickA will be commented
public abstract class Brick {

    Brick(int x, int y) {

    }

    public abstract void paintBrick(Graphics g);

    public abstract void update();

    public abstract void move();

    public abstract int getLowest();

    public abstract int getLeftMost();

    public abstract int getRightMost();

    public abstract void rotate();

    public abstract void checkBounds();

    public abstract boolean getRotate();

    public abstract void setRotate(boolean rotate);

    public abstract Tile[] getBrick();

    public abstract Color getColor();

    public abstract void setRight(boolean right);

    public abstract void setLeft(boolean left);

}
