import java.awt.Graphics;
import java.awt.Color;

public class Tile {

    // coordinates of tile
    private int x;
    private int y;

    // whether the tile is colored or not (occupied by a brick)
    private boolean filled;

    // The color that the tile is filled
    private Color fillColor;

    // Constructor
    Tile(int x, int y, Color c) {
        this.x = x;
        this.y = y;
        fillColor = c;
        if (c == Color.BLACK) {
            filled = false;
        }
        else {
            filled = true;
        }
    }

    // paint the tile
    public void paintTile(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 35, 35);
        g.setColor(fillColor);
        g.fillRect(x + 2, y + 2, 31, 31);
    }

    // get, set, and change methods
    public void changeX(int x) { this.x += x; }

    public void setX(int x) { this.x = x; }

    public int getX() { return x; }

    public void changeY(int y) { this.y += y; }

    public void setY(int y) { this.y = y; }

    public int getY() { return y; }

    public int getXTile() { return (x - 50) / 35; }

    public int getYTile() { return (y - 50) / 35; }

    public void setColor(Color c) { fillColor = c; }

    public Color getColor() { return fillColor; }

    public void setFilled(boolean f) { filled = f; }

    public boolean getFilled() { return filled; }

}
