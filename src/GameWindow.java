import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {
    Maze m;

    public GameWindow(){
        setBackground(Color.BLACK);

        m = new Maze();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        m.Width = getWidth();
        m.Height = getHeight();
        m.paint(g);
    }
}
