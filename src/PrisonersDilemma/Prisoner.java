package PrisonersDilemma;
import mvc.*;
import simStation.*;

import java.util.Iterator;

public class Prisoner extends Agent {
    private int fitness = 0;
    private boolean partnerCheated = false;

    private cooperationStrategy strategy;

    public Prisoner(cooperationStrategy strategy){
        super("Prisoner");
        heading = heading.random();
        this.strategy = strategy;
    }
    public void update(){
        Prisoner neighbor = (Prisoner) world.getNeighbor(this, 10.0);
        if(neighbor != null){
            if(neighbor.getCheated() == true){
                this.setCheated(true);
                if(this.cooperate()){
                    neighbor.updateFitness(5);
                }else{
                    neighbor.updateFitness(1);
                    this.updateFitness(1);
                }
            }
            else{
                this.setCheated(false);
                if(this.cooperate()){
                    neighbor.updateFitness(3);
                    this.updateFitness(3);
                }else{
                    this.updateFitness(5);
                }
            }
        }
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }

    public int getFitness(){ return fitness; }

    public boolean cooperate(){ return strategy.cooperate(partnerCheated); }

    public String getStrategy() { return strategy.getClass().getSimpleName(); }

    public void updateFitness(int x){
        fitness += x;
    }

    public boolean getCheated(){
        return partnerCheated;
    }

    public void setCheated(boolean c){
        partnerCheated = c;
    }

}

    class prisonerFactory extends SimStationFactory {
        public Model makeModel() {return new prisonerSimulation();}
        public String getTitle() {return "Prisoner's Dilemma";}
    }

    class prisonerSimulation extends Simulation{
        public void populate() {
            for(int i = 0; i < 5; i++) {
                addAgent(new Prisoner(new Cooperate()));
                addAgent(new Prisoner(new RandomlyCooperate()));
                addAgent(new Prisoner(new Cheat()));
                addAgent(new Prisoner(new Tit4Tat()));
            }
        }

        public String[] stats() {
            String[] s = new String[6];
            s[0] = super.stats()[0];
            s[1] = super.stats()[1];
            Iterator it = this.iterator();
            double fitnessStrategy1 = 0, fitnessStrategy2 = 0, fitnessStrategy3 = 0, fitnessStrategy4 = 0;
            double strategy1 = 0, strategy2 = 0, strategy3 = 0, strategy4 = 0;

            while(it.hasNext()){
                Prisoner p = (Prisoner) it.next();
                if(p.getStrategy().equals("Cooperate")){
                    fitnessStrategy1 += p.getFitness();
                    strategy1++;
                }
                else if(p.getStrategy().equals("RandomlyCooperate")){
                    fitnessStrategy2 += p.getFitness();
                    strategy2++;
                }
                else if(p.getStrategy().equals("Cheat")){
                    fitnessStrategy3 += p.getFitness();
                    strategy3++;
                }
                else if(p.getStrategy().equals("Tit4Tat")){
                    fitnessStrategy4 += p.getFitness();
                    strategy4++;
                }
            }

            double avgFitnessStrategy1 = strategy1 == 0 ? 0 : fitnessStrategy1 / strategy1;
            double avgFitnessStrategy2 = strategy2 == 0 ? 0 : fitnessStrategy2 / strategy2;
            double avgFitnessStrategy3 = strategy3 == 0 ? 0 : fitnessStrategy3 / strategy3;
            double avgFitnessStrategy4 = strategy4 == 0 ? 0 : fitnessStrategy4 / strategy4;

            s[2] = "Avg fitness (Cooperate): " + avgFitnessStrategy1;
            s[3] = "Avg fitness (RandomlyCooperate): " + avgFitnessStrategy2;
            s[4] = "Avg fitness (Cheat): " + avgFitnessStrategy3;
            s[5] = "Avg fitness (Tit4Tat): " + avgFitnessStrategy4;

            return s;

        }

        public static void main(String[] args) {
            AppPanel panel = new SimulationPanel(new prisonerFactory());
            panel.display();
        }

    }

        /* ------------------------------------------------

       Strategies - Cooperate, RandomlyCooperate, Cheat, Tit4Tat

            ------------------------------------------------ */

    abstract class cooperationStrategy{
        abstract boolean cooperate(boolean cheated);
    }
    class Cooperate extends cooperationStrategy{
        boolean cooperate(boolean cheated){
            return true;
        }

    }

    class RandomlyCooperate extends cooperationStrategy{
        boolean cooperate(boolean cheated){
            if(Math.random() > 0.5){
                return true;
            }
            return false;
        }
    }

    class Cheat extends cooperationStrategy{
        boolean cooperate(boolean cheated){
            return false;
        }
    }

    class Tit4Tat extends cooperationStrategy{
        boolean cooperate(boolean cheated){
            if(cheated == false) {
                return true;
            }
            return false;
        }
    }
