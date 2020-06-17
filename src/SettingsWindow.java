import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JPanel {
    JPanel panel;

    public SettingsWindow() {
        setBackground(Color.BLACK);

        panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel);
        setPreferredSize(new Dimension(500, 500));
        add(scrollPane, BorderLayout.CENTER);

        var clear = new JButton("Clear");
        clear.addActionListener(l -> {
            Score.getImplementation().li.clear();
            Score.getImplementation().saveScore();
            repaint();
            revalidate();
        });

        add(clear);

        panel.setLayout(new GridBagLayout());
        load();
    }

    public void load() {
        panel.removeAll();

        int y = 0;
        for (var e : Score.getImplementation().li) {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = y;
            panel.add(new JLabel(e.name), c);

            c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = y;
            panel.add(new JLabel(String.valueOf(e.score)), c);

            ++y;
        }
    }
}
