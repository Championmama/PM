package src;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Animator {
    
    public static Setting.Animator settings;
    private static Graphics m_g;
    private static Player m_player;
    private static ImageIcon img;
    private static Labyrinth lab;
    private static Ghost[] m_ghosts;
    public static boolean Running = true;

    public Animator(Graphics g, Player p , Ghost[] ghosts) {
        m_player = p;
        m_ghosts = ghosts;
        m_g = g;
        img = new ImageIcon("assets/cherry.jpg");
        lab = new Labyrinth();
        lab.setActive();
    }

    public static void zeichne() {
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 20; y++) {
                switch (lab.getBesetzung(x, y)) {
                    case 1: // Wall
                        m_g.setColor(Color.black);
                        m_g.fillRect(
                                getWindowXCoord(x),
                                getWindowYCoord(y),
                                Setting.Animator.CellWidth,
                                Setting.Animator.CellHeight);
                        m_g.setColor(new Color(0x010160));
                        m_g.fillRect(
                                getWindowXCoord(x),
                                getWindowYCoord(y),
                                Setting.Animator.CellWidth,
                                1);
                        m_g.fillRect(
                                getWindowXCoord(x),
                                getWindowYCoord(y),
                                1,
                                Setting.Animator.CellHeight);

                        break;
                    case 2: // Cherry
                        drawBG(x, y);
                        if (img.getImage() == null)
                            System.err.println("image not loaded");
                        m_g.drawImage(img.getImage(),
                                getWindowXCoord(x),
                                getWindowYCoord(y), null);
                        break;
                    case 3: // Point
                        drawPointwBG(x, y, Color.orange);
                        break;
                    case 4:// inv
                        drawPointwBG(x, y, Color.white);
                        break;
                    case 5:// door
                        drawBG(x, y);
                        m_g.setColor(new Color(0xffb1ff));
                        m_g.fillRect(getWindowXCoord(x),
                                getWindowYCoord(y) + Setting.Animator.CellHeight / 2-2,
                                Setting.Animator.CellWidth, 4);
                        break;
                    default:
                        drawBG(x, y);

                } // end of switch
            } // end of for
        } // end of for
        for(Ghost _G :m_ghosts) {
            _G.draw(m_g);
        }
        m_player.draw(m_g);
    }

    public static int getWindowXCoord(int x) {
        return Setting.Animator.outmargin + 7 + x * (Setting.Animator.CellWidth + Setting.Animator.inmargin);
    }

    public static int getWindowYCoord(int y) {
        return Setting.Animator.outmargin + 30 + y * (Setting.Animator.CellHeight + Setting.Animator.inmargin);
    }

    public static void drawBG(int x, int y) {
        m_g.setColor(new Color(0x404040));
        m_g.fillRect(
                getWindowXCoord(x),
                getWindowYCoord(y),
                Setting.Animator.CellWidth, Setting.Animator.CellHeight);
    }

    private static void drawPointwBG(int x, int y, Color c) {
        drawBG(x, y);
        m_g.setColor(c);
        m_g.fillOval(
            Setting.Animator.outmargin + 4 + (int) Setting.Animator.CellWidth / 2
                        + x * (Setting.Animator.CellWidth + Setting.Animator.inmargin),
                        Setting.Animator.outmargin + 27 + (int) Setting.Animator.CellHeight / 2
                        + y * (Setting.Animator.CellHeight + Setting.Animator.inmargin),
                        Setting.Animator.CellWidth/3, Setting.Animator.CellHeight/3);
    }
}
