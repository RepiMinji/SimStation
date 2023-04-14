package mvc;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import javax.swing.*;

public class AppPanel extends JPanel implements PropertyChangeListener, ActionListener  {

    protected Model model;
    protected AppFactory factory;
    protected View view;
    protected JPanel controlPanel; // not a separate class!
    private SafeFrame frame;
    private String name;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    public AppPanel(AppFactory factory) {
        super();

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        cp.add(this);
        frame.setTitle(factory.getTitle());

        //initializing factory
        this.factory = factory;

        //initializing controlPanel (left side w/ buttons)
        controlPanel = new ControlPanel();
        this.add(controlPanel);

        //initializing model and view for that model
        model = factory.makeModel();
        view = factory.makeView(model);
        view.setModel(model);

        //making view visible
        this.setLayout(new GridLayout(1, 2));
        this.add(view);

        frame.setJMenuBar(this.createMenuBar());
    }


    // called in main
    public void display() {
        frame.setVisible(true);
    }

    //Override in child class if needed for implementation
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public Model getModel() {
        return model;
    }

    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.removePropertyChangeListener(this);
        this.model = newModel;
        this.model.initSupport(); // defined in Bean
        this.model.addPropertyChangeListener(this);
        view.setModel(this.model);
        model.changed();
    }

    protected JMenuBar createMenuBar() {
        // similar to turtle graphics
        //updated
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save As", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", this.factory.getEditCommands(), AppPanel.this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent ae) {
        String cmmd = ae.getActionCommand();
        try {
            // handle control actions here
            switch (cmmd){
                case "About": {
                    Utilities.inform(factory.about());
                    break;
                }
                case "Help": {
                    Utilities.inform(factory.getHelp());
                    break;
                }
                case "New": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                    {
                        this.remove(view);
                        this.repaint();
                        model = factory.makeModel();
                        view = factory.makeView(model);
                        view.setModel(model);
                        this.add(view);

                        revalidate();
                    }
                    break;
                }
                case "Save": {
                    if(this.name != null)
                    {
                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(name));
                        os.writeObject(this.model);
                        os.close();

                    }
                    else
                    {
                        String fName = Utilities.getFileName((String) null, false);
                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                        os.writeObject(this.model);
                        os.close();
                        name = fName;
                    }
                    break;
                }
                case "Save As": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    name = fName;
                    os.writeObject(this.model);
                    os.close();
                    break;
                }
                case "Open": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));

                        model = (Model)is.readObject();
                        is.close();
                        this.remove(view);
                        view.setModel(model);
                        view = factory.makeView(model);
                        this.add(view);

                        revalidate();
                    }
                    break;
                }
                case "Quit": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                        System.exit(0);
                    break;
                }
                default: {
                    Command command = factory.makeEditCommand(model, cmmd, ae.getSource());
                    command.execute();
                }
            }
        }
        catch (Exception e) {
            handleException(e);
        }
    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            //default for MVC, override in specific implementations
            setBackground(Color.PINK);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }
}