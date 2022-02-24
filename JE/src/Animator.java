package src;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Animator {

    private Graphics m_g;
    private Player m_player;
    private ImageIcon m_img;
    private Labyrinth m_lab;
    private Ghost[] m_ghosts;
    public boolean Running = true;

    public Animator(Graphics g, Player p, Ghost[] ghosts) {
        m_player = p;
        m_ghosts = ghosts;
        m_g = g;
        //lädt Textur \/
        m_img = new ImageIcon("assets/cherry.jpg");

        m_lab = new Labyrinth();
        m_lab.setActive();
    }

    public void zeichne() {
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 20; y++) {
                switch (m_lab.getBesetzung(x, y)) {
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
                        if (m_img.getImage() == null)
                            System.err.println("image not loaded");
                        m_g.drawImage(m_img.getImage(),
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
                        m_g.setColor(new Color(0xffb1ff));
                        m_g.fillRect(getWindowXCoord(x),
                                getWindowYCoord(y) + Setting.Animator.CellHeight / 2 - 2,
                                Setting.Animator.CellWidth, 4);
                        break;
                    default:
                        drawBG(x, y);

                } // end of switch
            } // end of for
        } // end of for

        //for each loop
        for (Ghost _G : m_ghosts) {
            _G.draw(m_g);
        }
        
        m_player.draw(m_g);
        m_g.setColor(Color.white);
        m_g.fillRect(650, 10, 50, 50);
        m_g.setColor(Color.black);
        m_g.drawString(PM.Point_Text, 650, 50);
    }

    private int getWindowXCoord(int x) {
        return Setting.Animator.outmargin + 7 + x * (Setting.Animator.CellWidth + Setting.Animator.inmargin);
    }

    private int getWindowYCoord(int y) {
        return Setting.Animator.outmargin + 30 + y * (Setting.Animator.CellHeight + Setting.Animator.inmargin);
    }

    //malt den Hintergrund
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
