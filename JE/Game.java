import javax.swing.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Game extends JPanel {
    public static Timer s_Timer;
    Image img1 = Toolkit.getDefaultToolkit().getImage("../Spritesheet.png");
    ImageIcon ii = new ImageIcon(img1);

    public Game(int TR) {
        setBackground(new Color(0, 0, 0, 0));
        setSize(ii.getIconWidth(), ii.getIconHeight());
        JLabel label = new JLabel(ii);
        add(label);
        setVisible(true);
        s_Timer = new Timer(1000 / TR, new TimerListener());
        s_Timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img1, 0, 0, this);
    }

    void tick() {

    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tick();
        }
    }
}