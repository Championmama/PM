package src;

import util.Position.M_AXIS;

import java.awt.event.*;

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
                m_player.turn(M_AXIS.X, false);
                break;
            case 38:
                m_player.turn(M_AXIS.Y, true);
                break;
            case 39:
                m_player.turn(M_AXIS.X, true);
                break;
            case 40:
                m_player.turn(M_AXIS.Y, false);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
