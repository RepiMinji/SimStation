package mvc;

public interface AppFactory {
    //test change
    public Model makeModel();
    public View makeView(Model m);
    public String getTitle();
    public String about();
    public String[] getHelp();
    public String[] getEditCommands();
    public Command makeEditCommand(Model m, String t, Object s);

}
