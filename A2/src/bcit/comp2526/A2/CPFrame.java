package bcit.comp2526.A2;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bcit.comp2526.ButtonListeners.LoadButtonListener;
import bcit.comp2526.ButtonListeners.ResetButtonListener;
import bcit.comp2526.ButtonListeners.SaveButtonListener;
import bcit.comp2526.ButtonListeners.StartButtonListener;

/**
 * CPFrame for control interface.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 */
public class CPFrame extends JFrame {
    private static final long serialVersionUID = -5859470684819523810L;
    private Container controlPanel;
    private JButton startButton;
    private JButton restartButton;
    private JButton loadButton;
    private JButton saveButton;
    private World theWorld;
    private WorldSerialWriteReader worldReadWriter;

    /**
     * CPFrame constructor for generating the control panel frame.
     * 
     * @param theWorld
     *            theWorld we're controlling.
     */
    CPFrame(World theWorld) {
        controlPanel = this.getContentPane();
        // declare buttons
        startButton = new JButton("Start");
        restartButton = new JButton("Reset");
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        // add them to a subpanel
        JPanel subPanel = new JPanel();
        subPanel.add(startButton);
        subPanel.add(restartButton);
        subPanel.add(loadButton);
        subPanel.add(saveButton);
        // load subpanel into cp
        controlPanel.add(subPanel);
        // load up world and serializer for world
        this.theWorld = theWorld;
        worldReadWriter = new WorldSerialWriteReader();
        // setup listens for buttons
        startButton.addActionListener(
                new StartButtonListener(this.theWorld, startButton));
        restartButton.addActionListener(new ResetButtonListener(this.theWorld));
        saveButton.addActionListener(
                new SaveButtonListener(this.theWorld, worldReadWriter));
        loadButton.addActionListener(
                new LoadButtonListener(this.theWorld, worldReadWriter));

    }
}
