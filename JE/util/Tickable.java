package util;

import java.awt.Graphics;

import src.PM;
import src.Ticker;

public interface Tickable {
    //Observer Class for Ticker
    public Ticker ticker = PM.ticker;
    public abstract void tick();
    public abstract void draw(Graphics g);
}
