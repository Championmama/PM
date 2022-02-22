package src;

import util.Position.M_Axis;

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
                m_player.turn(M_Axis.X, false);
                break;
            case 38:
                m_player.turn(M_Axis.Y, true);
                break;
            case 39:
                m_player.turn(M_Axis.X, true);
                break;
            case 40:
                m_player.turn(M_Axis.Y, false);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
