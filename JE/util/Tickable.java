package util;

import src.PM;
import src.Ticker;

import java.awt.Graphics;

public interface Tickable {
    //Observer Class for Ticker
    public Ticker ticker = PM.ticker;
    public abstract void tick();
    public abstract void draw(Graphics g);
}
