package simStation;

import mvc.*;

import java.awt.*;
import java.util.*;

public class SimulationView extends View {


    public SimulationView(Simulation s)
    {
        super(s);
    }

    public void paintComponent(Graphics gc)
    {
        Simulation sim = (Simulation) model;
        Color c = gc.getColor();
        Iterator<Agent> it = sim.iterator();
        while(it.hasNext())
        {
            Agent agent = it.next();
            gc.setColor(Color.cyan);
            gc.fillOval(agent.getX(), agent.getY(), 5, 5);
        }
        gc.setColor(c);
    }
}
