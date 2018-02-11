package bcit.comp2526.A2;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

/**
 * GameFrame frame for displaying the world.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 */
public class GameFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int DSIZE = 500;
    private static final Dimension MSIZE = new Dimension(DSIZE, DSIZE);
    private static World world;

    /**
     * GameFrame constructor for displaying world w.
     * 
     * @param w
     *            - the world we want to display
     */
    public GameFrame(final World w) {
        world = w;
    }

    /**
     * init initializes the display for the world GameFrame.
     */
    public void init() {
        setTitle("Assignment 2a");
        setLayout(new GridLayout(world.getRowCount(), world.getColumnCount()));
        // add cells into world.
        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));
            }
        }
        // min size of the panel holding the grid
        this.setMinimumSize(MSIZE);
        // initialize the grid!
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        addMouseListener(new TurnListener(this));

        @SuppressWarnings("unused")
        ControlPanel cPanel = new ControlPanel(world);
    }

    /**
     * takeTurn makes one day pass by in the world when executed.
     */
    public void takeTurn() {
        // make the turn happen in the world
        world.takeTurn();
        // update the GameFrame to reflect changes
        repaint();
        revalidate();
    }
}
