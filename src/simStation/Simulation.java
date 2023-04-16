package simStation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {
    static Manager manager;

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
        this.manager.add(a);
    }

    public void start() throws Exception {
        this.populate();
        this.manager.execute("g");
    }

    public void suspend() throws Exception
    {
        this.manager.execute("s");
    }

    public void resume() throws Exception {
        this.manager.execute("r");
    }

    public void stop() throws Exception {
        this.manager.execute("h");
    }

    public Agent getNeighbor(Agent a, Double radius)
    {
        for(Agent b: this.manager.getAgents())
        {
            double distance = Math.sqrt(Math.pow(2, b.getX() - a.getX()) + Math.pow(2, b.getY() - a.getY()));
            if(distance <= radius)
            {
                return b;
            }
        }

        return null;
    }

    public static List<Agent> getAgents() {
        return manager.getAgents();
    }

    public void populate()
    {

    }

    public void stats()
    {

    }

}