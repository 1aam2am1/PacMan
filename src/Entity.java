import java.awt.*;

public class Entity {
    public int x;
    public int y;

    public int subX = 0;
    public int subY = 0;

    protected Image display_image;

    public Entity(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public void paint(Graphics g, int blockWidth, int blockHeight) {
        int wx = (blockWidth * x) + subX;
        int wy = (blockHeight * y) + subY;

        g.setColor(Color.BLACK);
        g.fillRect(wx, wy, blockWidth, blockHeight);

        if (display_image != null)
            g.drawImage(display_image, wx, wy, blockWidth, blockHeight, null);
    }
}
