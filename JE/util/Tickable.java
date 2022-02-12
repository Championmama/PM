package util;

import src.PM;
import src.Ticker;

public abstract class Tickable {
    //Observer Class for Ticker
    protected Ticker ticker;


    public Tickable() {
        this.ticker = PM.ticker;
        this.ticker.attach(this);
    }

    public abstract void tick();
}
