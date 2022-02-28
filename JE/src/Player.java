package src;

import util.Character;
import util.Position;
import util.Tickable;
import util.Position.M_AXIS;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Character implements Tickable {
    private int mundState;
    private int facingangle;// in PI/2 radians 0:right; 1:down; 2:left; 3:up
    private int lives;
    public int GamePoints;
    private int invTimer = 0;
    private SoundManager sManager;

    public Player(Position pos, SoundManager sm) {
        super(pos);
        lives = Setting.Lives;
        GamePoints = 0;
        PM.Point_Text="";
        sManager = sm;
    }

    public void die () {
        lives--;
        if(lives<=0) {
            ticker.setState(false);
            System.out.println("lost");
            sManager.stop();
        } else {
            setPosition(new Position(5,5));
        }
    }

    public void turn(M_AXIS direction, boolean fw) {
        if (facingangle == convertdirection(direction, fw))
            return;
        //
        facingangle = convertdirection(direction, fw);
    }

    public void setPosition(Position pos) {
        super.setPosition(pos);
    }


    public void move() {
        _Richtung _r = convertdirectionBack(facingangle);
        move(_r.Axis, _r.fw);
    }
    @Override
    public void move(M_AXIS direction, boolean fw) {
        super.move(direction, fw);
        int F = Labyrinth.currenLabyrinth.getBesetzung(getX(), getY());
        switch (F) {
            case 1:
            case 5:
                super.move(direction, !fw);
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
                // haben bereits bewegt
        }
        PM.Point_Text=Integer.toString(GamePoints);
        if (invTimer > 0) {
            invTimer--;
        }
    }
    public boolean invincible () {
        return invTimer > 0;
    }
    
    // #Effekte
    private void eatcherry() {
        GamePoints += Setting.Elements.Points.Cherry;
        lives++;
    }

    private void eatpoint() {
        GamePoints += Setting.Elements.Points.Point;
    }

    private void eatinv() {
        invTimer = Setting.invTimer;
        GamePoints += Setting.Elements.Points.inv;
    }

    public void draw(Graphics g) {
        g.setColor((invTimer==0)?Color.yellow:Color.BLUE);
        if (mundState == 1) {
            g.fillArc(getWindowXCoord(), getWindowYCoord(), Setting.Animator.CellHeight,
                    Setting.Animator.CellWidth, -90 * facingangle + 40, 280);
        } else {
            g.fillArc(getWindowXCoord(), getWindowYCoord(), Setting.Animator.CellHeight,
                    Setting.Animator.CellWidth, -90 * facingangle + 15, 330);
        }
        mundState = (mundState + 1) % 2;
        Position eyePosition = getRelativeEyePosition(facingangle);
        g.setColor(Color.black);
        g.fillOval(
                getWindowXCoord() + eyePosition.get(M_AXIS.X) + Setting.Animator.CellWidth / 2 - 2,
                getWindowYCoord() + eyePosition.get(M_AXIS.Y) + Setting.Animator.CellWidth / 2 - 2,
                4, 4); // Auge
    }

    private Position getRelativeEyePosition(int facingangle) {
        int Augenpositionen[][] = {
                { 2, -4 },
                { 4, 2 },
                { -2, -4 },
                { 4, -4 }
        };
        return new Position(Augenpositionen[facingangle][0], Augenpositionen[facingangle][1]);
    }

    private int convertdirection(M_AXIS direction, boolean fw) {
        int _C = 0;
        if (fw) {
            if (direction == M_AXIS.X) {
                _C = 0;
            } else {
                _C = 3;
            }
        } else {
            if (direction == M_AXIS.X) {
                _C = 2;
            } else {
                _C = 1;
            }
        }
        return _C;
    }


    @Override 
    public void tick() {
        move();
    }
}