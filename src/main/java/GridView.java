
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridView implements ActionListener, Constants {
    private MyPanel panel = null;
    private VirusSimulation sim = new VirusSimulation();
    private JButton btnStart;
    JSplitPane splitPane;
    JFrame frame;
    /**
     * Params for factor
     *
     */
    private int total_population;
    private int infected_population;
    private int social_distancing;
    private int infection_rate;
    private int death_rate;

    public int getTotalPopulation() {
        return total_population;
    }

    public void setTotalPopulation(int total_population) {
        this.total_population = total_population;
    }

    public int getInfectedPopulation() {
        return infected_population;
    }

    public void setInfectedPopulation(int infected_population) {
        this.infected_population = infected_population;
    }

    public int getSocialDistancing() {
        return social_distancing;
    }

    public void setSocialDistancing(int social_distancing) {
        this.social_distancing = social_distancing;
    }

    public int getInfectionRate() {
        return infection_rate;
    }

    public void setInfectionRate(int infection_rate) {
        this.infection_rate = infection_rate;
    }

    public int getDeathRate() {
        return death_rate;
    }

    public void setDeathRate(int death_rate) {
        this.death_rate = death_rate;
    }

    // constructor
    public GridView() {
        initializeParams();

        // Initialize gird
        initializeGrid();
    }

    private void initializeParams(){
        setTotalPopulation(200);
        setInfectedPopulation(5);
        setSocialDistancing(6);
        setInfectionRate(7);
        setDeathRate(8);
    }

    private void initializeGrid(){
        //Simulation main screen frame.
        frame = new JFrame();
        frame.setTitle(APP_NAME);
        frame.setResizable(false);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        frame.setBackground(Color.GREEN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        Dimension maxSize = new Dimension(400, 500);
        JPanel paramPanel = getParamsPanel();
        paramPanel.setMaximumSize(maxSize);
        splitPane.setLeftComponent(paramPanel);
        JPanel emptyPanel = new JPanel();

        splitPane.setRightComponent(emptyPanel);

        splitPane.setDividerLocation(300);
        frame.add(splitPane);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GridView();
    }


    public void actionPerformed(ActionEvent actionEvent) {

        int population = getTotalPopulation();
/*

        System.out.println("Total Population = "+getTotalPopulation());
        System.out.println("Infected Population = "+getInfectedPopulation());
        System.out.println("Social Distancing = "+getSocialDistancing());
        System.out.println("Infection Rate = "+getInfectionRate());
        System.out.println("Death Rate = "+getDeathRate());
*/

       if (sim.isPaused()) {
           btnStart.setText("Pause");
           sim.startSim();
       } else if (sim.isRunning()) {
           sim.pauseSim();
           sim.setRunning(false);
           btnStart.setText("Start");
       } else {
           System.out.println("Start was pressed");
           panel = new MyPanel(population);
           splitPane.setRightComponent(panel);

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
        JSlider populationSlider = new JSlider(100, 300, getTotalPopulation());
        populationSlider.setMinorTickSpacing(10);
        populationSlider.setMajorTickSpacing(50);
        populationSlider.setPaintTicks(true);
        populationSlider.setPaintLabels(true);

        final Label populationLabel = new Label(String.valueOf(populationSlider.getValue()));

        populationSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider jslider = (JSlider) e.getSource();
                setTotalPopulation(jslider.getValue());

                populationLabel.setText(String.valueOf(getTotalPopulation()));
            }
        });

        paramsPanel.add(populationSlider);
        paramsPanel.add(populationLabel);

        /**
         * Infected Population
         *
         */
        paramsPanel.add(new Label("Infected Population:"));
        JSlider infectedSlider = new JSlider(0, 100, getInfectedPopulation());
        infectedSlider.setMinorTickSpacing(5);
        infectedSlider.setMajorTickSpacing(10);
        infectedSlider.setPaintTicks(true);
        infectedSlider.setPaintLabels(true);

        final Label infectedLabel = new Label(infectedSlider.getValue()+"%");

        infectedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider jslider = (JSlider) e.getSource();
                setInfectedPopulation(jslider.getValue());

                infectedLabel.setText(getInfectedPopulation()+"%");
            }
        });

        paramsPanel.add(infectedSlider);
        paramsPanel.add(infectedLabel);

        /**
         * Infection Rate
         *
         */
        paramsPanel.add(new Label("Infection Rate:"));
        JSlider infectionSlider = new JSlider(0, 100, getInfectionRate());
        infectionSlider.setMinorTickSpacing(5);
        infectionSlider.setMajorTickSpacing(10);
        infectionSlider.setPaintTicks(true);
        infectionSlider.setPaintLabels(true);

        final Label infectionLabel = new Label(infectionSlider.getValue()+"%");

        infectionSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider jslider = (JSlider) e.getSource();
                setInfectionRate(jslider.getValue());
                infectionLabel.setText(getInfectionRate()+"%");
            }
        });

        paramsPanel.add(infectionSlider);
        paramsPanel.add(infectionLabel);

        /**
         * Social Distance
         *
         */
        paramsPanel.add(new Label("Social Distance:"));
        JSlider socialDistanceSlider = new JSlider(0, 100, getSocialDistancing());
        socialDistanceSlider.setMinorTickSpacing(5);
        socialDistanceSlider.setMajorTickSpacing(10);
        socialDistanceSlider.setPaintTicks(true);
        socialDistanceSlider.setPaintLabels(true);

        final Label socialDistanceLabel = new Label(socialDistanceSlider.getValue()+"%");

        socialDistanceSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider jslider = (JSlider) e.getSource();
                setSocialDistancing(jslider.getValue());

                socialDistanceLabel.setText(getSocialDistancing()+"%");
            }
        });

        paramsPanel.add(socialDistanceSlider);
        paramsPanel.add(socialDistanceLabel);

        paramsPanel.add(btnStart);

        return paramsPanel;
    }

}