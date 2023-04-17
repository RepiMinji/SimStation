package PrisonersDilemma;
import mvc.*;
import simStation.*;

public class Prisoner extends Agent {
    private int fitness = 0;
    private boolean partnerCheated = false;

    public Prisoner(){
        super("Prisoner");
        heading = heading.random();
    }
    public void update(){

    }

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
            if(Math.random()> 0.5){
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
