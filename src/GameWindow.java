import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.List;

public class GameWindow extends JPanel {
    final Lock lock = new ReentrantLock();

    boolean pause = false;

    Maze m;
    Timer gameTimer;

    private final List<java.awt.event.ActionListener> actions = new ArrayList<>();

    public GameWindow() {
        setBackground(Color.BLACK);
        setFocusable(true);

        loadRandom();
    }

    public void start() {
        requestFocus(true);

        if (!gameTimer.isRunning()) {
            gameTimer.start();
        }
    }

    public void stop() {
        gameTimer.stop();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        lock.lock();
        try {
            m.Width = getWidth();
            m.Height = getHeight();
            m.paint(g);
        } finally {
            lock.unlock();
        }
    }

    private void loadRandom() {
        if (m != null) {
            for (var k : getKeyListeners())
                removeKeyListener(k);
        }
        m = new Maze();

        try {
            Scanner o = new Scanner(new File("map" +
                    (new Random().nextInt(3) + 1) + ".txt"));

            m.LoadMaze(o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Entity e : m.ghosts) {
            if (e instanceof Player)
                addKeyListener((Player) e);
        }

        if (gameTimer != null) {
            gameTimer.stop();
        }

        gameTimer = new Timer(15, e -> action());
    }

    public void action() {
        lock.lock();
        try {
            if (pause)
                return;
            int result = m.update();

            if (result == 1) {
                stop();
                loadRandom();
                start();
            } else if (result == -1) {
                stop();
                EventQueue.invokeLater(this::makeAction);
                //lose
            }


            repaint();
        } finally {
            lock.unlock();
        }
    }

    public void addActionListener(java.awt.event.ActionListener l) {
        actions.add(l);
    }

    private void makeAction() {
        for (var e : actions) {
            e.actionPerformed(new ActionEvent(this, 0, ""));
        }
    }
}
