package simStation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {

    transient private Timer timer; // timers aren't serializable
    private int clock;

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
        }
    }

    // etc.

    public void addAgent(Agent a)
    {

    }

    public void start()
    {

    }

    public void suspend()
    {

    }

    public void resume()
    {

    }

    public void stop()
    {

    }

    public Agent getNeighbor(Agent a, Double radius)
    {
        return a;
    }

    public void populate()
    {

    }

}