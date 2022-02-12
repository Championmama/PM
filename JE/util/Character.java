package util;
import src.Setting;
import util.Position.M_Axis;


public class Character extends Tickable{
    protected Position m_Position;

    //Prefered Option
    public Character(Position StartPosition) {
        super();
        m_Position=StartPosition;
    }

    public Character(int x, int y) {
        new Character(new Position(x, y));
    }

    public void move(M_Axis direction, boolean fw) {
        m_Position.move(direction, fw);
    }
    protected void setPosition(Position pos) {
        m_Position = pos;
    }
    public Position getPosition() {
        return m_Position;
    }
    public int getX() {
        return m_Position.get(M_Axis.X);
    }
    public int getY() {
        return m_Position.get(M_Axis.Y);
    }

    public int getWindowXCoord() {
        return Setting.Animator.outmargin + 7 + getX() * (Setting.Animator.CellWidth + Setting.Animator.inmargin);
    }
    public int getWindowYCoord() {
        return Setting.Animator.outmargin + 30 + getY() * (Setting.Animator.CellHeight + Setting.Animator.inmargin);
    }

    protected class ret {
        public M_Axis Axis;
        public boolean fw;

        public ret(M_Axis A, boolean fw) {
            Axis = A;
            this.fw = fw;
        }
    }

    protected ret convertdirectionBack(int facingangle) {
        ret _R;
        switch (facingangle) {
            case 0:
                _R = new ret(M_Axis.X, true);
                break;
            case 1:
                _R = new ret(M_Axis.Y, false);
                break;
            case 2:
                _R = new ret(M_Axis.X, false);
                break;
            case 3:
                _R = new ret(M_Axis.Y, true);
                break;

            default:
                _R = new ret(M_Axis.X, true);
                break;
        }
        return _R;
    }

    @Override
    public void tick() {
        System.out.println("missing Implementation for tick() function");
    }
}
