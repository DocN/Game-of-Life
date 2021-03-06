package bcit.comp2526.A2;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Main Driver Class.
 * 
 * @author BCIT
 * @version 1.00
 *
 */
public final class Main {
    private static final Toolkit TOOLKIT;
    private static final int DIMENSIONS = 50;
    private static final float SAREA = 0.80f;
    private static final float W_MAX_PERCENT = 100.0f;
    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    /**
     * main function for A2 driver.
     * 
     * @param argv
     *            - no input required
     */
    public static void main(final String[] argv) {
        final GameFrame frame;
        final World world;

        RandomGenerator.reset();
        // creates the World
        world = new World(DIMENSIONS, DIMENSIONS);
        world.init();
        // creates and positions the frame that contains the World view.
        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * position sets the frame location on screen.
     * 
     * @param frame
     *            - frame being placed on screen
     */
    private static void position(final GameFrame frame) {
        final Dimension size;

        size = calculateScreenArea(SAREA, SAREA);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }

    /**
     * centreOnScreen moves the frame to the center of the screen.
     * 
     * @param size
     *            the dimensions of the frame
     * @return the position of the center of the screen.
     */
    public static Point centreOnScreen(final Dimension size) {
        final Dimension screenSize;

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width) / 2,
                (screenSize.height - size.height) / 2));
    }

    /**
     * calculateScreenArea - calculates allocate to the gameFrame.
     * 
     * @param widthPercent
     *            - width of the screen percentage.
     * @param heightPercent
     *            - height of the screen percentage.
     * @return -the area of dimension of the screen.
     */
    public static Dimension calculateScreenArea(final float widthPercent,
            final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int width;
        final int height;
        final int size;

        // sets the frame dimensions
        if ((widthPercent <= 0.0f) || (widthPercent > W_MAX_PERCENT)) {
            throw new IllegalArgumentException("widthPercent cannot be "
                    + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) || (heightPercent > W_MAX_PERCENT)) {
            throw new IllegalArgumentException("heightPercent cannot be "
                    + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size = Math.min(width, height);
        area = new Dimension(size, size);

        return (area);
    }
}
