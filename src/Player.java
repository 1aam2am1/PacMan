import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class Player extends Entity implements KeyListener {
    private static final int m = 50;
    private Animation down;
    private Animation up;
    private Animation left;
    private Animation right;
    private EnumMap<Position, Animation> pictures = new EnumMap<>(Position.class);

    public Player(int _x, int _y) {
        super(_x, _y);

        try {
            Image p = ImageIO.read(getClass().getResource(
                    "/resources/PacMan1.gif"));

            pictures.put(Position.DOWN, new Animation("PacMan", "down", 4, m));
            pictures.put(Position.UP, new Animation("PacMan", "up", 4, m));
            pictures.put(Position.LEFT, new Animation("PacMan", "left", 4, m));
            pictures.put(Position.RIGHT, new Animation("PacMan", "right", 4, m));

            for (var e : pictures.values()) {
                e.l.add(0, p);
                e.reverse = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        changeImage(0);
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

    @Override
    public void updatePosition(Node[][] n, int milliseconds) {
        super.updatePosition(n, milliseconds);

        changeImage(milliseconds);
    }

    private void changeImage(int m) {
        for (var e : pictures.values()) {
            e.getAnimated(m);
        }

        display_image = pictures.get(latest).getAnimated(0);
    }
}
