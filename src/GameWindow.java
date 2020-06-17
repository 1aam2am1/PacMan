import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GameWindow extends JPanel {
    final Lock lock = new ReentrantLock();

    boolean pause = false;

    Maze m;
    Timer gameTimer;

    public GameWindow() {
        setBackground(Color.BLACK);

        m = new Maze();

        for (Entity e : m.ghosts) {
            if (e.getClass().isAssignableFrom(Player.class))
                addKeyListener((Player) e);
        }

        gameTimer = new Timer(15, e -> action());
    }

    public void start() {
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

        m.Width = getWidth();
        m.Height = getHeight();
        m.paint(g);
    }

    public void action() {
        lock.lock();
        try {
            if (pause)
                return;
            int result = m.update();

            if (result == 1) {
                stop();
                //win
            } else if (result == -1) {
                stop();
                //lose
            }


            repaint();

            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
