package simStation;
import java.util.*;

public class Manager  extends Console {

    private List<Agent> agents;

    public Manager() {
        agents = new LinkedList<Agent>();
    }

    // provides agents with thread safe access to Console.stdout:
    public synchronized void println(String msg) {
        stdout.println(msg);
    }

    public void add(Agent a) {
        agents.add(a);
        a.setManager(this);
    }

    protected String execute(String cmmd) throws Exception {
        if (cmmd.equalsIgnoreCase("s")) {
            for(Agent a: agents) { a.suspend(); }
        } else if (cmmd.equalsIgnoreCase("r")) {
            for(Agent a: agents) { a.resume(); }
        } else if (cmmd.equalsIgnoreCase("g")) {
            for(Agent a: agents) {
                Thread thread = new Thread(a);
                thread.start();
            }
        } else if (cmmd.equalsIgnoreCase("h")) {
            for(Agent a: agents) { a.stop(); }
        } else if (cmmd.equalsIgnoreCase("d")) {
            for(Agent a: agents) { System.out.println(a); };
        } else {
            throw new Exception("unrecognized command: " + cmmd);
        }
        return "ok";
    }

    protected void help() {
        stdout.println("g to start");
        stdout.println("s to suspend");
        stdout.println("r to resume");
        stdout.println("h to halt");
        stdout.println("d to display status");
    }
}
