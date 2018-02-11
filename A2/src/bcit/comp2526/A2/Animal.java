package bcit.comp2526.A2;

import java.util.ArrayList;

/**
 * Animal class for animals.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 */
public abstract class Animal extends Entity {
    /**
     * days since last meal for animal.
     */
    protected static final int INTLASTMEAL = 0;
    /**
     * Default Serial.
     */
    private static final long serialVersionUID = 1L;
    private int lastMeal;

    /**
     * Animal constructor for animals.
     * 
     * @param location
     *            - location cell of the animal
     * @param gridColour
     *            - grid colour of the animal
     */
    public Animal(Cell location, GridColour gridColour) {
        super(location, gridColour);
        this.initLastMeal();
    }

    /**
     * initLastMeal sets the initial day since last eaten.
     */
    private void initLastMeal() {
        this.lastMeal = INTLASTMEAL;
    }

    /**
     * createAnimal births a new animal.
     * 
     * @param blankCell
     *            - the blankCell storing new animal
     * @return a birthed animal
     */
    protected abstract Animal createAnimal(Cell blankCell);

    /**
     * birthConditionsMet abstract checks if the conditions of the animal are
     * met.
     * 
     * @return true or false conditions met.
     */
    protected abstract boolean birthConditionsMet();

    /**
     * getEdibleList returns a list of things the animal can eat.
     * 
     * @return ArrayList of classes animal can eat.
     */
    protected abstract ArrayList<Class<? extends Entity>> getEdibleList();

    /**
     * giveBirth attempts to make the animal give birth.
     */

    /**
     * getDaysBeforeStarving returns the number of days before this animal
     * starves.
     * 
     * @return the number of days before animal starves
     */
    protected abstract int getDaysBeforeStarving();

    /**
     * giveBirth gives birth to a new animal.
     */
    public void giveBirth() {
        // make sure the conditions are met for birth
        if (this.birthConditionsMet()) {
            // get all the blank spots available and pick one
            ArrayList<Cell> blankSpots = this.getBlankSpots();
            int spotIndex = this.pickBlankSpot(blankSpots.size());
            if (blankSpots.size() > 0 && spotIndex >= 0) {
                Cell blankCell = blankSpots.get(spotIndex);
                Animal newAnimal = this.createAnimal(blankCell);
                // check to make sure an animal was actually created
                if (newAnimal != null) {
                    newAnimal.acted();
                    blankCell.setCellEntity(newAnimal);
                }
            }
        }
    }

    /**
     * move - moves the animal to another random cell.
     * 
     * @return success or failure boolean
     */
    public boolean move() {
        Cell myLocation = this.getLocation();
        boolean foundFood = false;

        // look for food spots first
        ArrayList<Cell> possibleSpots = this.searchForFood();
        Cell newLocation = null;

        // the animal found food
        if (possibleSpots.size() > 0) {
            foundFood = true;
        }
        // when food isn't found move to blanks instead
        if (!foundFood) {
            possibleSpots = this.getBlankSpots();
            // if there are no plants go to a blank spot
            if (possibleSpots.size() <= 0) {
                return false;
            }
        }

        // pick a random spot to move to
        if (possibleSpots.size() > 0) {
            // randomly pick a newSpot
            int newSpot = (int) (Math
                    .floor(Math.random() * (possibleSpots.size())) + 1);
            newSpot--;
            newLocation = possibleSpots.get(newSpot).getCellEntity()
                    .getLocation();
        }
        // perform move and sometimes eat
        if (newLocation != null) {
            if (foundFood) {
                this.eat(newLocation);
            }
            this.replaceCell(myLocation, newLocation);
            return true;
        }
        return false;
    }

    /**
     * searchForFood checks the area for food that animal can eat.
     * 
     * @return ArrayList of food animal can eat.
     */
    private ArrayList<Cell> searchForFood() {
        ArrayList<Class<? extends Entity>> edibleList = this.getEdibleList();
        ArrayList<Cell> possibleSpots = new ArrayList<Cell>();
        // check for Omnivores
        for (int i = 0; i < edibleList.size(); i++) {
            if (edibleList.get(i) == Omnivore.class) {
                possibleSpots = this.getOmniSpots();
                if (possibleSpots.size() > 0) {
                    return possibleSpots;
                }
            }
        }
        // check for Herbivores
        for (int i = 0; i < edibleList.size(); i++) {
            if (edibleList.get(i) == Herbivore.class) {
                possibleSpots = this.getHerbSpots();
                if (possibleSpots.size() > 0) {
                    return possibleSpots;
                }
            }
        }
        // check for Plants
        for (int i = 0; i < edibleList.size(); i++) {
            if (edibleList.get(i) == Plant.class) {
                possibleSpots = this.getPlantSpots();
                if (possibleSpots.size() > 0) {
                    return possibleSpots;
                }
            }
        }
        return possibleSpots;
    }

