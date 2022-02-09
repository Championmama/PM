package util;

import src.Labyrinth;
import src.Setting;

public class Position {
    private int m_x;
    private int m_y;

    public Position(int x, int y) {
        m_x = x;
        m_y = y;
    }

    public void set(M_Axis Axis, int value) {
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

    public int get(M_Axis Axis) {
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

    public void move(M_Axis direction, boolean fw) {
        if (m_x == Labyrinth.m_width-1 && direction == M_Axis.X && fw) {
            m_x = !Setting.Loop?Labyrinth.m_width-1:0;
        } else if (m_x == 0 && direction == M_Axis.X && !fw) {
            m_x = Setting.Loop?Labyrinth.m_width-1:0;
        } else if (m_y == Labyrinth.m_height-1 && direction == M_Axis.Y && !fw) {
            m_y = !Setting.Loop?Labyrinth.m_height-1:0;;
        } else if (m_y == 0 && direction == M_Axis.Y && fw) {
            m_y = Setting.Loop?Labyrinth.m_height-1:0;
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

    public enum M_Axis {
        X, Y
    }

    public void Print() {
        System.out.println("X: " + m_x + " | y: " + m_y);
    }
}
