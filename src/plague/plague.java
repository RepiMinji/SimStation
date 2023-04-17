package plague;
import mvc.*;
import simStation.*;
import java.awt.*;
import java.util.Iterator;


class plague extends Agent {

    boolean host;

    public plague(boolean v){
        super("Plague");
        heading = heading.random();
        host = v;


    }

    public void update(){
        plague neighbor = (plague) world.getNeighbor(this, 50.0);
        if(neighbor != null)
        {
            if(neighbor.getInfectedStatus() == true){
                    this.setInfected();
                    //make red somehow idk
            }
        }

        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }


    public boolean getInfectedStatus(){
        return host;
    }

    public void setInfected(){
        this.host = true;
    }

}

class plagueFactory extends SimStationFactory{

    public Model makeModel() { return new plagueSimulation(); }
    public String getTitle() { return "PlagueSimulation";}
}

class plagueSimulation extends Simulation{

    public static int VIRULENCE = 50;
    public static int RESISTANCE = 2;
    public static int initialInfected = 50;

    public void populate() {
        for(int i = 0; i < 2; i++) {
            addAgent(new plague(false));
        }

        for(int i = 0; i < initialInfected; i++) {
            addAgent(new plague(true));
            //make red
        }
    }

    public String[] stats() {
        String[] s = new String[3];
        s[0] = super.stats()[0];
        s[1] = super.stats()[1];

        //count infected
        Iterator count = this.iterator();
        int infected = 0;
        while (count.hasNext()) {
            plague p = (plague) count.next();
            if (p.getInfectedStatus() == true) { infected++;}
        }
        s[2]="# of infected: " + infected;
        return s;
    }

    public static void main(String[] args)
    {
        AppPanel panel = new SimulationPanel(new plagueFactory());
        panel.display();
    }
}