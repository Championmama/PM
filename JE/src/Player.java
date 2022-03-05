package src;

import util.AXIS;
import util.Character;
import util.Position;
import util.Tickable;
import util.RICHTUNG;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Character implements Tickable {
    private int m_mundState;
    private int m_facingangle;// in PI/2 radians 0:right; 1:down; 2:left; 3:up
    private int m_lives;
    public int GamePoints;
    private int m_invTimer = 0;
    private SoundManager sManager;

    public Player(Position pos, SoundManager sm) {
        super(pos);
        m_lives = Setting.Lives;
        GamePoints = 0;
        PM.Point_Text="";
        sManager = sm;
    }

    public void die () {
        m_lives--;
        if(m_lives<=0) {
            ticker.setState(false);
            System.out.println("lost");
            sManager.stop();
        } else {
            setPosition(new Position(5,5));
        }
    }

    public void turn(RICHTUNG richtung) {
        if (m_facingangle == convertdirection(richtung))
            return;
        //
        m_facingangle = convertdirection(richtung);
    }


    public void move() {
        RICHTUNG _r = RICHTUNG.convertdirectionBack(m_facingangle);
        move(_r);
    }

    @Override
    public void move(RICHTUNG richtung) {
        //erst bewegen, dann gucken, ob dort ein Block war und wir zurück gehen müssen.
        super.move(richtung);
        int F = Labyrinth.currenLabyrinth.getBesetzung(getX(), getY());
        switch (F) {
            case 1:
            case 5:
                super.move(RICHTUNG.switchdir(richtung));
                break;
            case 2:
                Labyrinth.currenLabyrinth.setBesetzung(getX(), getY(), 0);
                eatcherry();
                break;
            case 3:
                Labyrinth.currenLabyrinth.setBesetzung(getX(), getY(), 0);
                eatpoint();
                break;
            case 4:
                Labyrinth.currenLabyrinth.setBesetzung(getX(), getY(), 0);
                eatinv();
                break;
            default:
                GamePoints += Setting.Elements.Points.empty;
        }
        PM.Point_Text=Integer.toString(GamePoints);
        if (m_invTimer > 0) {
            m_invTimer--;
        }
    }
    public boolean invincible () {
        return m_invTimer > 0;
    }
    
    // #Effekte
    private void eatcherry() {
        GamePoints += Setting.Elements.Points.Cherry;
        m_lives++;
    }

    private void eatpoint() {
        GamePoints += Setting.Elements.Points.Point;
    }

    private void eatinv() {
        m_invTimer = Setting.invTimer;
        GamePoints += Setting.Elements.Points.inv;
    }

    // Position der Augen
    private Position getRelativeEyePosition(int m_facingangle) {
        int Augenpositionen[][] = {
                { 2, -4 },
                { 4, 2 },
                { -2, -4 },
                { 4, -4 }
        };
        return new Position(Augenpositionen[m_facingangle][0], Augenpositionen[m_facingangle][1]);
    }

    // Aus Richtung wird facingangle gemacht
    private int convertdirection(RICHTUNG richtung) {
        int _C;
        switch (richtung) {
            case LINKS:
            _C=2;
                break;
            case OBEN:
            _C=3;
                break;
            case RECHTS:
            _C=0;
                break;
            case UNTEN:
            _C=1;
                break;
            default:
            _C=0; 
        }
        return _C;
    }


    // Interface Methoden
    @Override
    public void draw(Graphics g) {
        g.setColor((m_invTimer==0)?Color.yellow:Color.BLUE);
        if (m_mundState == 1) {
            g.fillArc(getWindowXCoord(), getWindowYCoord(), Setting.Animator.CellHeight,
                    Setting.Animator.CellWidth, -90 * m_facingangle + 40, 280);
        } else {
            g.fillArc(getWindowXCoord(), getWindowYCoord(), Setting.Animator.CellHeight,
                    Setting.Animator.CellWidth, -90 * m_facingangle + 15, 330);
        }
        m_mundState = (m_mundState + 1) % 2;
        Position eyePosition = getRelativeEyePosition(m_facingangle);
        g.setColor(Color.black);
        g.fillOval(
                getWindowXCoord() + eyePosition.get(AXIS.X) + Setting.Animator.CellWidth / 2 - 2,
                getWindowYCoord() + eyePosition.get(AXIS.Y) + Setting.Animator.CellWidth / 2 - 2,
                4, 4); // Auge
    }

    @Override 
    public void tick() {
        move();
    }
}