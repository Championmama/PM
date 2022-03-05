package util;
import src.Setting;


public class Character{
    protected Position p_Position;

    public Character(Position StartPosition) {
        super();
        p_Position=StartPosition;
    }

    public void move(RICHTUNG direction) {
        p_Position.move(direction);
    }
    
    protected void setPosition(Position pos) {
        p_Position = pos;
    }
    public Position getPosition() {
        return p_Position;
    }
    public int getX() {
        return p_Position.get(AXIS.X);
    }
    public int getY() {
        return p_Position.get(AXIS.Y);
    }

    public int getWindowXCoord() {
        return Setting.Animator.outmargin + 7 + getX() * (Setting.Animator.CellWidth + Setting.Animator.inmargin);
    }
    public int getWindowYCoord() {
        return Setting.Animator.outmargin + 30 + getY() * (Setting.Animator.CellHeight + Setting.Animator.inmargin);
    }
}
