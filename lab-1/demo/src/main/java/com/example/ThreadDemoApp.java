package com.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThreadDemoApp {
    private JFrame frame;
    private JButton startButton;
    private JSpinner spinnerThread1;
    private JSpinner spinnerThread2;
    private JSlider sharedSlider;
    private ThreadManager threadManager;

    public ThreadDemoApp() {
        frame = new JFrame("Thread Demo App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create and configure UI components
        startButton = new JButton("Start");
        spinnerThread1 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        spinnerThread2 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        // Create the sharedSlider with tick marks
        sharedSlider = new JSlider(0, 100);
        sharedSlider.setMajorTickSpacing(10); // Set tick interval
        sharedSlider.setPaintTicks(true); // Enable tick marks
        sharedSlider.setPaintLabels(true); // Enable labels

        threadManager = new ThreadManager(sharedSlider);

        // Add action listener to the "Start" button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startButton.getText().equals("Start")) {
                    // Start the threads
                    threadManager.startThreads(
                            (Integer) spinnerThread1.getValue(),
                            (Integer) spinnerThread2.getValue()
                    );
                    startButton.setText("Stop");
                } else {
                    // Stop the threads
                    threadManager.stopThreads();
                    startButton.setText("Start");
                }
            }
        });

        // Add change listeners to JSpinners
        spinnerThread1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newPriority = (int) spinnerThread1.getValue();
                threadManager.setThreadPriority(1, newPriority);
            }
        });

        spinnerThread2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newPriority = (int) spinnerThread2.getValue();
                threadManager.setThreadPriority(2, newPriority);
            }
        });

        // Create panels to organize components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(sharedSlider);

        JPanel spinnerPanel = new JPanel();
        spinnerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        spinnerPanel.add(new JLabel("Thread 1 Priority:"));
        spinnerPanel.add(spinnerThread1);
        spinnerPanel.add(new JLabel("Thread 2 Priority:"));
        spinnerPanel.add(spinnerThread2);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(spinnerPanel);
        centerPanel.add(buttonPanel);

        // Add panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ThreadDemoApp();
            }
        });
    }
}
