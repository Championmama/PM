package src;

import java.awt.event.*;

import util.Position;
import util.Position.M_Axis;

public class Ticker implements ActionListener {
    private Player player;
    private Ghost[] ghosts;

    public Ticker(Player player, Ghost[] ghosts) {
        this.player = player;
        this.ghosts = ghosts;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Animator.Running)
            return;
        player.move();
        for (Ghost g : ghosts) {
            g.move();
        }
        Animator.zeichne();
        Ghost c = checkcollision();
        if (c != null) {
            if (player.invincible()) {
                c.die();
            } else {
                player.die();
            }
        }
        for (Ghost g : ghosts) {
            g.setTarget(player.getPosition());
        }
        
    }

    private Ghost checkcollision() {
        for (Ghost g : ghosts) {
            if (g.getPosition().get(M_Axis.X) == player.getPosition().get(M_Axis.X)
                    && g.getPosition().get(M_Axis.Y) == player.getPosition().get(M_Axis.Y)) {
                return g;
            }
        }
        return null;
    }
}
