package simStation;

import mvc.Utilities;

public abstract class Agent implements Runnable {

    protected String name;
    protected Thread myThread;
    private boolean suspended, stopped;
    protected Manager manager;
    protected Heading heading;
    protected int x;
    protected int y;
    protected Simulation world;

    public Agent(String name) {
        this.name = name;
        suspended = false;
        stopped = false;
        myThread = null;
        x = Utilities.rng.nextInt(world.SIZE);
        y = Utilities.rng.nextInt(world.SIZE);
        heading = Heading.random();
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getName() { return name; }
    public synchronized String toString() {
        String result = name;
        if (stopped) result += " (stopped)";
        else if (suspended) result += " (suspended)";
        else result += " (running)";
        return result;
    }
    // thread stuff:
    public synchronized void start()
    {
        if(myThread == null)
        {
            myThread = new Thread(this, name);
        }
        myThread.start();
    }
    public synchronized void stop() { stopped = true; }
    public synchronized boolean isStopped() { return stopped; }
    public synchronized void suspend() { suspended = true; }
    public synchronized boolean isSuspended() { return suspended;  }
    public synchronized void resume() { notify(); }
    // wait for me to die:
    public synchronized void join() {
        try {
            if (myThread != null) myThread.join();
        } catch(InterruptedException e) {
            manager.println(e.getMessage());
        }
    }
    // wait for notification if I'm not stopped and I am suspended
    private synchronized void checkSuspended() {
        try {
            while(!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            manager.println(e.getMessage());
        }
    }


    public void run() {
        myThread = Thread.currentThread();
        while (!isStopped()) {
            try {
                update();
                Thread.sleep(100);
                checkSuspended();
            } catch(InterruptedException e) {
                manager.println(e.getMessage());
            }
        }
        manager.stdout.println(name + " stopped");
    }

    public abstract void update();

    public void move(int steps)
    {
        switch(heading)
        {
            case NORTH:
            {
                y -= steps;
                if(y < 0)
                {
                    y = Simulation.SIZE + y;
                }
                world.changed();
                break;
            }
            case EAST:
            {
                x += steps;
                if(x > Simulation.SIZE)
                {
                    x = Simulation.SIZE - x;
                }
                world.changed();
                break;
            }
            case SOUTH:
            {
                y += steps;
                if(y > Simulation.SIZE)
                {
                    y = Simulation.SIZE - y;
                }
                world.changed();
                break;
            }
            case WEST:
            {
                x -= steps;
                if(x < 0)
                {
                    x = Simulation.SIZE + x;
                }
                world.changed();
                break;
            }
            case NORTHWEST:
            {
                x-= Math.sqrt(Math.pow(2, steps)/2);
                y -= Math.sqrt(Math.pow(2, steps)/2);
                if(y < 0)
                {
                    y = Simulation.SIZE + y;
                }
                if(x < 0)
                {
                    x = Simulation.SIZE + x;
                }
                world.changed();
                break;
            }
            case NORTHEAST:
            {
                x+= Math.sqrt(Math.pow(2, steps)/2);
                y -= Math.sqrt(Math.pow(2, steps)/2);
                if(y > Simulation.SIZE)
                {
                    y = Simulation.SIZE - y;
                }
                if(x > Simulation.SIZE)
                {
                    x = Simulation.SIZE - x;
                }
                world.changed();
                break;
            }
            case SOUTHWEST:
            {
                x-= Math.sqrt(Math.pow(2, steps)/2);
                y += Math.sqrt(Math.pow(2, steps)/2);
                if(y < 0)
                {
                    y = Simulation.SIZE + y;
                }
                if(x < 0)
                {
                    x = Simulation.SIZE + x;
                }
                world.changed();
                break;
            }
            case SOUTHEAST:
            {
                x += Math.sqrt(Math.pow(2, steps)/2);
                y += Math.sqrt(Math.pow(2, steps)/2);
                if(y < 0)
                {
                    y = Simulation.SIZE + y;
                }
                if(x > Simulation.SIZE)
                {
                    x = Simulation.SIZE - x;
                }
                world.changed();
                break;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Heading getHeading() {
        return heading;
    }

    public void setHeading(Heading heading) {
        this.heading = heading;
    }

    public Simulation getWorld() {
        return world;
    }

    public void setWorld(Simulation world) {
        this.world = world;
    }
}