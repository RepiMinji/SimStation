package simStation;

import mvc.*;

public class SimStationFactory implements AppFactory {

    public Model makeModel()
    {
        return new Simulation();
    }

    public View makeView(Model m)
    {

        return new SimulationView((Simulation)m);
    }

    public String[] getEditCommands()
    {
        return new String[]{"Start", "Suspend", "Resume", "Stop", "Stats"};
    }

    public String getTitle()
    {
        return "SimStation";
    }

    public String about()
    {
        return "SimStation simulation 1.0 for Lance Le";
    }

    public String[] getHelp()
    {
        return new String[]{"Start: Begins the simulation", "Suspend: Temporarily stops the simulation",
                "Resume: Begins the simulation from a suspended state", "Stop: Permanently stops the simulation",
                "Stats: Shows the stats of the simulation"};
    }

    public Command makeEditCommand(Model model, String type, Object source){
        if(type == "Start")
        {
            return new StartCommand(model);
        }
        else if(type == "Stats")
        {
            return new StatsCommand(model);
        }
        else if(type == "Resume")
        {
            return new ResumeCommand(model);
        }
        else if(type == "Suspend")
        {
            return new SuspendCommand(model);
        }
        else if(type == "Stop")
        {
            return new StopCommand(model);
        }
        return null;
    }
}
