package util;

import src.Setting;

public class Position {

  private int m_x;
  private int m_y;
  // m_y ist in einem Koordinatensystem, dass von oben anfängt:
  /*
    (0,0)(1,0)(2,0)
    (0,1)(1,1)(2,1)
    (0,2)(1,2)(2,2)
  */

  public Position(int x, int y) {
    m_x = x;
    m_y = y;
  }

  public void set(AXIS Axis, int value) {
    switch (Axis) {
      case X:
        this.m_x = value;
        break;
      case Y:
        this.m_y = value;
        break;
      default:
    }
  }

  public int get(AXIS Axis) {
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
    }
    return cPos;
  }

  public void move(RICHTUNG direction) {
    
      if (m_x == Setting.width - 1 && direction == RICHTUNG.RECHTS) {
        m_x = 0;
      } else if (m_x == 0 && direction == RICHTUNG.LINKS) {
        m_x = Setting.width - 1;
      } else if (m_y == Setting.height - 1 && direction == RICHTUNG.UNTEN) {
        m_y = 0;
      } else if (m_y == 0 && direction == RICHTUNG.OBEN) {
        m_y = Setting.height - 1;
      } else {
          switch (direction) {
        case OBEN:
          m_y--;
          break;
        case RECHTS:
          m_x++;
          break;
        case LINKS:
          m_x--;
          break;
        case UNTEN:
          m_y++;
          break;
        default:
    }
      }
    
      
  }
}
