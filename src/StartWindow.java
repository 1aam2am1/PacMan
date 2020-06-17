import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class StartWindow extends JFrame implements ActionListener {
    JButton start;
    JButton settings;
    JButton exit;

    JPanel startPanel;
    GameWindow gamePanel;
    JPanel settingsPanel;
    NamePanel namePanel;

    public StartWindow() {
        super("PackMan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        setResizable(false);

        start = new JButton("Start");
        settings = new JButton("Settings");
        exit = new JButton("Exit");

        settingsPanel = new SettingsWindow();
        startPanel = new JPanel();
        gamePanel = new GameWindow();
        namePanel = new NamePanel();

        start.addActionListener(this);
        settings.addActionListener(this);
        exit.addActionListener(this);
        gamePanel.addActionListener(this);

        startPanel.setLayout(new FlowLayout());
        startPanel.add(start);
        startPanel.add(settings);
        startPanel.add(exit);

        add(startPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            remove(startPanel);
            add(gamePanel);
            gamePanel.start();
            repaint();
            revalidate();
        } else if (e.getSource() == settings) {
            remove(startPanel);
            add(settingsPanel);
            repaint();
            revalidate();
        } else if (e.getSource() == exit) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == gamePanel) {
            //lost
            remove(gamePanel);
            add(namePanel);
            namePanel.setScore(gamePanel.m.points);
            namePanel.requestFocus();
            repaint();
            revalidate();
        }
    }
}
