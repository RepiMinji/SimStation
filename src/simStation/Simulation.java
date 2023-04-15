package simStation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {
    static List<Agent> agents = new LinkedList<Agent>();

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
        agents.add(a);
    }

    public void start()
    {
        for(Agent agent: agents)
        {
            agent.run();
        }
    }

    public void suspend()
    {
        Thread thread = new Thread();
        thread.suspend();
    }

    public void resume()
    {
        Thread thread = new Thread();
        thread.resume();
    }

    public void stop()
    {
        Thread thread = new Thread();
        thread.stop();
    }

    public Agent getNeighbor(Agent a, Double radius)
    {
        return a;
    }

    public static List<Agent> getAgents() {
        return agents;
    }

    public void populate()
    {

    }

    public void stats()
    {

    }

}