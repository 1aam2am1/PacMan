import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Entity {
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
}
