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

    protected RICHTUNG convertdirectionBack(int facingangle) {
        RICHTUNG _R;
        switch (facingangle) {
            case 0:
                _R = RICHTUNG.RECHTS;
                break;
            case 1:
                _R = RICHTUNG.UNTEN;
                break;
            case 2:
                _R = RICHTUNG.LINKS;
                break;
            case 3:
                _R = RICHTUNG.OBEN;
                break;

            default:
                _R = RICHTUNG.RECHTS;
                break;
        }
        return _R;
    }
}
