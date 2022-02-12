package src;

import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.plaf.InsetsUIResource;

import src.Ghost.GHOSTTYPES;
import util.Position;

public class PM extends JFrame {
  public static JButton StartButton;
  public static Ticker ticker;
  private Timer timer;
  private JTextField eingabe = new JTextField();
  private Graphics g;
  private Player player;
  Ghost[] m_ghosts;

  public PM() {
    super();

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    int frameWidth = 700;
    int frameHeight = 500;

    setSize(frameWidth, frameHeight);
    setLocationRelativeTo(null);
    setTitle("Pacman");
    setResizable(false);
    setVisible(true);

    StartButton = new JButton("Start");
    StartButton.setMargin(new InsetsUIResource(4, 4, 4, 4));
    StartButton.setBounds(frameWidth / 2 - 50, frameHeight / 2 - 15, 100, 30);
    StartButton.setVisible(true);
    StartButton.addActionListener(new ButtonListener());
    add(StartButton);

  }

  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      StartButton.setVisible(false);
      StartButton.setBounds(0, 0, 1, 1);
      StartButton.removeActionListener(this);

      timer = new Timer((int)(1000/Setting.TickRate), null);
      timer.setRepeats(true);
      timer.start();
      g = getGraphics();
      ticker = new Ticker();
      startGame();
      timer.addActionListener(ticker);
    }
  }
  private void startGame() {
    player = new Player(Setting.StartPosition);

    Ghost Pinky = new Ghost(      new Position(1, 1),GHOSTTYPES.PINKY, player);
    Ghost Greeny = new Ghost(     new Position(1, 2), GHOSTTYPES.GREENY, player);
    Ghost Limy = new Ghost(       new Position(1, 3), GHOSTTYPES.LIMY, player);
    Ghost Stretchy = new Ghost(   new Position(1, 4), GHOSTTYPES.STRETCHY, player);

    Ghost[] ghosts = {Pinky, Greeny, Limy, Stretchy};
    m_ghosts = ghosts;


    eingabe.addKeyListener(new PMKeyListener(player));
    eingabe.setBounds(0, 0, 1, 1);
    add(eingabe);


    new Animator(g, player, ghosts);
    System.out.println("start");
    FocusManager.getCurrentManager().focusNextComponent(this);
  }
}