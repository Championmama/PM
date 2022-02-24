package src;

import java.awt.event.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import util.Tickable;

public class Ticker implements ActionListener {
    private List<Tickable> tickables = new ArrayList<Tickable>();
    private boolean m_gameState = false;
    private Graphics m_g;

    public Ticker(Graphics g) {

        this.m_gameState = true;
        this.m_g = g;
    }

    public void attach (Tickable tickable) {
        tickables.add(tickable);
    }

    public void benachrichtige() {
        for(Tickable t : tickables) {
            t.tick();
        }
        for(Tickable t : tickables) {
            t.draw(m_g);
        }
    }

    public void setState(boolean state) {
        this.m_gameState = state;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!m_gameState)return;
        benachrichtige();
    }
}
