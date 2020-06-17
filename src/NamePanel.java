import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NamePanel extends JPanel implements ActionListener {
    JTextField name;
    JLabel score;

    private int p;

    public NamePanel() {
        setFocusable(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        score = new JLabel();
        name = new JTextField();
        var b = new JButton("Save Score");

        score.setHorizontalTextPosition(JLabel.CENTER);
        b.addActionListener(this);

        add(score);
        add(name);
        add(b);
    }

    public void setScore(int s) {
        p = s;

        score.setText("Score: " + p);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Score.getImplementation().addScore(name.getText(), p);
    }
}
