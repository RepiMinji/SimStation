package mvc;

abstract public class Command {
    public Model model;

    public Command (Model model){
        this.model = model;
    }

    public Model getModel(){
        return this.model;
    }

    public void execute() throws Exception {

    }

}
