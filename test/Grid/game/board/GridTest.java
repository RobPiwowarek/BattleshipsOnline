package game.board;

import exception.IncorrectGridSizeException;
import game.ships.BattleShip;
import game.ships.ShipAngle;
import game.ships.ShipType;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {
    Grid grid;

    @Test
    public void addShip() throws Exception {
        grid = new Grid(10, 10);
        BattleShip ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        grid.addShip(ship, 1, 1, false);
        assertEquals(ship, grid.getElement(1,1).getShip());
    }

    @Test
    public void getElement() throws Exception{
        grid = new Grid(2, 2);

        for (int i = 0; i < grid.getY(); ++i){
            for (int j = 0; j < grid.getX(); ++j){
                assertNotNull(grid.getElement(0, 0));
            }
        }

    }

    @Test
    public void setElement() throws Exception {
        grid = new Grid(2, 2);
        GridElement element = new GridElement(null);
        grid.setElement(1, 1, element);

        assertEquals(grid.getElement(1, 1), element);
    }

    @Test
    public void getX() throws Exception {
        grid = new Grid(2, 5);

        assertEquals(2, grid.getX());
        assertEquals(5, grid.getY());
    }

    @Test
    public void getY() throws Exception {
        grid = new Grid(2, 5);

        assertEquals(5, grid.getY());
    }

}