package simStation;
import mvc.*;
public class ResumeCommand extends Command{

    public ResumeCommand(Model m)
    {
        super(m);
    }

    public void execute() throws Exception{
        if(!(model instanceof Simulation))
        {
            throw new Exception("Model must instantiate Simulation");
        }
        Simulation sim = (Simulation)model;
        sim.resume();
        sim.changed();
    }
}
