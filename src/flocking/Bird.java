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
        Bird neighbor = (Bird) world.getNeighbor(this, 50.0);
        if(neighbor != null)
        {
            this.setHeading(neighbor.getHeading());
            this.setSpeed(neighbor.getSpeed());
        }
        else {
            heading = Heading.random();
        }
        int steps = this.speed;
        move(steps);
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

    public String[] stats()
    {
        String[] s = new String[7];
        s[0] = super.stats()[0];
        s[1] = super.stats()[1];
        int b1 = 0, b2 = 0, b3 = 0, b4 = 0, b5 = 0;
        Iterator it = this.iterator();
        while(it.hasNext())
        {
            Bird bird = (Bird) it.next();
            if(bird.getSpeed() == 1)
            {
                b1++;
            }
            else if(bird.getSpeed() == 2)
            {
                b2++;
            }
            else if(bird.getSpeed() == 3)
            {
                b3++;
            }
            else if(bird.getSpeed() == 4)
            {
                b4++;
            }
            else {
                b5++;
            }
        }
        s[2] = "birds at speed 1: " + b1;
        s[3] = "birds at speed 2: " + b2;
        s[4] = "birds at speed 3: " + b3;
        s[5] = "birds at speed 4: " + b4;
        s[6] = "birds at speed 5: " + b5;
        return s;
    }

    public static void main(String[] args)
    {
        AppPanel panel = new SimulationPanel(new flockingFactory());
        panel.display();
    }
}
