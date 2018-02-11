package bcit.comp2526.A2;

import static java.awt.Color.BLACK;

import java.awt.Color;
import java.awt.Component;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import bcit.comp2526.DoubleLL.CouldNotAddException;
import bcit.comp2526.DoubleLL.DoubleLinkedList;

/**
 * World class for creating our world.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 */
public class World implements Serializable {
    /**
     * default serial.
     */
    private static final long serialVersionUID = 1L;

    private static final int RAND_MAX = 100;
    private static final int PLANT_PERCENTAGE = 70;
    private static final int HERB_PERCENTAGE = 50;
    private static final int OMNI_PERCETANGE = 40;
    private static final int CARNI_PERCENTAGE = 32;
    private static final int FIRST_DAY = 0;
    private int x;
    private int y;
    private Cell[][] worldGrid;
    private int day;

    /**
     * World constructor for making word by dimensions x,y.
     * 
     * @param x
     *            - the row dimension
     * @param y
     *            - the column dimension
     */
    public World(int x, int y) {
        this.x = x;
        this.y = y;
        // create the world
        worldGrid = new Cell[x][y];
        // generate the cells and the things that exist in the cells
    }

    /**
     * init - starts generating the world.
     */
    public void init() {
        // creates and positions the frame that contains the World view.
        this.generateWorld();
        this.day = FIRST_DAY;
    }

    /**
     * getRowCount returns the number of rows in the World.
     * 
     * @return - rows in the world integer.
     */
    public int getRowCount() {
        return this.x;
    }

    /**
     * getColumnCount returns the number of columns in the world.
     * 
     * @return - columns in the world integer.
     */
    public int getColumnCount() {
        return this.y;
    }

    /**
     * getCellAt returns the cell as a component.
     * 
     * @param row
     *            - row of the cell we're getting in world.
     * @param column
     *            - column of the cell we're getting in world.
     * @return - java component of Cell to display.
     */
    public Component getCellAt(int row, int column) {
        Cell currentCell = worldGrid[row][column];

        Entity currentEntity = currentCell.getCellEntity();
        JPanel panel = currentCell.getPanel();
        // base panel colour on entity type
        // for plant entity
        if (currentEntity.getGridColour() == GridColour.GREEN) {
            panel.setBackground(Color.GREEN);
            // for blank entity
        } else if (currentEntity.getGridColour() == GridColour.WHITE) {
            panel.setBackground(Color.WHITE);
            // for herbivores entity
        } else if (currentEntity.getGridColour() == GridColour.YELLOW) {
            panel.setBackground(Color.YELLOW);
        } else if (currentEntity.getGridColour() == GridColour.BLUE) {
            panel.setBackground(Color.BLUE);
        } else if (currentEntity.getGridColour() == GridColour.MAGENTA) {
            panel.setBackground(Color.MAGENTA);
        }
        // setup the border for the component
        MatteBorder border = BorderFactory.createMatteBorder(0, 0, 1, 1, BLACK);
        if (column == 0 && row == 0) {
            border = BorderFactory.createMatteBorder(1, 1, 1, 1, BLACK);
        } else if (column == 0) {
            border = BorderFactory.createMatteBorder(0, 1, 1, 1, BLACK);
        } else if (row == 0) {
            border = BorderFactory.createMatteBorder(1, 0, 1, 1, BLACK);
        } else {
            border = BorderFactory.createMatteBorder(0, 0, 1, 1, BLACK);
        }
        panel.setBorder(border);
        // return the panel we created
        return panel;
    }

    /**
     * getCellObject returns a cell object in row and column desired.
     * 
     * @param row
     *            - row we want to get a cell from in the world.
     * @param column
     *            - column we want to get a cell from in the world.
     * @return - The desired cell from the world.
     */
    public Cell getCellObject(int row, int column) {
        // check if we're in bounds
        if (row >= 0 && column >= 0 && row < x && column < y) {
            return worldGrid[row][column];
        }
        // return null if we're not.
        return null;
    }

