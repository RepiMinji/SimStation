package simStation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {
    public static final int SIZE = 250;
    protected List<Agent> agents;

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

    public int getClock() {
        return clock;
    }

    // etc.

    public void addAgent(Agent a)
    {
        agents.add(a);
        a.setWorld(this);
    }

    public void start() throws Exception {
        agents = new LinkedList<Agent>();
        clock = 0;
        populate();
        startTimer();
        for(Agent agent: agents)
        {
            agent.start();
        }
    }

    public void suspend() throws Exception
    {
        for(Agent agent: agents)
        {
            agent.suspend();
        }
        stopTimer();
    }

    public void resume() throws Exception {
        startTimer();
        for(Agent agent: agents)
        {
            agent.resume();
        }
    }

    public void stop() throws Exception {
        for(Agent agent: agents)
        {
            agent.stop();
        }
        stopTimer();
    }

    public Agent getNeighbor(Agent a, Double radius)
    {
        for(Agent b: agents)
        {
            double distance = Math.sqrt(Math.pow(2, b.getX() - a.getX()) + Math.pow(2, b.getY() - a.getY()));
            if(distance <= radius)
            {
                return b;
            }
        }

        return null;
    }

    public void populate()
    {

    }

    public String[] stats()
    {
        String[] s = new String[2];
        s[0] = "agents: " + agents.size();
        s[1] = "timer: " + this.getClock();
        return s;
    }

    public synchronized Iterator<Agent> iterator()
    {
        return agents.iterator();
    }

}