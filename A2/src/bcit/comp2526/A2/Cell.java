package bcit.comp2526.A2;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Cell - Cell object for cells in World grid.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 */
public class Cell extends JPanel {
    private static final long serialVersionUID = 1L;
    private int row;
    private int column;
    private World theWorld;
    private Entity cellEntity;

    /**
     * Cell Constructor for making a Cell in the World Grid.
     * 
     * @param world
     *            - world the cell is part of
     * @param row
     *            - row the cell is on in World grid
     * @param column
     *            - column the cell is on in World grid
     * @param cellEntity
     *            - Entity of the cell.
     */
    public Cell(World world, int row, int column, Entity cellEntity) {
        this.setTheWorld(world);
        this.setRow(row);
        this.setColumn(column);
        this.setCellEntity(cellEntity);
    }

    /**
     * getPanel gets the panel of Cell.
     * 
     * @return returns this object as a JPanel component
     */
    public JPanel getPanel() {
        return this;
    }

    /**
     * getRow returns the row of the cell.
     * 
     * @return - row of the cell
     */
    public int getRow() {
        return this.row;
    }

    /**
     * setRow sets the new row of the cell.
     * 
     * @param row
     *            the new row.
     */
    private void setRow(int row) {
        this.row = row;
    }

    /**
     * getColumn gets the column of the cell.
     * 
     * @return column of the cell.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * setColumn set the column of the cell.
     * 
     * @param column
     *            new column of cell
     */
    private void setColumn(int column) {
        this.column = column;
    }

    /**
     * getTheWorld returns the world the cell is in.
     * 
     * @return returns the world the cell is in
     */
    protected World getTheWorld() {
        return theWorld;
    }

    /**
     * setTheWorld sets the world the cell is in.
     * 
     * @param theWorld
     *            - new world cell is in
     */
    private void setTheWorld(World theWorld) {
        this.theWorld = theWorld;
    }

    /**
     * getCellEntity returns the entity stored in the cell.
     * 
     * @return cellEntity stored in cell
     */
    public Entity getCellEntity() {
        return cellEntity;
    }

    /**
     * setCellEntity sets the entity in the cell.
     * 
     * @param cEntity
     *            - the new entity in the cell
     */
    public void setCellEntity(Entity cEntity) {
        // replace the old entity with new one.
        this.cellEntity = cEntity;
        // update the colour of the entity in the Cell
        Color color = Color.white;
        if (cEntity != null) {
            if (cEntity.getGridColour() == GridColour.GREEN) {
                color = Color.GREEN;
                this.cellEntity = (Plant) this.cellEntity;
            } else if (cellEntity.getGridColour() == GridColour.YELLOW) {
                color = Color.YELLOW;
                this.cellEntity = (Herbivore) this.cellEntity;
            } else if (cellEntity.getGridColour() == GridColour.BLUE) {
                color = Color.BLUE;
                this.cellEntity = (Omnivore) this.cellEntity;
            } else if (cellEntity.getGridColour() == GridColour.MAGENTA) {
                color = Color.MAGENTA;
                this.cellEntity = (Carnivore) this.cellEntity;
            }
        }
        this.getPanel().setBackground(color);
    }

    /**
     * getAdjcentCells gets the adj cells around the current cell.
     * 
     * @return the adj cells in array
     */
    protected Cell[] getAdjcentCells() {
        // get current position
        int curRow = this.getRow();
        int curColumn = this.getColumn();
        // get the world dimensions
        int x = theWorld.getRowCount();
        int y = theWorld.getColumnCount();

        // set boundaries for adj cells to check
        int yMin = curColumn - 1;
        int yMax = curColumn + 1;
        int xMin = curRow - 1;
        int xMax = curRow + 1;

        // make an array list for storing all the valid adj cells
        ArrayList<Cell> adjCells = new ArrayList<Cell>();

        // check surrounding blocks
        for (int i = xMin; i <= xMax; i++) {
            for (int j = yMin; j <= yMax; j++) {
                // condition for if we're on the cell that we're located at
                if (!(i == row && j == column)) {
                    // check if we're in bounds
                    if (checkBounds(i, j, x, y)) {
                        adjCells.add(theWorld.getCellObject(i, j));
                    }
                }
            }
        }
        // convert arrayList to array
        Cell[] arrayAdjCells = adjCells.toArray(new Cell[adjCells.size()]);
        return arrayAdjCells;
    }

    /**
     * CheckBounds checks the positions to make sure they're in the world bound.
     * 
     * @param cCheckPosX
     *            - row we're checking
     * @param cCheckPosY
     *            - column we're checking
     * @param xWorldBound
     *            - row bound of the world
     * @param yWorldBound
     *            - column bound of the world
     * @return - boolean if the coordinates are in bound or not
     */
    private boolean checkBounds(int cCheckPosX, int cCheckPosY, int xWorldBound,
            int yWorldBound) {
        // check if we're in bound for x and y axis
        if (cCheckPosX < 0 || cCheckPosX >= xWorldBound) {
            return false;
        }
        if (cCheckPosY < 0 || cCheckPosY >= yWorldBound) {
            return false;
        }
        return true;
    }

    /**
     * isEmtpy checks if the grid is empty or contains plant.
     * 
     * @return - if the grid is empty.
     */
    public boolean isEmpty() {
        // check if a grid space is empty
        if (cellEntity != null) {
            if (cellEntity.getGridColour() == GridColour.WHITE
                    || cellEntity.getGridColour() == GridColour.GREEN) {
                return true;
            }
        }
        return false;
    }
}
