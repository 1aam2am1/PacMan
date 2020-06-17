import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Player extends Entity implements KeyListener {
    private Image picture;

    public Player(int _x, int _y) {
        super(_x, _y);

        try {
            picture = ImageIO.read(getClass().getResource(
                    "/resources/PacMan1.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        display_image = picture;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            destiny = Position.LEFT;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            destiny = Position.RIGHT;
        } else if (keyCode == KeyEvent.VK_UP) {
            destiny = Position.UP;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            destiny = Position.DOWN;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
