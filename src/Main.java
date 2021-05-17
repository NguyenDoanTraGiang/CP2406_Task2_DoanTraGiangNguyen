import View.EditorPanel;
import View.SimulationPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 600;
    private static SimulationPanel simulationPanel = new SimulationPanel();
    private static EditorPanel editorPanel = new EditorPanel();
    private static final int SCALE = 8;

    public static void main(String[] args) {
        // Simulation Window setup:
        JFrame mainWindow = new JFrame("Traffic Simulator");
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        //Status Bar
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 0));
        bottomPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        JLabel modeLabel = new JLabel("Mode: ");
        bottomPanel.add(modeLabel);
        JLabel statusLabel = new JLabel("Status: ");
        bottomPanel.add(statusLabel);
        mainWindow.add(bottomPanel, BorderLayout.SOUTH);

        // GuideLine Bar
        JPanel guideLineBar = new JPanel();
        guideLineBar.setLayout(new GridLayout(1, 0));
        guideLineBar.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        JLabel guideLine = new JLabel("Hint: Press 'New' to create new map and begin customization");
        guideLineBar.add(guideLine);
        mainWindow.add(guideLineBar, BorderLayout.NORTH);

        //Menu bar:
        Container westBar = new Container();
        westBar.setLayout(new GridLayout(15, 1, 0, 10));
        mainWindow.add(westBar, BorderLayout.WEST);

        JButton newMapBtn = new JButton("New");
        newMapBtn.addActionListener(e -> {
            guideLine.setText("Hint: Click on yellow line to create new road. Click a road to create a new road. When satisfied with layout, press 'Load Map'");
            simulationPanel.setVisible(false);
            mainWindow.remove(editorPanel);
            editorPanel = new EditorPanel();
            editorPanel.newMap();
            editorPanel.setScale(SCALE);
            mainWindow.add(editorPanel);
            editorPanel.setVisible(true);
            statusLabel.setText("Status: New Map");
            mainWindow.validate();
            mainWindow.repaint();
        });
        westBar.add(newMapBtn);

        JButton exitProgramBtn = new JButton("Exit");
        exitProgramBtn.addActionListener(e -> System.exit(0));
        westBar.add(exitProgramBtn);

        JButton loadSimItem = new JButton("Load Map");
        loadSimItem.addActionListener(e ->{
            guideLine.setText("Now you can add more vehicle and start the simulation");
        });
        westBar.add(loadSimItem);

        JButton spawnItem = new JButton("Add Vehicles");
        spawnItem.setEnabled(false);
        westBar.add(spawnItem);

        JButton startSimItem = new JButton("Start");
        startSimItem.setEnabled(false);
        startSimItem.addActionListener(e -> {
            simulationPanel.simulate();
            statusLabel.setText("Status: Simulation Started");
            simulationPanel.setStopSim(false);
            mainWindow.validate();
            mainWindow.repaint();
        });
        westBar.add(startSimItem);

        spawnItem.addActionListener(e -> {
            String spawnInput = JOptionPane.showInputDialog("Total number of Vehicles to spawn:");
            int spawns = Integer.parseInt(spawnInput);
            simulationPanel.setVehicleSpawn(spawns);
            String spawnRateInput = JOptionPane.showInputDialog("Number of Simulation tics between spawns:");
            int spawnRate = Integer.parseInt(spawnRateInput);
            simulationPanel.setVehicleSpawnRate(spawnRate);
        });

        JButton stopSimItem = new JButton("Stop");
        stopSimItem.setEnabled(false);
        stopSimItem.addActionListener(e -> {
            simulationPanel.setStopSim(true);
            statusLabel.setText("Status: Simulation Stopped");
            mainWindow.validate();
            mainWindow.repaint();
        });
        westBar.add(stopSimItem);

        loadSimItem.addActionListener(e -> {
            statusLabel.setText("Status: Map Loaded!");
            editorPanel.setVisible(false);
            simulationPanel = new SimulationPanel();
            simulationPanel.setScale(SCALE);
            simulationPanel.loadMap(editorPanel.getRoads(), editorPanel.getLights());
            mainWindow.add(simulationPanel);
            startSimItem.setEnabled(true);
            spawnItem.setEnabled(true);
            stopSimItem.setEnabled(true);
            mainWindow.repaint();
        });

        JButton setUpdateRateItem = new JButton("Update Rate");
        setUpdateRateItem.addActionListener(e -> {
            String updateRateInput = JOptionPane.showInputDialog("Enter the Update Rate of the Simulation");
            int updateRate = Integer.parseInt(updateRateInput);
            simulationPanel.setUpdateRate(updateRate);
            statusLabel.setText("Status: Update Rate set to " + updateRate);
            mainWindow.validate();
            mainWindow.repaint();
        });
        westBar.add(setUpdateRateItem);

        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);

    }
}
