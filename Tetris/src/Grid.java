import java.awt.Graphics;
import java.awt.Color;

public class Grid {

    // starting coordinates for the grid
    private final int x;
    private final int y;

    // number of lines filled in one turn
    private int line;

    // 2D array that determines the structure of the board
    private Tile[][] grid;

    // Creates the grid
    Grid() {
        x = 50;
        y = 100;
        grid = new Tile[8][18];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 18; j++) {
                grid[i][j] = new Tile(50 + 35 * i, 50 + 35 * j, Color.BLACK);
            }
        }
        line = 0;
    }

    // paints the board by painting all the tiles in the grid 2D array
    public void paintGrid(Graphics g) {
        for (Tile[] x : grid) {
            for (Tile y : x) {
                y.paintTile(g);
            }
        }
        update();
    }

    // updates the row if a line is completed
    public void update() {
        for (int i = 0; i < 18; i++) {
            boolean row = true;
            for (int j = 0; j < 8; j++) {
                if (!grid[j][i].getFilled()) {
                    row = false;
                }
            }
            // if a row is completed, set each tile equal to the one above it to shift everything down
            if (row) {
                for (int j = 0; j < 8; j++) {
                    grid[j][i].setFilled(false);
                    grid[j][i].setColor(Color.BLACK);
                    for (int k = 0; k < 3; k++) {
                        grid[j][i-k].setColor(grid[j][i-k-1].getColor());
                        grid[j][i-k].setFilled(grid[j][i-k-1].getFilled());
                    }
                }
                // add one to the number of lines completed this turn
                line += 1;
            }
        }
    }

    // get and set methods
    public Tile[][] getGrid() { return grid; }

    public int getLine() { return line; }

    public void setLine(int line) { this.line = line; }

    // fill in the grid based on what has been placed down
    public void setGrid(Tile t) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 18; j++) {
                if (grid[i][j].getX() == t.getX() && grid[i][j].getY() == t.getY()) {
                    grid[i][j] = t;
                }
            }
        }
    }
}
