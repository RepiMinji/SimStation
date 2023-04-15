package flocking;
import mvc.*;
import simStation.*;
import java.awt.*;
import java.util.Iterator;
class Bird extends Agent{

    public Bird()
    {
        super("agent");
        heading = Heading.random();
    }

    public void update()
    {

    }
}

class flockingFactory extends SimStationFactory{
    public Model makeModel() {return new flockingSimulation();}
    public String getTitle() {return "Flocking";}
}

class flockingSimulation extends Simulation{

    public void populate() {
        for(int i = 0; i < 15; i++) {
            addAgent(new Bird());
        }
    }

    public void stats()
    {

    }

    public static void main(String[] args)
    {
        AppPanel panel = new SimulationPanel(new flockingFactory());
        panel.display();
    }
}
