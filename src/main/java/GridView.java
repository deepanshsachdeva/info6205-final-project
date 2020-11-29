
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridView implements ActionListener {
    private MyPanel panel = null;
    private VirusSimulation sim = new VirusSimulation();
    private JButton btnStart;

    // constructor
    public GridView() {
        // Initialize gird
        initializeGrid();
    }

    private void initializeGrid(){
        //Simulation main screen frame.
        JFrame frame = new JFrame();
        frame.setTitle("Covid-19 virus spreading simulation");
        frame.setResizable(false);
        int frameWidth = 1400; // need to move in diff file
        int frameHeight = 800; // need to move in diff file
        frame.setSize(frameWidth, frameHeight);
        frame.setBackground(Color.GREEN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new MyPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(getParamsPanel());
        splitPane.setDividerLocation(300);
        splitPane.setRightComponent(panel);

        frame.add(splitPane);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GridView();
    }


    public void actionPerformed(ActionEvent actionEvent) {
        if (sim.isPaused()) {
            btnStart.setText("Pause");
            sim.startSim();
        } else if (sim.isRunning()) {
            sim.pauseSim();
            sim.setRunning(false);
            btnStart.setText("Start");
        } else {
            System.out.println("Start was pressed");
            sim.addObserver(panel);
            sim.startSim();
            sim.setRunning(true); // force this on early, because we're about to reset the buttons
            btnStart.setText("Pause");
        }
    }


    private JPanel getParamsPanel() {
        btnStart = new JButton("Start");
        btnStart.setActionCommand("Start");
        btnStart.addActionListener(this);

        JPanel paramsPanel = new JPanel();

        paramsPanel.setLayout(new BoxLayout(paramsPanel, BoxLayout.Y_AXIS));

        /**
         * Population
         *
         */
        paramsPanel.add(new Label("Population:"));
        JSlider populationSlider = new JSlider(100, 300, 200);
        populationSlider.setMinorTickSpacing(10);
        populationSlider.setMajorTickSpacing(50);
        populationSlider.setPaintTicks(true);
        populationSlider.setPaintLabels(true);

        final Label populationLabel = new Label();
        populationLabel.setText(String.valueOf(populationSlider.getValue()));

        populationSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider jslider = (JSlider) e.getSource();
                populationLabel.setText(String.valueOf(jslider.getValue()));
            }
        });

        paramsPanel.add(populationSlider);
        paramsPanel.add(populationLabel);

        /**
         * Infected Population
         *
         */
        paramsPanel.add(new Label("Infected Population:"));
        JSlider infectedSlider = new JSlider(0, 100, 5);
        infectedSlider.setMinorTickSpacing(5);
        infectedSlider.setMajorTickSpacing(10);
        infectedSlider.setPaintTicks(true);
        infectedSlider.setPaintLabels(true);

        final Label infectedLabel = new Label();
        infectedLabel.setText(String.valueOf(infectedSlider.getValue()));

        infectedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider jslider = (JSlider) e.getSource();
                infectedLabel.setText(String.valueOf(jslider.getValue()));
            }
        });

        paramsPanel.add(infectedSlider);
        paramsPanel.add(infectedLabel);

        /**
         * Social Distance
         *
         */
        paramsPanel.add(new Label("Social Distance:"));
        JSlider socialDistanceSlider = new JSlider(0, 100, 5);
        socialDistanceSlider.setMinorTickSpacing(5);
        socialDistanceSlider.setMajorTickSpacing(10);
        socialDistanceSlider.setPaintTicks(true);
        socialDistanceSlider.setPaintLabels(true);

        final Label socialDistanceLabel = new Label();
        socialDistanceLabel.setText(String.valueOf(infectedSlider.getValue()));

        socialDistanceSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider jslider = (JSlider) e.getSource();
                socialDistanceLabel.setText(String.valueOf(jslider.getValue()));
            }
        });

        paramsPanel.add(socialDistanceSlider);
        paramsPanel.add(socialDistanceLabel);

        /**
         * Death Rate
         *
         */
        paramsPanel.add(new Label("Death Rate:"));
        JSlider deathSlider = new JSlider(0, 100, 5);
        deathSlider.setMinorTickSpacing(5);
        deathSlider.setMajorTickSpacing(10);
        deathSlider.setPaintTicks(true);
        deathSlider.setPaintLabels(true);

        final Label deathLabel = new Label();
        deathLabel.setText(String.valueOf(infectedSlider.getValue()));

        socialDistanceSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider jslider = (JSlider) e.getSource();
                deathLabel.setText(String.valueOf(jslider.getValue()));
            }
        });

        paramsPanel.add(deathSlider);
        paramsPanel.add(deathLabel);

        paramsPanel.add(btnStart);

        return paramsPanel;
    }

}