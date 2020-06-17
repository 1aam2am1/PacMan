import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {
    public int Height = 500;
    public int Width = 500;
    private static final int DOT_HEIGHT = 3;
    private static final int DOT_WIDTH = 3;

    private Node[][] maze;
    public List<Entity> ghosts = new ArrayList<>();

    private final int dotPoints = 10;
    private final int powerUpPoints = 50;
    private final int ghostPoints = 200;
    public int points = 0;


    public Maze() {

    }

    public void paint(Graphics g) {
        int blockHeight = Height / (maze[0].length);
        int blockWidth = Width / (maze.length);

        int x = 0;
        for (Node[] nodes : maze) {
            int y = 0;
            for (Node node : nodes) {
                switch (node) {
                    case WALL -> {
                        g.setColor(Color.BLUE);
                        g.fillRect(x, y, blockWidth, blockHeight);
                    }
                    case DOT -> {
                        g.setColor(Color.BLACK);
                        g.fillRect(x, y, blockWidth, blockHeight);
                        g.setColor(Color.YELLOW);
                        g.fillRect(x + (blockWidth / 2) - (DOT_WIDTH / 2), y + (blockHeight / 2) - (DOT_HEIGHT / 2),
                                DOT_WIDTH, DOT_HEIGHT);
                    }
                    case BLANK -> {
                        g.setColor(Color.BLACK);
                        g.fillRect(x, y, blockWidth, blockHeight);
                    }
                    case POWER_UP -> {
                        g.setColor(Color.BLACK);
                        g.fillRect(x, y, blockWidth, blockHeight);
                        g.setColor(Color.YELLOW);
                        g.fillRect(x + (blockWidth / 2) - DOT_WIDTH, y + (blockHeight / 2) - DOT_HEIGHT,
                                2 * DOT_WIDTH, 2 * DOT_HEIGHT);
                    }
                }
                y += blockHeight;
            }
            x += blockWidth;
        }

        for (Entity e : ghosts) {
            e.paint(g, blockWidth, blockHeight);
        }
    }

    public void LoadMaze(@NotNull Scanner o) {
        int x = o.nextInt();
        int y = o.nextInt();
        maze = new Node[x][y];

        int k = 0;
        for (int j = 0; j < y; ++j) {
            for (int i = 0; i < x; ++i) {
                int temp = o.nextInt();

                if (temp == Node.BLANK.v) {
                    maze[i][j] = Node.BLANK;
                } else if (temp == Node.DOT.v) {
                    maze[i][j] = Node.DOT;
                } else if (temp == Node.WALL.v) {
                    maze[i][j] = Node.WALL;
                } else if (temp == Node.PACMAN.v) {
                    ghosts.add(new Player(i, j));
                    maze[i][j] = Node.BLANK;
                } else if (temp == Node.GHOST.v) {
                    ghosts.add(new Ghost(i, j, k));
                    k++;
                    maze[i][j] = Node.BLANK;
                } else if (temp == Node.POWER_UP.v) {
                    maze[i][j] = Node.POWER_UP;
                }
            }
        }
    }

    public int update() {
        boolean dots = false;
        for (Node[] nodes : maze) {
            for (Node node : nodes) {
                if (node == Node.DOT) {
                    dots = true;
                    break;
                }
            }
        }

        if (!dots) {
            return 1;
        }

        for (Entity e : ghosts) {
            e.updatePosition(maze, 15);
        }

        Player p = null;
        for (Entity e : ghosts) {
            if (e instanceof Player)
                p = (Player) e;
        }

        if (p == null) {
            return -1;
        }

        for (Entity e : ghosts) {
            if (e instanceof Ghost) {
                if (PhysicsHelper.isOverlapping(e, p)) {
                    Ghost g = (Ghost) e;
                    if (g.display_image == g.scared) {
                        ghosts.remove(e);
                        points += ghostPoints;
                        break;
                    } else {
                        return -1;
                    }
                }
            }
        }

        int nx = p.x;
        int ny = p.y;
        if (Math.abs(p.subX) >= 0.5 || Math.abs(p.subY) >= 0.5) {
            nx += Math.signum(p.subX);
            ny += Math.signum(p.subY);
        }

        if (maze[nx][ny] == Node.DOT) {
            maze[nx][ny] = Node.BLANK;
            points += dotPoints;
        }

        if (maze[nx][ny] == Node.POWER_UP) {
            maze[nx][ny] = Node.BLANK;
            points += powerUpPoints;
            makeScared();
        }

        return 0;
    }


    void makeScared() {
        for (Entity e : ghosts) {
            if (e instanceof Ghost) {
                Ghost g = (Ghost) e;

                g.scaredTime += g.scaredTimeMax;
            }
        }
    }
}
