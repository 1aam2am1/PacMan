import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Ghost extends Entity {
    public final int scaredTimeMax = 9000;
    public Image picture;

    int px;
    int py;
    public Image scared;
    public int scaredTime = 0;

    public Ghost(int _x, int _y, int i) {
        super(_x, _y);
        px = _x;
        py = _y;

        i %= 2;
        i += 1;
        try {
            picture = ImageIO.read(getClass().getResource(
                    "/resources/Ghost" + i + ".gif"));

            scared = ImageIO.read(getClass().getResource(
                    "/resources/GhostScared" + i + ".gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        display_image = picture;
    }

    @Override
    public void update(Node[][] n) {
        var capabilities = PhysicsHelper.canGo(n, x, y);

        if (capabilities.size() != 1) {
            capabilities.remove(PhysicsHelper.flip(destiny));
        }

        if (capabilities.contains(destiny)) {
            capabilities.remove(destiny);

            int choice = new Random().nextInt(capabilities.size() + 6);

            if (choice >= 6) {
                choice -= 6;
                destiny = capabilities.get(choice);
            }
        } else {
            int choice = new Random().nextInt(capabilities.size());

            destiny = capabilities.get(choice);
        }
    }

    @Override
    public void updatePosition(Node[][] n, int milliseconds) {
        super.updatePosition(n, milliseconds);

        if (scaredTime > 0) {
            scaredTime -= milliseconds;
        }
        if (scaredTime < 0) {
            scaredTime = 0;
        }

        if (scaredTime > 0) {
            display_image = scared;
        } else {
            display_image = picture;
        }
    }
}
