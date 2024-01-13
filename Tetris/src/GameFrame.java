import java.awt.Color;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

    // Sets up the window
    GameFrame() {
        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 800);
        this.setResizable(false);
    }

}
