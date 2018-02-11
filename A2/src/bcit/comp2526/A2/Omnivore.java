package bcit.comp2526.A2;

import java.util.ArrayList;

/**
 * Herbivore entity that are contained in Cell.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 */
public class Omnivore extends Animal {
    /**
     * Default Serial.
     */
    private static final long serialVersionUID = 1L;
    /*
     * List of entities that the Animal can eat
     */
    private static ArrayList<Class<? extends Entity>> edibleList;
    private static final GridColour OMNICOLOUR = GridColour.BLUE;
    private static final int DAYSBEFORESTARVING = 2;
    // static requirements for herbs to give birth
    private static final int BIRTH_FOOD_REQ = 3;
    private static final int BIRTH_REQ_MATE = 1;
    private static final int BIRTH_BLANK = 3;

    /**
     * Herbivore constructor for making a Herbivore.
     * 
     * @param location
     *            - the location Cell of the Herbivore being created.
     */
    public Omnivore(Cell location) {
        super(location, OMNICOLOUR);
        setupEdibleList();
    }

    /**
     * setupEdibleList sets up a list of things this animal can eat.
     */
    private static void setupEdibleList() {
        edibleList = new ArrayList<Class<? extends Entity>>();
        edibleList.add(Plant.class);
        edibleList.add(Herbivore.class);
    }

    /**
     * getEdibleList returns a list of things the animal can eat.
     * 
     * @return ArrayList of classes animal can eat.
     */
    protected ArrayList<Class<? extends Entity>> getEdibleList() {
        return Omnivore.edibleList;
    }

    /**
     * checkStarved checks if the Herbivore has starved yet.
     * 
     * @return if the herbivore has starved or not
     */
    public boolean checkStarved() {
        int lMeal = this.getLastMeal();
        if (lMeal >= DAYSBEFORESTARVING) {
            return true;
        }
        lMeal++;
        this.setLastMeal(lMeal);
        return false;
    }

    /**
     * birthConditionsMet - checks if all the conditions for birth are true.
     * 
     * @return success or failure boolean
     */
    protected boolean birthConditionsMet() {
        return this.birthConditionsMet(BIRTH_FOOD_REQ, BIRTH_REQ_MATE,
                BIRTH_BLANK);
    }

    /**
     * birthConditionsMet checks if all the required resources for birth are
     * there.
     * 
     * @param foodReq
     *            - required number of food
     * @param mateReq
     *            - required number of mates
     * @param blankReq
     *            - required number of blank spots
     * @return success or failure of conditions
     */
    protected boolean birthConditionsMet(int foodReq, int mateReq,
            int blankReq) {
        Cell currentLoc = this.getLocation();
        Cell[] adjCells = currentLoc.getAdjcentCells();
        int omniCheck = 0;
        int foodCheck = 0;
        int blankCheck = 0;
        for (int i = 0; i < adjCells.length; i++) {
            Entity cEntity = adjCells[i].getCellEntity();
            // if it's green it's a plant
            if (cEntity.getGridColour() == GridColour.GREEN
                    || cEntity.getGridColour() == GridColour.YELLOW) {
                foodCheck = foodCheck + 1;
            } else if (cEntity.getGridColour() == GridColour.BLUE) {
                omniCheck = omniCheck + 1;
            } else if (cEntity.getGridColour() == GridColour.WHITE) {
                blankCheck = blankCheck + 1;
            }
        }
        // if all conditions met return true
        if (omniCheck >= mateReq && foodCheck >= foodReq
                && blankCheck >= blankReq) {
            return true;
        }
        return false;
    }

    /**
     * createAnimal creates an animal object.
     * 
     * @param blankCell
     *            Cell the animal will live in.
     * @return the new Animal
     */
    protected Animal createAnimal(Cell blankCell) {
        Animal newAnimal = null;
        if (blankCell != null) {
            newAnimal = new Omnivore(blankCell);
        }
        return newAnimal;
    }

    /**
     * getDaysBeforeStarving returns the number of days this animal can last
     * before starving.
     * 
     * @return the number of days before this animal starves
     */
    protected int getDaysBeforeStarving() {
        return Omnivore.DAYSBEFORESTARVING;
    }
}
