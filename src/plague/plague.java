package plague;
import mvc.*;
import simStation.*;
import java.awt.*;
import java.util.Iterator;


class plague extends Agent {

    private boolean infected;

    public plague(boolean v){
        super("Plague");
        heading = heading.random();
        infected = v;


    }

    public void update(){
        plague neighbor = (plague) world.getNeighbor(this, 200.0);
        if(neighbor != null && neighbor.isInfected())
        {
            this.setInfected();
        }
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(5) + 1;
        move(steps);
    }


    public boolean isInfected(){
        return infected;
    }

    public void setInfected(){
        this.infected = true;
    }

}

class plagueFactory extends SimStationFactory{

    public Model makeModel() { return new plagueSimulation(); }
    public String getTitle() { return "PlagueSimulation";}
    public View makeView(Model model){return new plagueView(model);}
}

class plagueSimulation extends Simulation{

    public static int VIRULENCE = 50;
    public static int RESISTANCE = 2;
    public static int initialInfected = 5;

    public void populate() {
        for(int i = 0; i < 15; i++) {
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
        Double inf = 0.0;
        while (count.hasNext()) {
            plague p = (plague) count.next();
            if (p.isInfected() == true) { inf++;}
        }
        s[2]="% infected: " + 100*(inf/this.agents.size());
        return s;
    }

    public static void main(String[] args)
    {
        AppPanel panel = new SimulationPanel(new plagueFactory());
        panel.display();
    }
}

class plagueView extends View{
    public plagueView(Model m)
    {
        super(m);
    }

    public void paintComponent(Graphics gc)
    {
        Color oldColor = gc.getColor();
        plagueSimulation sim = (plagueSimulation) model;
        Iterator<Agent> it = sim.iterator();
        while(it.hasNext())
        {
           plague h = (plague) it.next();
           if(h.isInfected())
           {
               gc.setColor(Color.red);
           }
           else {
               gc.setColor(Color.green);
           }
           gc.fillOval(h.getX(), h.getY(), 5, 5);
        }
        gc.setColor(oldColor);
    }
}