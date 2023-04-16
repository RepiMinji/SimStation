package simStation;

import mvc.*;

import java.awt.*;
import java.util.*;

public class SimulationView extends View {


    public SimulationView(Simulation s)
    {
        super(s);
        setBackground(Color.white);
    }

    public void paintComponent(Graphics gc)
    {
        Simulation sim = (Simulation) model;
        Color oldColor = gc.getColor();
        Iterator<Agent> it = sim.iterator();
        while(it.hasNext())
        {
            Agent a = it.next();
            gc.setColor(Color.cyan);
            gc.fillOval(a.getX(), a.getY(), 5, 5);
        }
        gc.setColor(oldColor);
    }
}
