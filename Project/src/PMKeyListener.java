package src;

import java.awt.event.*;

import util.RICHTUNG;

public class PMKeyListener implements KeyListener {
    private Player m_player;

    public PMKeyListener(Player player) {
        m_player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 37:
                m_player.turn(RICHTUNG.LINKS);
                break;
            case 38:
                m_player.turn(RICHTUNG.OBEN);
                break;
            case 39:
                m_player.turn(RICHTUNG.RECHTS);
                break;
            case 40:
                m_player.turn(RICHTUNG.UNTEN);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
