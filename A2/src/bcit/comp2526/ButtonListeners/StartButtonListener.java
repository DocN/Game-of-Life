package bcit.comp2526.ButtonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

import bcit.comp2526.A2.World;

/**
 * StartButtonListener listens to start button.
 * 
 * @author DrN
 * @version 1.00
 */
public class StartButtonListener implements ActionListener {
    private static final int WAIT_TIME = 200;
    private boolean startStop;
    private Timer timer;
    private World theWorld;
    private JButton sButton;

    /**
     * StartButton Constructor.
     * 
     * @param theWorld
     *            theWorld we're controlling.
     * @param sButton
     *            the start button we're listening to.
     */
    public StartButtonListener(World theWorld, JButton sButton) {
        this.startStop = true;
        this.theWorld = theWorld;
        this.sButton = sButton;
    }

    /**
     * actionPerformed for handling actions from listener.
     * 
     * @param a
     *            ActionEvent that occurred.
     */
    public void actionPerformed(ActionEvent a) {
        // check if the timer is running atm.
        if (startStop) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // take a turn function
                    theWorld.takeTurn();
                    System.out.println("Day: " + theWorld.getDay());
                }
            }, 0, WAIT_TIME);
            sButton.setText("Stop");
            startStop = false;
        } else {
            timer.cancel();
            sButton.setText("Start");
            startStop = true;
        }
    }
}
