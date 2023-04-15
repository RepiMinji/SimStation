package simStation;

import mvc.*;

import java.awt.*;
import javax.swing.*;
public class SimulationPanel extends AppPanel{

    private JButton Start;
    private JButton Suspend;
    private JButton Resume;
    private JButton Stop;
    private JButton Stats;

    public SimulationPanel(AppFactory factory)
    {
        super(factory);
        controlPanel.setBackground(Color.white);

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.white);
        buttons.setLayout(new GridLayout(5, 1, 0, 20));

        Start = new JButton("Start");
        Start.addActionListener(this);
        buttons.add(Start);
        Suspend = new JButton("Suspend");
        Suspend.addActionListener(this);
        buttons.add(Suspend);
        Resume = new JButton("Resume");
        Resume.addActionListener(this);
        buttons.add(Resume);
        Stop = new JButton("Stop");
        Stop.addActionListener(this);
        buttons.add(Stop);
        Stats = new JButton("Stats");
        Stats.addActionListener(this);
        buttons.add(Stats);

        controlPanel.add(buttons);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        AppFactory factory = new SimStationFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }

}
