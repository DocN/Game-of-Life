package bcit.comp2526.DoubleLL;

/**
 * CouldNotRemoveException thrown when unable to add to linklist.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 */
public class CouldNotRemoveException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * CouldNotRemoveException - Constructor for creating exception.
     * 
     * @param message
     *            error message.
     */
    public CouldNotRemoveException(String message) {
        super(message);
    }
}
