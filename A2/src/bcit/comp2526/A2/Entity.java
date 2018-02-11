package bcit.comp2526.A2;

import java.io.Serializable;

/**
 * Entity - Entity class for entities in Cells for the world.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 */
public class Entity implements Serializable {
    /**
     * default serial.
     */
    private static final long serialVersionUID = 1L;
    private Cell location;
    private GridColour gridColour;
    private boolean acted;

    /**
     * Entity constructor for making a Entities object.
     * 
     * @param location
     *            - the location cell of the entity created.
     * @param gridColour
     *            - the grid colour of the entity created.
     */
    public Entity(Cell location, GridColour gridColour) {
        this.setGridColour(gridColour);
        this.setCell(location);
        this.resetActed();
    }

    /**
     * setCell sets the cell the entity is currently in.
     * 
     * @param loc
     *            - location cell the entity is in.
     */
    public void setCell(Cell loc) {
        this.location = loc;
    }

    /**
     * setGridColour sets the gridColour of the entity.
     * 
     * @param gridColour
     *            - the grid colour we want to change to.
     */
    protected void setGridColour(GridColour gridColour) {
        this.gridColour = gridColour;
    }

    /**
     * getGridColour returns the specie's GridColour.
     * 
     * @return - the grid colour.
     */
    public GridColour getGridColour() {
        return this.gridColour;
    }

    /**
     * getLocation returns the location of the entity.
     * 
     * @return - the Cell of the entity.
     */
    protected Cell getLocation() {
        return this.location;
    }

    /**
     * setLocation sets a new location of the entity.
     * 
     * @param location
     *            - new Cell location of the entity
     */
    protected void setLocation(Cell location) {
        this.location = location;
    }

    /**
     * act - performs an entity action.
     * 
     * - the day the action was performed.
     */
    protected void act() {
        // blank spot does nothing..
        return;
    }

    /**
     * acted sets acted to true.
     */
    protected void acted() {
        this.acted = true;
    }

    /**
     * resetActed sets acted back to false.
     */
    protected void resetActed() {
        this.acted = false;
    }

    /**
     * getActed returns the current status of acted.
     * 
     * @return the current acted status
     */
    protected boolean getActed() {
        return this.acted;
    }
}
