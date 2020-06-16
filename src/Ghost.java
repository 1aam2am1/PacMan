import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Ghost extends Entity {
    private Image picture;
    private Image scared;

    public Ghost(int _x, int _y, int i) {
        super(_x, _y);

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
}
