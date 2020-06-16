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
    JPanel settingsPanel;

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

        start.addActionListener(this);
        settings.addActionListener(this);
        exit.addActionListener(this);

        startPanel.setLayout(new FlowLayout());
        startPanel.add(start);
        startPanel.add(settings);
        startPanel.add(exit);

        add(startPanel);
        startPanel.setBounds(0,0,500,500);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {

        } else if (e.getSource() == settings) {
            remove(startPanel);
            add(settingsPanel);
            settingsPanel.setBounds(0, 0, 500, 500);
        } else if (e.getSource() == exit) {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
