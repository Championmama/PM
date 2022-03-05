package src;

import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.plaf.InsetsUIResource;

import src.Ghost.GHOSTTYPES;

public class PM extends JFrame {
  public JButton StartButton;

  public static Ticker ticker;

  public static String Point_Text;

  private JTextField eingabe = new JTextField();

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
    //Startbutton deaktivieren
    StartButton.setVisible(false);
    StartButton.setBounds(0, 0, 1, 1);

    // sound definieren
    SoundManager sManager = new SoundManager();


    // GameObjekte definieren
    Labyrinth lab = new Labyrinth();

    Player player = new Player(Setting.StartPosition, sManager);

    Ghost pinky = new Ghost(
      Setting.Elements.GhostSpawns.Spawn[0],
      GHOSTTYPES.PINKY,
      player
    );
    Ghost greeny = new Ghost(
      Setting.Elements.GhostSpawns.Spawn[1],
      GHOSTTYPES.GREENY,
      player
    );
    Ghost limy = new Ghost(
      Setting.Elements.GhostSpawns.Spawn[2],
      GHOSTTYPES.LIMY,
      player
    );
    Ghost orangy = new Ghost(
      Setting.Elements.GhostSpawns.Spawn[3],
      GHOSTTYPES.STRETCHY,
      player
    );

    // GameObjekte bewegbar machen
    ticker = new Ticker(getGraphics());

    Timer timer = new Timer((1000 / Setting.TickRate), null);
    timer.setRepeats(true);
    timer.addActionListener(ticker);

    ticker.attach(lab);
    ticker.attach(player);
    ticker.attach(pinky);
    ticker.attach(greeny);
    ticker.attach(limy);
    ticker.attach(orangy);

    // Inputs warnehmen
    eingabe.addKeyListener(new PMKeyListener(player));
    eingabe.setBounds(0, 0, 1, 1);
    add(eingabe);

    //Fokus setzen
    FocusManager.getCurrentManager().focusNextComponent(this);

    timer.start();
  }
}
