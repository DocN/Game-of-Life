package bcit.comp2526.A2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * TurnListener used for listening for mouse clicks.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 */
public class TurnListener extends MouseAdapter {
    private GameFrame frame;
    private int turns;

    /**
     * TurnListener creates a listener to listen to the GameFrame.
     * 
     * @param frame
     *            - GameFrame we want to listen to.
     */
    public TurnListener(GameFrame frame) {
        this.frame = frame;
        this.turns = 0;
    }

    /**
     * mouseClicked executes a GameFrame turn when clicked.
     * 
     * @param e
     *            - The mouseEvent
     */
    public void mouseClicked(MouseEvent e) {
        frame.takeTurn();
        this.turns = this.turns + 1;
        System.out.println("DAY " + this.turns);
    }
}
