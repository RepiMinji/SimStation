package simStation;

import mvc.*;

import java.awt.*;
import java.awt.geom.*;

public class SimulationView extends View {

    Ellipse2D.Double a;

    public SimulationView(Simulation s)
    {
        super(s);
        repaint();
    }

    public void draw(Graphics2D g)
    {
        g.setColor(Color.CYAN);
        for(Agent agent: Simulation.getAgents())
        {
            a = new Ellipse2D.Double(a.getX(), a.getY(), 5, 5);
            g.fill(a);
        }
    }
}
