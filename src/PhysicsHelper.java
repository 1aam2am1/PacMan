import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhysicsHelper {
    static public List<Entity.Position> canGo(Node[][] n, int x, int y) {
        List<Entity.Position> result = new ArrayList<>();
        if (x > 0) {
            if (n[x - 1][y] != Node.WALL)
                result.add(Entity.Position.LEFT);
        }
        if ((x + 1) < n.length) {
            if (n[x + 1][y] != Node.WALL)
                result.add(Entity.Position.RIGHT);
        }

        if (y > 0) {
            if (n[x][y - 1] != Node.WALL)
                result.add(Entity.Position.UP);
        }
        if ((y + 1) < n.length) {
            if (n[x][y + 1] != Node.WALL)
                result.add(Entity.Position.DOWN);
        }


        return result;
    }

    static public Entity.Position flip(Entity.Position p) {
        switch (p) {
            case UP -> {
                return Entity.Position.DOWN;
            }
            case DOWN -> {
                return Entity.Position.UP;
            }
            case LEFT -> {
                return Entity.Position.RIGHT;
            }
            case RIGHT -> {
                return Entity.Position.LEFT;
            }
        }

        throw new RuntimeException("Switch should catch");
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = new Random().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static boolean isOverlapping(Entity l, Entity r) {
        double px = (double) l.x + l.subX;
        double py = (double) l.y + l.subY;


        double p2x = (double) r.x + r.subX;
        double p2y = (double) r.y + r.subY;

        // Compute the intersection boundaries
        double interLeft = Math.max(px, p2x);
        double interTop = Math.max(py, p2y);
        double interRight = Math.min(px + 1, p2x + 1);
        double interBottom = Math.min(py + 1, p2y + 1);

        // If the intersection is valid (positive non zero area), then there is an intersection
        return (interLeft < interRight) && (interTop < interBottom);
    }
}
