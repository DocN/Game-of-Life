package bcit.comp2526.A2;

import java.util.ArrayList;

/**
 * Plant Entity.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 */
public class Plant extends Entity {
    /**
     * default serial.
     */
    private static final long serialVersionUID = 1L;
    private static GridColour plantColour = GridColour.GREEN;
    private static final int REQBLANKS = 3;
    private static final int REQNPLANTS = 2;

    /**
     * Plant constructor for making a Plant in Cell location.
     * 
     * @param location
     *            - the location Cell of the new Plant
     */
    public Plant(Cell location) {
        super(location, plantColour);
    }

    /**
     * act does all the things a plant does in a turn.
     */
    protected void act() {
        if (this.getActed()) {
            return;
        }
        this.seed();
        this.acted();
    }

    /**
     * seed generates a new plant if the right conditions are met.
     * 
     * - the current day for giving birthday to new plants
     */
    public void seed() {
        /*
         * set all variables needed to check the area for optimal seeding
         * conditions
         */
        Cell myLocation = super.getLocation();
        Cell[] adjCells = myLocation.getAdjcentCells();
        ArrayList<Cell> blankCells = new ArrayList<Cell>();
        int neighbourPlantCount = 0;
        int blankSpots = 0;

        // go through all the adjCells to increment to try and meet conditions
        for (int i = 0; i < adjCells.length; i++) {
            Cell currentCell = adjCells[i];
            // make sure the current cell isn't nothing
            if (currentCell != null) {
                if (currentCell.getCellEntity().getClass() == Plant.class) {
                    // increment count for plants next door
                    neighbourPlantCount++;
                }
                // check if we have empty spots
                if (currentCell.getCellEntity()
                        .getGridColour() == GridColour.WHITE) {
                    blankSpots++;
                    blankCells.add(currentCell);
                }
            }
        }
        if (blankSpots >= REQBLANKS && neighbourPlantCount >= REQNPLANTS) {
            // randomly pick one of the empty spots
            int newSpot = (int) (Math.floor(Math.random() * (blankCells.size()))
                    + 1);
            newSpot--;
            Cell plantingCell = blankCells.get(newSpot);
            // make a new plant and store it in the cell
            Plant newPlant = new Plant(plantingCell);
            newPlant.acted();
            plantingCell.setCellEntity(newPlant);
        }
    }
}
