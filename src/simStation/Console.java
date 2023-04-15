package simStation;
import java.io.*;

public class Console {
    protected BufferedReader stdin =
            new BufferedReader(
                    new InputStreamReader(System.in));
    protected PrintWriter stdout =
            new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(System.out)), true);
    protected PrintWriter stderr =
            new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(System.err)), true);
    public void controlLoop() {
        while(true) {
            try {
                stdout.print("-> ");
                stdout.flush(); // force the write
                String cmmd = stdin.readLine();
                cmmd = cmmd.trim(); // trim white space
                if (cmmd.equalsIgnoreCase("quit")) break;
                // add more meta commands here: help, about, etc.
                stdout.println(execute(cmmd));
            } catch(Exception e) {
                stderr.println(e.getMessage());
            }
        } // while
        stdout.println("bye");
    } // controlLoop

    // override in a subclass:
    protected String execute(String cmmd) throws Exception {
        return "echo: " + cmmd;
    }
} // Console