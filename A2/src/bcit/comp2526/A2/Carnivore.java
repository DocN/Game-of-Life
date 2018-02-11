package bcit.comp2526.A2;

import java.util.ArrayList;

/**
 * Herbivore entity that are contained in Cell.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 */
public class Carnivore extends Animal {
    /**
     * Declare default serial.
     */
    private static final long serialVersionUID = 1L;
    /*
     * List of entities that the Animal can eat
     */
    private static ArrayList<Class<? extends Entity>> edibleList;
    private static final GridColour CARNICOLOUR = GridColour.MAGENTA;
    private static final int DAYSBEFORESTARVING = 2;
    // static requirements for herbs to give birth
    private static final int BIRTH_FOOD_REQ = 2;
    private static final int BIRTH_REQ_MATE = 1;
    private static final int BIRTH_BLANK = 2;

    /**
     * Carnivore constructor for making a Carnivore.
     * 
     * @param location
     *            - the location Cell of the Herbivore being created.
     */
    public Carnivore(Cell location) {
        super(location, CARNICOLOUR);
        setupEdibleList();
    }

    /**
     * setupEdibleList sets up a list of things Carnivores can eat.
     */
    private static void setupEdibleList() {
        edibleList = new ArrayList<Class<? extends Entity>>();
        edibleList.add(Omnivore.class);
        edibleList.add(Herbivore.class);
    }

    /**
     * getEdibleList returns a list of things the Carnivore can eat.
     * 
     * @return ArrayList of classes animal can eat.
     */
    protected ArrayList<Class<? extends Entity>> getEdibleList() {
        return Carnivore.edibleList;
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
            if (cEntity.getGridColour() == GridColour.BLUE
                    || cEntity.getGridColour() == GridColour.YELLOW) {
                foodCheck = foodCheck + 1;
            } else if (cEntity.getGridColour() == GridColour.MAGENTA) {
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
            newAnimal = new Carnivore(blankCell);
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
        return Carnivore.DAYSBEFORESTARVING;
    }
}
