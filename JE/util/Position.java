package util;

import src.Setting;

public class Position {
    private int m_x;
    private int m_y;

    public Position(int x, int y) {
        m_x = x;
        m_y = y;
    }

    public void set(M_AXIS Axis, int value) {
        switch (Axis) {
            case X:
                this.m_x = value;
                break;
            case Y:
                this.m_y = value;
                break;
            default:
                System.err.println("Axis was not part of the enum");

        }
    }

    public int get(M_AXIS Axis) {
        int cPos;
        switch (Axis) {
            case X:
                cPos = this.m_x;
                break;
            case Y:
                cPos = this.m_y;
                break;
            default:
                cPos = 0;
                System.err.println("Axis was not part of the enum");

        }
        return cPos;
    }

    public void move(M_AXIS direction, boolean fw) {
        if (m_x == Setting.width-1 && direction == M_AXIS.X && fw) {
            m_x = !Setting.Loop?Setting.width-1:0;
        } else if (m_x == 0 && direction == M_AXIS.X && !fw) {
            m_x = Setting.Loop?Setting.width-1:0;
        } else if (m_y == Setting.height-1 && direction == M_AXIS.Y && !fw) {
            m_y = !Setting.Loop?Setting.height-1:0;;
        } else if (m_y == 0 && direction == M_AXIS.Y && fw) {
            m_y = Setting.Loop?Setting.height-1:0;
        } else {
            switch (direction) {
                case X:
                    m_x += fw ? 1 : -1;
                    break;
                case Y:
                    // umgekehrt weil die y-Koordinate von oben beginnt
                    m_y += fw ? -1 : 1;
                    break;
            }
        }
    }

    public enum M_AXIS {
        X, Y
    }
}
