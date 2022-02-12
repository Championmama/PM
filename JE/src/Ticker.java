package src;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import util.Tickable;

public class Ticker implements ActionListener {

    private List<Tickable> tickables = new ArrayList<Tickable>();
    private boolean gameState = false;

    public Ticker() {
        this.gameState = true;
    }

    public void attach (Tickable tickable) {
        tickables.add(tickable);
    }

    public void notifyAllObservers() {
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
        notifyAllObservers();
        Animator.zeichne();
    }
}
