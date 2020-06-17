import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    public List<Image> l = new ArrayList<>();
    public boolean reverse = false;
    private int milliseconds;
    private int w = 0;
    private int run = 0;

    public Animation(String name, String d, int k, int _milliseconds) {
        milliseconds = _milliseconds;

        for (int i = 1; i < k; ++i) {
            try {
                Image picture = ImageIO.read(getClass().getResource(
                        "/resources/" + name + (i + 1) + d + ".gif"));
                l.add(picture);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Image getAnimated(int m) {
        run += m;
        if (run >= milliseconds) {
            run = 0;
            w += 1;
            w %= l.size();
        }

        if (reverse) {
            return l.get(l.size() - w - 1);
        }
        return l.get(w);
    }
}
