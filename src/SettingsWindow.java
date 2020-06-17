import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JPanel {
    public SettingsWindow(){
        setBackground(Color.BLACK);

        var panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(panel);
        setPreferredSize(new Dimension(500, 500));
        add(scrollPane, BorderLayout.CENTER);


        panel.setLayout(new GridLayout(Score.getImplementation().li.size(), 2));
        for (var e : Score.getImplementation().li) {
            panel.add(new JLabel(e.name));
            panel.add(new JLabel(String.valueOf(e.score)));
        }
    }
}
