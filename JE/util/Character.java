package util;
import src.Setting;
import util.Position.M_AXIS;


public class Character{
    protected Position p_Position;

    public Character(Position StartPosition) {
        super();
        p_Position=StartPosition;
    }

    public void move(M_AXIS direction, boolean fw) {
        p_Position.move(direction, fw);
    }
    
    protected void setPosition(Position pos) {
        p_Position = pos;
    }
    public Position getPosition() {
        return p_Position;
    }
    public int getX() {
        return p_Position.get(M_AXIS.X);
    }
    public int getY() {
        return p_Position.get(M_AXIS.Y);
    }

    public int getWindowXCoord() {
        return Setting.Animator.outmargin + 7 + getX() * (Setting.Animator.CellWidth + Setting.Animator.inmargin);
    }
    public int getWindowYCoord() {
        return Setting.Animator.outmargin + 30 + getY() * (Setting.Animator.CellHeight + Setting.Animator.inmargin);
    }

    protected class _Richtung {
        public M_AXIS Axis;
        public boolean fw;

        public _Richtung(M_AXIS A, boolean fw) {
            Axis = A;
            this.fw = fw;
        }
    }

    protected _Richtung convertdirectionBack(int facingangle) {
        _Richtung _R;
        switch (facingangle) {
            case 0:
                _R = new _Richtung(M_AXIS.X, true);
                break;
            case 1:
                _R = new _Richtung(M_AXIS.Y, false);
                break;
            case 2:
                _R = new _Richtung(M_AXIS.X, false);
                break;
            case 3:
                _R = new _Richtung(M_AXIS.Y, true);
                break;

            default:
                _R = new _Richtung(M_AXIS.X, true);
                break;
        }
        return _R;
    }
}
