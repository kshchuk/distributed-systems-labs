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
    private JButton startButton1;
    private JButton startButton2;
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
        startButton1 = new JButton("Start 1");
        startButton2 = new JButton("Start 2");
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
                    threadManager.createThread(1);
                    threadManager.createThread(2);
                    
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

        startButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startButton1.getText().equals("Start 1")) {
                    // Check if the critical section is free
                    if (threadManager.getSemaphore() == 0) {
                        // Set semaphore to indicate the critical section is busy
                        threadManager.setSemaphore(1);
                        
                        // Set lowest priority for the first thread (thread 1)
                        threadManager.createThread(1);
                        threadManager.setThreadPriority(1, Thread.MIN_PRIORITY);
                        spinnerThread1.setValue(Thread.MIN_PRIORITY);
                        
                        // Start the first thread
                        threadManager.startThread(1);
                        
                        startButton1.setText("Stop 1");
                        // startButton2.setEnabled(false); // Disable START 2 button
                    } else {
                        JOptionPane.showMessageDialog(frame, "Occupied by thread");
                    }
                } else {
                    // Free the critical section and stop the first thread
                    threadManager.setSemaphore(0); // Set semaphore to indicate the critical section is free
                    threadManager.stopThread(1);
                    startButton1.setText("Start 1");
                    // startButton2.setEnabled(true); // Enable START 2 button
                }
            }
        });
        
        startButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startButton2.getText().equals("Start 2")) {
                    // Check if the critical section is free
                    if (threadManager.getSemaphore() == 0) {
                        // Set semaphore to indicate the critical section is busy
                        threadManager.setSemaphore(1);
                        
                        // Set highest priority for the second thread (thread 2)
                        threadManager.createThread(2);
                        threadManager.setThreadPriority(2, Thread.MAX_PRIORITY);
                        spinnerThread2.setValue(Thread.MAX_PRIORITY);
                        
                        // Start the second thread
                        threadManager.startThread(2);
                        
                        startButton2.setText("Stop 2");
                        // startButton1.setEnabled(false); // Disable START 1 button
                    } else {
                        JOptionPane.showMessageDialog(frame, "Occupied by thread");
                    }
                } else {
                    // Free the critical section and stop the second thread
                    threadManager.setSemaphore(0);; // Set semaphore to indicate the critical section is free
                    threadManager.stopThread(2);
                    startButton2.setText("Start 2");
                    // startButton1.setEnabled(true); // Enable START 1 button
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

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(startButton1);
        bottomPanel.add(startButton2);

        // Add panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

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
