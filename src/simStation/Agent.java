package simStation;

public class Agent {
    private String name;
    private Heading heading;
    private int x;
    private int y;
    private boolean suspended;
    private boolean stopped;
    private Thread myThread;

    public Agent()
    {
        name = "myAgent";
        heading = Heading.NORTH;
        x = 0;
        y = 0;
        suspended = false;
        stopped = false;
        //myThread = ???
    }

    public void run()
    {

    }

    public void start()
    {

    }

    public void suspend()
    {

    }

    public void resume()
    {

    }

    public void stop()
    {

    }

    public void update()
    {

    }

    public void move (int steps)
    {

    }

}
