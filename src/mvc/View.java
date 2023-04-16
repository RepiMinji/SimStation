package mvc;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.*;
import java.beans.*;

public class View extends JPanel implements PropertyChangeListener {

    protected Model model;

    public View(Model model) {
        super();
        this.model = model;
        model.addPropertyChangeListener(this);
        // optional border around the view component
        setBorder(LineBorder.createGrayLineBorder());//.createBlackLineBorder());
    }

    public Model getModel() {
        return model;
    }

    // called by File/Open and File/New
    public void setModel(Model newModel) {
        this.model.removePropertyChangeListener(this);
        this.model = newModel;
        this.model.initSupport();
        this.model.addPropertyChangeListener(this);
        this.updateUI();
        this.repaint();
    }
    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        this.repaint();
    }

}