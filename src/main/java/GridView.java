
import javax.swing.*;
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
        frame.setLayout(new BorderLayout());
        frame.add(getNorthPanel(), BorderLayout.NORTH);
        panel = new MyPanel();
        frame.add(panel, BorderLayout.CENTER);

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


    private JPanel getNorthPanel() {
        btnStart = new JButton("Start");
        btnStart.setActionCommand("Start");
        btnStart.addActionListener(this);
        JPanel pan = new JPanel();
        pan.add(btnStart);
        return pan;
    }

}