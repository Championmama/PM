package src;

import java.awt.Graphics;
import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.plaf.InsetsUIResource;

import src.Ghost.GHOSTTYPES;

public class PM extends JFrame {
  public static JButton StartButton;
  public static Ticker ticker;
  private Timer timer;
  private JTextField eingabe = new JTextField();
  private Graphics g;
  private Player player;
  public static String Point_Text;
  Ghost[] m_ghosts;

  public PM() {
    super();
    this.pack();
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
    StartButton.addActionListener(new ButtonListener(this));
    add(StartButton);

  }

  void startGame() {
    StartButton.setVisible(false);
    StartButton.setBounds(0, 0, 1, 1);

    timer = new Timer((int) (1000 / Setting.TickRate), null);
    timer.setRepeats(true);
    g = getGraphics();

    // Definiere alle Objekte
    SoundManager sManager = new SoundManager(this);
    player = new Player(Setting.StartPosition, sManager);

    Ghost Pinky = new Ghost(Setting.Elements.GhostSpawns.Spawn[0], GHOSTTYPES.PINKY, player);
    Ghost Greeny = new Ghost(Setting.Elements.GhostSpawns.Spawn[1], GHOSTTYPES.GREENY, player);
    Ghost Limy = new Ghost(Setting.Elements.GhostSpawns.Spawn[2], GHOSTTYPES.LIMY, player);
    Ghost Stretchy = new Ghost(Setting.Elements.GhostSpawns.Spawn[3], GHOSTTYPES.STRETCHY, player);

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