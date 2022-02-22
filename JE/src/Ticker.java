package src;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import util.Tickable;

public class Ticker implements ActionListener {
    private Animator animator;
    private List<Tickable> tickables = new ArrayList<Tickable>();
    private boolean gameState = false;

    public Ticker(Animator anim) {
        animator = anim;
        this.gameState = true;
    }

    public void attach (Tickable tickable) {
        tickables.add(tickable);
    }

    public void benachrichtige() {
        for(Tickable t : tickables) {
            t.tick();
        }
    }

    public void setState(boolean state) {
        this.gameState = state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameState)return;
        benachrichtige();
        animator.zeichne();
    }
}
