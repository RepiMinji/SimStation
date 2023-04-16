package flocking;
import mvc.*;
import simStation.*;
import java.awt.*;
import java.util.Iterator;
class Bird extends Agent{

    int speed;
    public Bird()
    {
        super("Bird");
        heading = Heading.random();
        speed = Utilities.rng.nextInt(4) + 1;
    }

    public void update()
    {
        Bird neighbor = (Bird) world.getNeighbor(this, 10.0);
        if(neighbor != null)
        {
            this.setHeading(neighbor.getHeading());
            this.setSpeed(neighbor.getSpeed());
        }
        else {
            heading = Heading.random();
            int steps = this.speed * (Utilities.rng.nextInt(10) + 1);
            move(steps);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
        int b1 = 0, b2 = 0, b3 = 0, b4 = 0, b5 = 0;

    }

    public static void main(String[] args)
    {
        AppPanel panel = new SimulationPanel(new flockingFactory());
        panel.display();
    }
}