    /**
     * pickBlankSpot picks a random index from pool of size.
     * 
     * @param size
     *            the number of blank spots
     * @return random int spotIndex
     */
    protected int pickBlankSpot(int size) {
        int newSpot = (int) (Math.floor(Math.random() * (size)) + 1);
        newSpot--;
        return newSpot;
    }

    /**
     * getBlankSpots returns the blank spots around animal.
     * 
     * @return ArrayList of cells containing blank spots around animal
     */
    protected ArrayList<Cell> getBlankSpots() {
        return getSpots(GridColour.WHITE);
    }

    /**
     * getBlankSpots returns the plant spots around animal.
     * 
     * @return ArrayList of cells containing plant spots around animal
     */
    protected ArrayList<Cell> getPlantSpots() {
        return getSpots(GridColour.GREEN);
    }

    /**
     * getBlankSpots returns the herb spots around animal.
     * 
     * @return ArrayList of cells containing herb spots around animal
     */
    protected ArrayList<Cell> getHerbSpots() {
        return getSpots(GridColour.YELLOW);
    }

    /**
     * getBlankSpots returns the Omni spots around animal.
     * 
     * @return ArrayList of cells containing Omni spots around animal
     */
    protected ArrayList<Cell> getOmniSpots() {
        return getSpots(GridColour.BLUE);
    }

    /**
     * getSpots get the spots around the animal of a specific colour.
     * 
     * @param colour
     *            The colour we're looking for
     * @return An ArrayList of cells of a specific colour around animal
     */
    private ArrayList<Cell> getSpots(GridColour colour) {
        // get the animal's current location
        Cell location = this.getLocation();
        Cell[] adjCells = location.getAdjcentCells();
        ArrayList<Cell> possibleSpots = new ArrayList<Cell>();
        // check spots around animal for a specific type.
        for (int i = 0; i < adjCells.length; i++) {
            Cell currentCell = adjCells[i];
            GridColour currentColour = currentCell.getCellEntity()
                    .getGridColour();
            if (currentColour == colour) {
                possibleSpots.add(currentCell);
            }
        }
        return possibleSpots;
    }

    /**
     * eat function eats the newLocation entity.
     * 
     * @param newLocation
     *            - new cell we're moving to/eating.
     * @return if the Herbivore got to eat or not boolean
     */
    public boolean eat(Cell newLocation) {
        ArrayList<Class<? extends Entity>> edibleList = this.getEdibleList();
        if (newLocation != null) {
            // confirm it's a plant
            for (int i = 0; i < edibleList.size(); i++) {
                Class<? extends Entity> currentEdible = edibleList.get(i);
                if (newLocation.getCellEntity().getClass() == currentEdible) {
                    // reset last meal
                    this.setLastMeal(INTLASTMEAL);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checkStarved checks if the Animal has starved yet.
     * 
     * @return if the Animal has starved or not
     */
    protected boolean checkStarved() {
        int lMeal = this.getLastMeal();
        int daysBeforeStarving = this.getDaysBeforeStarving();
        if (lMeal >= daysBeforeStarving) {
            return true;
        }
        lMeal++;
        this.setLastMeal(lMeal);
        return false;
    }

    /**
     * getLastMeal returns this.lastMeal.
     * 
     * @return the last time animal ate.
     */
    protected int getLastMeal() {
        return this.lastMeal;
    }

    /**
     * setLastMeal sets the last time the Herb has eaten.
     * 
     * @param lastMeal
     *            last time the animal has eaten.
     */
    protected void setLastMeal(int lastMeal) {
        this.lastMeal = lastMeal;
    }

    /**
     * replaceCell - replaces the 2nd cell with the 1st one and empties the 1st.
     * 
     * @param myLocation
     *            - current cell location
     * @param newLocation
     *            - new location that cell is moving to
     * @return - replaced successfully boolean
     */
    protected boolean replaceCell(Cell myLocation, Cell newLocation) {
        Entity myEntity = myLocation.getCellEntity();

        if (myLocation != null && newLocation != null) {
            // set the new location to this herb
            newLocation.setCellEntity(myEntity);
            newLocation.getCellEntity().setLocation(newLocation);
            // set the current cell to a new blank
            myLocation.setCellEntity(new Entity(myLocation, GridColour.WHITE));
            return true;
        }
        return false;
    }

    /**
     * act performs the acts of an animal.
     * 
     * - the day the act was performed in the world.
     */
    protected void act() {
        // if this animal has already acted don't act.
        if (this.getActed()) {
            return;
        }
        // try to give birth
        this.giveBirth();
        // move around
        this.move();
        // check if the animal has starved if it has replace it with a blank
        if (this.checkStarved()) {
            Cell animalCell = this.getLocation();
            // replace with blank entity
            animalCell.setCellEntity(new Entity(animalCell, GridColour.WHITE));
        }
        // set acted status
        this.acted();
    }

}
