import java.awt.*;
import java.util.Vector;

public class Entity {
    public int x;
    public int y;

    public double subX = 0;
    public double subY = 0;

    public double speed = 3;

    protected Image display_image;

    public Position destiny = PhysicsHelper.randomEnum(Position.class);
    protected Position latest = destiny;

    public void update(Node[][] n) {

    }

    public void updatePosition(Node[][] n, int milliseconds) {
        if (latest != destiny && Math.abs(subX) < 0.1 && Math.abs(subY) < 0.1) {
            subX = 0;
            subY = 0;

            latest = destiny;
        } else if (latest == PhysicsHelper.flip(destiny)) {
            latest = destiny;
        }


        if (PhysicsHelper.canGo(n, x, y).contains(latest) || (Math.signum(latest.x) != Math.signum(subX) ||
                Math.signum(latest.y) != Math.signum(subY))) {
            subX += speed * latest.x * milliseconds / 1000;
            subY += speed * latest.y * milliseconds / 1000;
        }

        ///should not exists
        if(!PhysicsHelper.canGo(n, x, y).contains(latest)){
            update(n);
        }

        if (Math.abs(subX) >= 1) {
            x += Math.signum(subX);
            subX = 0;

            update(n);
        }
        if (Math.abs(subY) >= 1) {
            y += Math.signum(subY);
            subY = 0;

            update(n);
        }
    }

    public Entity(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public void paint(Graphics g, int blockWidth, int blockHeight) {
        int wx = (blockWidth * x) + (int) (blockWidth * subX);
        int wy = (blockHeight * y) + (int) (blockHeight * subY);

        g.setColor(Color.BLACK);
        g.fillRect(wx, wy, blockWidth, blockHeight);

        if (display_image != null)
            g.drawImage(display_image, wx, wy, blockWidth, blockHeight, null);
    }

    enum Position {
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP(0, -1),
        DOWN(0, 1);

        public double x;
        public double y;

        Position(double _x, double _y) {
            x = _x;
            y = _y;
        }
    }
}
