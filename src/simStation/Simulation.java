package simStation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {
    public static final int SIZE = 250;
    protected List<Agent> agents;
    private Manager manager;
    transient private Timer timer; // timers aren't serializable
    private int clock;
    public Simulation()
    {
        agents = new LinkedList<Agent>();
        manager = new Manager();
    }

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
        manager.add(a);
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

    public synchronized Agent getNeighbor(Agent a, Double radius)
    {
        Agent neighbor = null;
        int n = Utilities.rng.nextInt(agents.size());
        int start = n;
        while(neighbor == null)
        {
            Agent b = agents.get(n);
            double distance = Math.sqrt(Math.pow(2, b.getX() - a.getX()) + Math.pow(2, b.getY() - a.getY()));
            if(b != a && distance <= radius)
            {
                neighbor = b;
            }
            else {
                n = (n + 1) % agents.size();
                if(start == n)
                {
                    break;
                }
            }
        }

        return neighbor;
    }

    public void populate()
    {

    }

    public String[] stats()
    {
        String[] s = new String[2];
        s[0] = "#Agents: " + agents.size();
        s[1] = "Timer: " + this.getClock();
        return s;
    }

    public synchronized Iterator<Agent> iterator()
    {
        return agents.iterator();
    }

}