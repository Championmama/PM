package src;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;

import util.Tickable;

public class Labyrinth implements Tickable {
    public static Labyrinth currenLabyrinth;
    private static int[][] m_Besetzung = {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1 },
            { 1, 3, 0, 3, 3, 3, 3, 0, 3, 0, 0, 3, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 1, 0, 0, 1 },
            { 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 4, 1, 1, 1, 0, 3, 1, 3, 0, 0, 0, 1 },
            { 1, 4, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 3, 0, 0, 0, 0, 0, 3, 3, 0, 1, 0, 1, 1, 0, 1 },
            { 0, 3, 1, 0, 1, 1, 0, 3, 0, 3, 0, 3, 1, 4, 0, 0, 0, 3, 0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 3, 0 },
            { 1, 0, 1, 0, 3, 0, 3, 3, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 3, 0, 1 },
            { 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 3, 1, 3, 0, 0, 0, 3, 3, 0, 3, 0, 1, 0, 0, 3, 0, 0, 0, 1 },
            { 1, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 2, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 3, 1 },
            { 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 3, 1, 1, 5, 1, 1, 0, 3, 1, 0, 1, 0, 3, 1, 1, 1, 1, 4, 1 },
            { 1, 3, 0, 0, 3, 0, 1, 1, 3, 0, 3, 0, 1, 0, 0, 0, 1, 0, 0, 1, 3, 0, 0, 1, 1, 0, 0, 0, 0, 1 },
            { 1, 0, 1, 1, 3, 3, 3, 1, 0, 0, 1, 3, 1, 0, 0, 0, 1, 0, 0, 1, 0, 3, 0, 0, 0, 0, 0, 1, 0, 1 },
            { 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 3, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1 },
            { 1, 3, 1, 0, 1, 1, 3, 0, 3, 1, 0, 3, 0, 3, 0, 3, 0, 0, 0, 3, 0, 3, 0, 3, 3, 0, 3, 0, 0, 1 },
            { 1, 3, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 4, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
            { 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 1 },
            { 1, 0, 1, 3, 4, 0, 0, 3, 0, 0, 4, 1, 0, 1, 1, 1, 1, 0, 1, 3, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1 },
            { 1, 3, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 3, 1, 0, 3, 0, 0, 1, 0, 3, 1, 0, 3, 1, 0, 1, 3, 0, 1 },
            { 1, 0, 3, 0, 1, 1, 3, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1 },
            { 1, 4, 0, 3, 0, 3, 0, 3, 0, 3, 4, 3, 0, 3, 0, 3, 0, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1 } };

    private ImageIcon m_img;

    public Labyrinth() {
        URL u = getClass().getResource("../assets/cherry.jpg");
        m_img = new ImageIcon(u);
        setActive();
    }

    public void setActive() {
        currenLabyrinth = this;
    }

    public void setBesetzung(int x, int y, int value) {
        m_Besetzung[y][x] = value;
    }

    public int getBesetzung(int x, int y) {
        return m_Besetzung[y][x];
    }

    @Override
    public void tick() {
        boolean _GameOver = true;
        for (int[] Reihe : m_Besetzung) {
            for (int Zelle : Reihe) {
                if (Zelle == 2 || Zelle == 3) {
                    _GameOver = false;
                }
            }
        }

        if (_GameOver) {
            ticker.setState(false);
            System.out.println("Won");
        }
    }

    private Graphics m_g;

    @Override
    public void draw(Graphics g) {
        if (m_g == null) {
            m_g = g;
        }
        for (int x = 0; x < Setting.width; x++) {
            for (int y = 0; y < Setting.height; y++) {
                switch (m_Besetzung[y][x]) {
                    case 1: // Wall
                        g.setColor(Color.black);
                        g.fillRect(
                                getWindowXCoord(x),
                                getWindowYCoord(y),
                                Setting.Animator.CellWidth,
                                Setting.Animator.CellHeight);
                        g.setColor(new Color(0x010160));
                        g.fillRect(
                                getWindowXCoord(x),
                                getWindowYCoord(y),
                                Setting.Animator.CellWidth,
                                1);
                        g.fillRect(
                                getWindowXCoord(x),
                                getWindowYCoord(y),
                                1,
                                Setting.Animator.CellHeight);

                        break;
                    case 2: // Cherry
                        drawBG(x, y);
                        if (m_img.getImageLoadStatus() == 4)
                            System.err.println("image not loaded");
                        g.drawImage(m_img.getImage(),
                                getWindowXCoord(x),
                                getWindowYCoord(y), null);
                        break;
                    case 3: // Point
                        drawPointwithBG(x, y, Color.orange);
                        break;
                    case 4:// inv
                        drawPointwithBG(x, y, Color.white);
                        break;
                    case 5:// door
                        drawBG(x, y);
                        g.setColor(new Color(0xffb1ff));
                        g.fillRect(getWindowXCoord(x),
                                getWindowYCoord(y) + Setting.Animator.CellHeight / 2 - 2,
                                Setting.Animator.CellWidth, 4);
                        break;
                    default:
                        drawBG(x, y);

                } // end of switch
            } // end of for
        } // end of for
        m_g.setColor(Color.white);
        m_g.fillRect(650, 10, 50, 50);
        m_g.setColor(Color.black);
        m_g.drawString(PM.Point_Text, 650, 50);
    }

    // rellativ zu Bildschirmkoordinaten
    private int getWindowXCoord(int x) {
        return Setting.Animator.outmargin + 7 + x * (Setting.Animator.CellWidth + Setting.Animator.inmargin);
    }

    private int getWindowYCoord(int y) {
        return Setting.Animator.outmargin + 30 + y * (Setting.Animator.CellHeight + Setting.Animator.inmargin);
    }

    // malt den Hintergrund
    private void drawBG(int x, int y) {
        m_g.setColor(new Color(0x404040));
        m_g.fillRect(
                getWindowXCoord(x),
                getWindowYCoord(y),
                Setting.Animator.CellWidth, Setting.Animator.CellHeight);
    }

    // malt Hintergrund und dann den Punkt mit Farbe als Parameter
    private void drawPointwithBG(int x, int y, Color c) {
        drawBG(x, y);
        m_g.setColor(c);
        m_g.fillOval(
                Setting.Animator.outmargin + 4 + (int) Setting.Animator.CellWidth / 2
                        + x * (Setting.Animator.CellWidth + Setting.Animator.inmargin),
                Setting.Animator.outmargin + 27 + (int) Setting.Animator.CellHeight / 2
                        + y * (Setting.Animator.CellHeight + Setting.Animator.inmargin),
                Setting.Animator.CellWidth / 3, Setting.Animator.CellHeight / 3);
    }
}
