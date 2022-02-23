package src;

import util.Character;
import util.Position;
import util.Tickable;
import util.Position.M_Axis;

import java.awt.Color;
import java.awt.Graphics;

public class Ghost extends Character implements Tickable{
    public static enum GHOSTTYPES {
        PINKY, GREENY, LIMY, STRETCHY
    }

    public GHOSTTYPES Type;

    public Ghost(Position StartPosition, GHOSTTYPES GhostColor, Player player) {
        super(StartPosition);
        Type = GhostColor;
        target = player;
        targetPosition = player.getPosition();
        fairnessTimer = Setting.FaitnessTimer/2;
    }

    private Color getColor() {
        switch (Type) {
            case GREENY:
                return Color.GREEN;

            case LIMY:
                return Color.CYAN;

            case PINKY:
                return Color.PINK;

            case STRETCHY:
                return Color.ORANGE;
            default:
                return Color.WHITE;

        }
    }

    private Player target;
    private Position targetPosition;

    public void setTarget(Position s) {
        targetPosition = s;
    }

    public void die() {
        targetPosition = Setting.Elements.GhostSpawns.Spawn[Type.ordinal()];
        fairnessTimer = Setting.FaitnessTimer;
    }

    private int fairnessTimer;

    public void move() {
        if(fairnessTimer > 0) {
            fairnessTimer--;
            return;
        }
        targetPosition=target.getPosition();
        int directionR;

        float dx = targetPosition.get(M_Axis.X) - getX() + (float)(Math.random()-0.5)*5;
        float dy = targetPosition.get(M_Axis.Y) - getY() + (float)(Math.random()-0.5)*5;
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx >= 0) {
                directionR = 0;
            } else {
                directionR = 2;
            }
        } else {
            if (dy < 0) {
                directionR = 3;
            } else {
                directionR = 1;
            }
        }
        boolean movable = false;

        M_Axis direction;
        boolean fw;
        while (!movable) {
            ret _S = convertdirectionBack(directionR % 4);
            direction = _S.Axis;
            fw = _S.fw;
            super.move(direction, fw);
            int F = Labyrinth.currenLabyrinth.getBesetzung(getX(), getY());
            if (F == 1) {
                super.move(direction, !fw);
                movable = false;
                directionR++;
            } else {
                movable = true;
            }
        }
    }

    public void draw(Graphics g) {
        
        g.setColor(getColor());

        g.fillRect(getWindowXCoord(), getWindowYCoord() + Setting.Animator.CellHeight / 2,
                Setting.Animator.CellWidth, 3 * Setting.Animator.CellHeight / 8);
        g.fillArc(getWindowXCoord(), getWindowYCoord(), Setting.Animator.CellWidth, Setting.Animator.CellHeight, 0,
                180);

        g.fillRect(getWindowXCoord(), getWindowYCoord() + 3 * Setting.Animator.CellHeight / 4,
                Setting.Animator.CellWidth / 5, Setting.Animator.CellHeight / 4);
        g.fillRect(getWindowXCoord() + 2 * Setting.Animator.CellWidth / 5,
                getWindowYCoord() + 3 * Setting.Animator.CellHeight / 4, Setting.Animator.CellWidth / 5,
                Setting.Animator.CellHeight / 4);
        g.fillRect(getWindowXCoord() + 4 * Setting.Animator.CellWidth / 5,
                getWindowYCoord() + 3 * Setting.Animator.CellHeight / 4, Setting.Animator.CellWidth / 5,
                Setting.Animator.CellHeight / 4);
    }

    @Override 
    public void tick() {
        move();
        if(getX() == target.getX() && getY() == target.getY()) {
            if(target.invincible()) {die();} else target.die();
        }
    }

}
