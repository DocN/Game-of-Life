package bcit.comp2526.ButtonListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bcit.comp2526.A2.World;
import bcit.comp2526.A2.WorldSerialWriteReader;

/**
 * SaveButtonListener for listening to save button actions.
 * 
 * @author DrN
 * @version 1.00
 */
public class SaveButtonListener implements ActionListener {
    private World theWorld;
    private WorldSerialWriteReader worldReadWriter;

    /**
     * SaveButtonListener constructor.
     * 
     * @param theWorld
     *            the world we're controlling.
     * @param worldReadWriter
     *            the read/writer for serial world object.
     */
    public SaveButtonListener(World theWorld,
            WorldSerialWriteReader worldReadWriter) {
        this.theWorld = theWorld;
        this.worldReadWriter = worldReadWriter;
    }

    /**
     * actionPerformed for handling actions from listener.
     * 
     * @param a
     *            ActionEvent that occurred.
     */
    public void actionPerformed(ActionEvent a) {
        if (worldReadWriter.writeObject(this.theWorld)) {
            System.out.println("World state saved!");
        }
    }
}
