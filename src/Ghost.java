import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Ghost extends Entity {
    private Image picture;
    private Image scared;

    int px;
    int py;

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
}
