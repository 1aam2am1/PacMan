import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
    public int Height = 500;
    public int Width = 500;
    private static final int DOT_HEIGHT = 3;
    private static final int DOT_WIDTH = 3;

    private Node[][] maze;

    public Maze() {
        try {
            Scanner o = new Scanner(new File("map1.txt"));

            LoadMaze(o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void paint(Graphics g) {
        int blockHeight = Height / (maze.length);
        int blockWidth = Width / (maze[0].length);

        int y = 0;
        for (Node[] nodes : maze) {
            int x = 0;
            for (Node node : nodes) {
                if (node == Node.WALL) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, blockWidth, blockHeight);
                } else if (node == Node.DOT) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, blockWidth, blockHeight);
                    g.setColor(Color.YELLOW);
                    g.fillRect(x + (blockWidth / 2), y + (blockHeight / 2),
                            DOT_WIDTH, DOT_HEIGHT);
                } else if (node == Node.PACMAN) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, blockWidth, blockHeight);
                    g.setColor(Color.YELLOW);
                    ///animate(g, pacMan, x, y);
                } else if (node == Node.GHOST) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, blockWidth, blockHeight);
                    g.setColor(Color.YELLOW);
                    ///Player ghost = GetGhost(i,j);
                    ///animate(g, ghost, x, y);
                } else if (node == Node.BLANK) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, blockWidth, blockHeight);
                }
                x += blockWidth;
            }
            y += blockHeight;
        }
    }

    public void LoadMaze(Scanner o) {
        int x = o.nextInt();
        int y = o.nextInt();
        maze = new Node[x][y];

        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                int temp = o.nextInt();

                if (temp == Node.BLANK.v) {
                    maze[i][j] = Node.BLANK;
                } else if (temp == Node.DOT.v) {
                    maze[i][j] = Node.DOT;
                } else if (temp == Node.WALL.v) {
                    maze[i][j] = Node.WALL;
                } else if (temp == Node.PACMAN.v) {
                    maze[i][j] = Node.PACMAN;
                } else if (temp == Node.GHOST.v) {
                    maze[i][j] = Node.GHOST;
                } else if (temp == Node.POWER_UP.v) {
                    maze[i][j] = Node.POWER_UP;
                }
            }
        }
    }
}