    /**
     * generateWorld - generates the world full of random creatures and blanks.
     */
    public void generateWorld() {
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                int currentRand = RandomGenerator.nextNumber(RAND_MAX);

                Entity newEntity;

                if (currentRand >= PLANT_PERCENTAGE) {
                    newEntity = new Plant(worldGrid[i][j]);
                } else if (currentRand >= HERB_PERCENTAGE) {
                    newEntity = new Herbivore(worldGrid[i][j]);
                } else if (currentRand >= OMNI_PERCETANGE) {
                    newEntity = new Omnivore(worldGrid[i][j]);
                } else if (currentRand >= CARNI_PERCENTAGE) {
                    newEntity = new Carnivore(worldGrid[i][j]);
                } else {
                    // declare it as a blank spot on the grid
                    newEntity = new Entity(worldGrid[i][j], GridColour.WHITE);
                }
                worldGrid[i][j] = new Cell(this, i, j, newEntity);
                newEntity.setCell(worldGrid[i][j]);
            }
        }
    }

    /**
     * resetWorld - resets the world full of random creatures and blanks.
     */
    public void resetWorld() {
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                int currentRand = RandomGenerator.nextNumber(RAND_MAX);

                Entity newEntity;

                if (currentRand >= PLANT_PERCENTAGE) {
                    newEntity = new Plant(worldGrid[i][j]);
                } else if (currentRand >= HERB_PERCENTAGE) {
                    newEntity = new Herbivore(worldGrid[i][j]);
                } else if (currentRand >= OMNI_PERCETANGE) {
                    newEntity = new Omnivore(worldGrid[i][j]);
                } else if (currentRand >= CARNI_PERCENTAGE) {
                    newEntity = new Carnivore(worldGrid[i][j]);
                } else {
                    // declare it as a blank spot on the grid
                    newEntity = new Entity(worldGrid[i][j], GridColour.WHITE);
                }
                worldGrid[i][j].setCellEntity(newEntity);
                newEntity.setCell(worldGrid[i][j]);
            }
        }
        this.setDay(FIRST_DAY);
    }

    /**
     * loadWorld loads in a world from saved serialized file.
     * 
     * @param loadedWorld
     *            loadedWorld to replace current one
     */
    public void loadWorld(World loadedWorld) {
        // make sure new world exists
        if (loadedWorld == null) {
            return;
        }
        // make sure they're the same size
        int oWorldRow = this.getRowCount();
        int oWorldColumn = this.getColumnCount();
        int lWorldRow = loadedWorld.getRowCount();
        int lWorldColumn = loadedWorld.getColumnCount();
        if (oWorldRow == lWorldRow && oWorldColumn == lWorldColumn) {
            // go through and copy entities
            for (int i = 0; i < oWorldRow; i++) {
                for (int j = 0; j < oWorldColumn; j++) {
                    Cell currentCell = this.getCellObject(i, j);
                    Cell currentLoadedCell = loadedWorld.getCellObject(i, j);
                    // load in loaded object into cell
                    currentCell
                            .setCellEntity(currentLoadedCell.getCellEntity());
                    currentLoadedCell.getCellEntity().setCell(currentCell);
                }
            }
            // set the day to the loaded world's day
            this.setDay(loadedWorld.getDay());
        }
    }

    /**
     * takeTurn takes a turn in the world when executed, usage of Double Link
     * List from DoubleLL package.
     */
    public void takeTurn() {
        this.resetEntities();
        this.nextDay();
        DoubleLinkedList<Entity> entityList = this.getWorldEntities();
        // fail safe if the list empty.
        if (entityList == null) {
            return;
        }
        // act for all entities
        for (Entity current : entityList) {
            Entity currentEntity = current;
            currentEntity.getGridColour();
            currentEntity.act();
        }
    }

    /**
     * getWorldEntities returns a list of entities in the world. Usage of
     * DoubleLL package
     * 
     * @return returns the entities in the world in a list.
     */
    private DoubleLinkedList<Entity> getWorldEntities() {
        int row = this.getRowCount();
        int column = this.getColumnCount();

        DoubleLinkedList<Entity> entityList = new DoubleLinkedList<Entity>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Cell currentCell = this.getCellObject(i, j);
                // try to add all elements to the list one by one.
                try {
                    entityList.addToFront(currentCell.getCellEntity());
                } catch (CouldNotAddException e) {
                    e.printStackTrace();
                }
            }
        }
        return entityList;
    }

    /**
     * resetEntities clears all the acted statuses on entities.
     */
    private void resetEntities() {
        int row = this.getRowCount();
        int column = this.getColumnCount();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Cell currentCell = this.getCellObject(i, j);
                if (currentCell != null) {
                    Entity currentEntity = currentCell.getCellEntity();
                    currentEntity.resetActed();
                }
            }
        }
    }

    /**
     * nextDay increments to the next day in the World.
     */
    private void nextDay() {
        this.day = this.day + 1;
    }

    /**
     * getDay gets the current day.
     * 
     * @return day the day of the world.
     */
    public int getDay() {
        return this.day;
    }

    /**
     * setDay sets the day.
     * 
     * @param day
     *            - the new day to set to.
     */
    private void setDay(int day) {
        this.day = day;
    }
}