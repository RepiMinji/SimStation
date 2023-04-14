package mvc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JPanel implements PropertyChangeListener {
    protected Model model;

    public View(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
        this.updateUI();

        //creates black border around view panel
        Border line = BorderFactory.createLineBorder(Color.black);
        setBorder(line);
        setBackground(Color.LIGHT_GRAY);
        repaint();
    }
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
        this.updateUI();
    }
    public void setModel(Model m) {
        //setting new model
        this.model = m;
        this.model.initSupport();
        this.model.addPropertyChangeListener(this);
        this.updateUI();

        this.repaint();
    }

}
