import java.util.ArrayList;
import java.util.List;

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
}
