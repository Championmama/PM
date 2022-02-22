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
      startGame();
    }
  }

  private void startGame() {
    StartButton.setVisible(false);
    StartButton.setBounds(0, 0, 1, 1);

    timer = new Timer((int) (1000 / Setting.TickRate), null);
    timer.setRepeats(true);
    g = getGraphics();
    
    //Definiere alle Objekte
    player = new Player(Setting.StartPosition);

    Ghost Pinky = new Ghost(new Position(1, 1), GHOSTTYPES.PINKY, player);
    Ghost Greeny = new Ghost(new Position(1, 2), GHOSTTYPES.GREENY, player);
    Ghost Limy = new Ghost(new Position(1, 3), GHOSTTYPES.LIMY, player);
    Ghost Stretchy = new Ghost(new Position(1, 4), GHOSTTYPES.STRETCHY, player);

    Ghost[] ghosts = { Pinky, Greeny, Limy, Stretchy };
    m_ghosts = ghosts;

    eingabe.addKeyListener(new PMKeyListener(player));
    eingabe.setBounds(0, 0, 1, 1);
    add(eingabe);

    Animator anim = new Animator(g, player, ghosts);

    ticker = new Ticker(anim);
    ticker.attach(Labyrinth.currenLabyrinth);
    ticker.attach(player);
    ticker.attach(Pinky);
    ticker.attach(Greeny);
    ticker.attach(Limy);
    ticker.attach(Stretchy);
    timer.addActionListener(ticker);

    FocusManager.getCurrentManager().focusNextComponent(this);
    
    System.out.println("start");
    timer.start();
  }
}