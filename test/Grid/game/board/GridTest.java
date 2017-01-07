package game.board;

import game.ships.BattleShip;
import game.ships.ShipAngle;
import game.ships.ShipType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GridTest {
    Grid grid;

    @Test
    public void givenGridWhenAddingShipThenShipIsAdded() throws Exception {
        grid = new Grid(10, 10);
        BattleShip ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        grid.addShip(ship, 1, 1, false);
        assertEquals(ship, grid.getElement(1, 1).getShip());
    }


    @Test
    public void givenEmptyGridWhenInitialisingThenGridElementsAreNotNull() throws Exception {
        grid = new Grid(2, 2);

        for (int i = 0; i < grid.getY(); ++i) {
            for (int j = 0; j < grid.getX(); ++j) {
                assertNotNull(grid.getElement(0, 0));
            }
        }

    }

    @Test
    public void givenNewGridWhenSettingElementThenElementIsSet() throws Exception {
        grid = new Grid(2, 2);
        GridElement element = new GridElement(null);
        grid.setElement(1, 1, element);

        assertEquals(grid.getElement(1, 1), element);
    }

    @Test
    public void givenGridWhenGettingXThenXIsReturned() throws Exception {
        grid = new Grid(2, 5);

        assertEquals(2, grid.getX());
    }

    @Test
    public void givenGridWhenGettingYThenYIsReturned() throws Exception {
        grid = new Grid(2, 5);

        assertEquals(5, grid.getY());
    }

}