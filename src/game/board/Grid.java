package game.board;

import exception.GridOutOfBoundsException;
import exception.IncorrectGridSizeException;
import game.ships.ShipAngle;
import game.ships.ShipType;

public class Grid {
    private int x;
    private int y;
    private GridElement[][] elements;

    public Grid() {
        elements = new GridElement[this.x][this.y];
    }

    public Grid(int x, int y) {
        this.x = x;
        this.y = y;
        this.elements = new GridElement[x][y];
    }

    public Grid(int size) throws IncorrectGridSizeException {
        this.x = size;
        this.y = size;

        if (size < 0) throw new IncorrectGridSizeException("given size < 0");

        elements = new GridElement[size][size];
    }

    public Grid(GridElement[][] e) throws IncorrectGridSizeException {
        this.elements = e;

        // check for GridElement[0][X]
        if (e.length == 0 || e[0].length == 0) throw new IncorrectGridSizeException("given GridElement[][] size == 0");

        this.x = e[0].length;
        this.y = e.length;
    }

    public Grid(Grid g) {
        this.x = g.x;
        this.y = g.y;
        this.elements = g.elements;
    }

    public boolean addShip(ShipType shipType, ShipAngle angle, int x, int y) throws GridOutOfBoundsException {
        if (x > this.x || x < 0 || y > this.y || y < 0){
            throw new GridOutOfBoundsException("Incorrect coordinates");
        }

        // TODO: (can place horizontally/vertically) then place then update visually
        switch(angle){
            case HORIZONTAL: break;
            case VERTICAL: break;
        }

        return true;
    }

    public GridElement getElement(int x, int y) {
        return elements[x][y];
    }

    public void setElement(int x, int y, GridElement g) {
        this.elements[x][y] = g;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


}
