package randomWalk;

import mvc.*;
import simStation.*;
import java.awt.*;
import java.util.Iterator;

class Drunk extends Agent {

    public Drunk() {
        super("Drunk");
        heading = Heading.random();
    }

    public void update() {
        heading = Heading.random();
        Drunk neighbor = (Drunk) world.getNeighbor(this, 10.0);
        if(neighbor != null)
        {
            heading = Heading.random();
        }
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }

}


class RandomWalkFactory extends SimStationFactory {
    public Model makeModel() { return new RandomWalkSimulation(); }
    public String getTitle() { return "Random Walks";}
}

 class RandomWalkSimulation extends Simulation {

    public RandomWalkSimulation()
    {
        super();
    }
    public void populate() {
        for(int i = 0; i < 15; i++) {
            addAgent(new Drunk());
        }
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new RandomWalkFactory());
        panel.display();
    }

}
