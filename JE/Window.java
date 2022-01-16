import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

import javax.swing.*;

public class Window extends JFrame {
    static int s_width;
    static int s_height;
    static int s_TR;
    private JButton p_StartButton = new JButton("Start");
    public static Game m_Game;

    private void startGame() {
        if (m_Game == null) {

            System.out.println("Spiel initialisiert");
            m_setVisible(p_StartButton, false);
            m_Game = new Game(s_TR);
            add(m_Game);

        } else
            System.out.println("Achtung, alle Panik schieben, Spiel gibt es schon");

    }

    public Window(int width, int height, int TR) {
        super();
        s_width = width;
        s_height = height;
        s_TR=TR;
        // init Window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(s_width, s_height);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("PM");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);

        // Anfang Komponenten
        p_StartButton.setBounds((s_width - 75) / 2, (s_height - 25) / 2, 75, 25);
        add(p_StartButton);
        p_StartButton.addActionListener(new StartGameActionListener());
    }

    private class StartGameActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            startGame();
        }
    }

    public void WindowVisible(boolean... active) {
        setVisible(active.length > 0 ? active[0] : true);
    }

    static <T extends JComponent> void m_setVisible(T Comp, boolean visibility) {
        Comp.setVisible(visibility);
    }
}
