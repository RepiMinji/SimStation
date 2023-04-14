package simStation;

import mvc.*;

public class simFactory implements AppFactory {

    public Model makeModel()
    {
        return new simStation();
    }

    public View makeView(Model m)
    {
        return new simStationView((simStation)m);
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
        return "SimStation simulation showing four simulations: randomWalks, flocking, plague, and prisonersDilemma";
    }

    public String[] getHelp()
    {
        return new String[]{"blah blah blah"};
    }

    public Command makeEditCommand(Model model, String type, Object source)
    {
        return null;
    }
}
