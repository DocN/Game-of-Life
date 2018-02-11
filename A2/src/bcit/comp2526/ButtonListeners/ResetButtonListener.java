package bcit.comp2526.ButtonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bcit.comp2526.A2.World;

/**
 * ResetButtonListener listens to the reset button in CP.
 * 
 * @author DrN
 * @version 1.00
 */
public class ResetButtonListener implements ActionListener {
    private World theWorld;

    /**
     * ResetButtonListener Constructor.
     * 
     * @param theWorld
     *            the world we're dealing with.
     */
    public ResetButtonListener(World theWorld) {
        this.theWorld = theWorld;
    }

    /**
     * actionPerformed for handling actions from listener.
     * 
     * @param a
     *            ActionEvent that occurred.
     */
    public void actionPerformed(ActionEvent a) {
        theWorld.resetWorld();
    }
}
