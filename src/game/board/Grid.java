package game.board;

import exception.AlreadyMarkedException;
import exception.GridOutOfBoundsException;
import exception.IncorrectGridSizeException;
import game.ships.BattleShip;
import game.ships.ShipAngle;
import game.ships.ShipType;
import mvc.GameView;

public class Grid {
    private int x;
    private int y;
    private GridElement[][] elements;
    private GameView gameView;

    public Grid(int x, int y) throws IncorrectGridSizeException {
        if (x < 0) throw new IncorrectGridSizeException("x < 0");

        if (y < 0) throw new IncorrectGridSizeException("y < 0");

        this.x = x;
        this.y = y;
        this.elements = new GridElement[y][x];
        initGrid();
    }

    public Grid(int size) throws IncorrectGridSizeException {
        if (size < 0) throw new IncorrectGridSizeException("given size < 0");

        this.x = size;
        this.y = size;

        elements = new GridElement[size][size];
        initGrid();
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

    public boolean addShip(BattleShip ship, int x, int y, boolean isEnemy) throws GridOutOfBoundsException {
        if (x > this.x || x < 0 || y > this.y || y < 0) {
            throw new GridOutOfBoundsException("Incorrect coordinates");
        }

        switch (ship.getAngle()) {
            case HORIZONTAL:
                if (isPlaceableHorizontally(x, y, ship.getType().getLength())) {

                    for (int i = 0; i < ship.getType().getLength(); ++i) {
                        elements[y][x + i].setShip(ship);
                    }
                } else return false;

                break;
            case VERTICAL:
                if (isPlaceableVertically(x, y, ship.getType().getLength())) {

                    for (int i = 0; i < ship.getType().getLength(); ++i) {
                        elements[y + i][x].setShip(ship);
                    }
                } else return false;

                break;
        }
        return true;
    }

    public boolean addShip(ShipType shipType, ShipAngle angle, int x, int y, boolean isEnemy) throws GridOutOfBoundsException {
        BattleShip ship = new BattleShip(shipType, angle);

        return addShip(ship, x, y, isEnemy);
    }

    private boolean isPlaceableHorizontally(int x, int y, int length) {
        if (x + length > this.x) return false;

        // checks 1 before
        if (x > 0) {
            if (elements[y][x - 1].getShip() != null) return false;
        }

        // check on 2 parallel lines
        for (int i = 0; i < length; ++i) {
            if (elements[y][x + i].getShip() != null) return false;

            if (y > 0) {
                if (elements[y - 1][x + i].getShip() != null) return false;
            }

            if (y + 1 < this.y) {
                if (elements[y + 1][x + i].getShip() != null) return false;
            }
        }

        // check 1 after
        if (x + length < this.x) {
            if (elements[y][x + length].getShip() != null) return false;
        }

        return true;
    }

    private boolean isPlaceableVertically(int x, int y, int length) {
        if (y + length > this.y) return false;

        // checks 1 before
        if (y > 0) {
            if (elements[y - 1][x].getShip() != null) return false;
        }

        // checks 2 parallel lines
        for (int i = 0; i < length; ++i) {

            if (elements[y + i][x].getShip() != null) return false;

            if (x > 0) {
                if (elements[y + i][x - 1].getShip() != null) return false;
            }

            if (x + 1 < this.x) {
                if (elements[y + i][x + 1].getShip() != null) return false;
            }
        }

        // checks 1 after
        if (y + length < this.y) {
            if (elements[y + length][x].getShip() != null) return false;
        }

        return true;
    }

    public boolean attackTile(int x, int y) {
        BattleShip ship = elements[y][x].getShip();

        if (ship != null) {
            ship.takeDamage();
            try {
                elements[y][x].mark();
            } catch (AlreadyMarkedException e) {
                System.err.println("Element " + "(" + x + "," + y + ")" + " is already marked");
                e.printStackTrace();
            }
            return true;
        } else return false;
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

    private void initGrid() {
        for (int i = 0; i < this.y; ++i) {
            for (int j = 0; j < this.x; ++j) {
                elements[i][j] = new GridElement();
            }
        }
    }

}
