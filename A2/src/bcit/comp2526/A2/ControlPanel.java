package bcit.comp2526.A2;

import javax.swing.JFrame;

/**
 * ControlPanel for GameFrame.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 */
public class ControlPanel {
    private static final int SIZEX = 50;
    private static final int SIZEY = 175;
    private CPFrame frame;

    /**
     * ControlPanel constructor for making CP.
     * 
     * @param theWorld
     *            the world we're controlling.
     * 
     */
    ControlPanel(World theWorld) {
        frame = new CPFrame(theWorld);
        frame.setTitle("Control Panel");
        frame.setVisible(true);
        frame.setBounds(SIZEX, SIZEX, SIZEX, SIZEY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
