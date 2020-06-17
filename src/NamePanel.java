import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class NamePanel extends JPanel implements ActionListener {
    JTextField name;
    JLabel score;

    private final List<ActionListener> actions = new ArrayList<>();

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

        EventQueue.invokeLater(this::makeAction);
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
