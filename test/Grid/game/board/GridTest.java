package game.board;

import exception.GridOutOfBoundsException;
import exception.IncorrectGridSizeException;
import game.ships.BattleShip;
import game.ships.ShipAngle;
import game.ships.ShipType;
import mvc.GameModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {
    Grid grid;

    @Test(expected = GridOutOfBoundsException.class)
    public void givenNewGridWhenAddingShipToIncorrectLocationThenExceptionIsThrown() throws Exception {
        GameModel model = new GameModel();
        grid = model.getGrid();

        BattleShip ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        grid.addShip(ship, -1, -1, false);
    }

    @Test(expected = IncorrectGridSizeException.class)
    public void whenCreatingGridWithIncorrectSizeThenExceptionIsThrown() throws Exception {
        grid = new Grid(-1, -1);
    }

    @Test
    public void givenGridWhenAddingShipThenShipIsAdded() throws Exception {
        grid = new Grid(10, 10);
        BattleShip ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        grid.addShip(ship, 1, 1, false);
        assertEquals(ship, grid.getElement(1, 1).getShip());
    }

    @Test
    public void givenNewGridWithOneShipWhenAttackingShipThenTrueIsReturned() throws Exception {
        GameModel model = new GameModel();
        BattleShip ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        model.getGrid().addShip(ship, 1, 1, false);

        assertTrue(model.getGrid().attackTile(1, 1));
    }

    @Test
    public void givenNewGridWhenNotAttackingShipThenFalseIsReturned() throws Exception {
        GameModel model = new GameModel();

        model.getGrid().attackTile(1, 1);

        assertFalse(model.getGrid().attackTile(1, 1));
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

    @Test
    public void givenGridWithOneShipWhenPlacingSecondShipOnFirstShipThenFalseIsReturned() throws Exception {
        GameModel model = new GameModel();
        model.getGrid().addShip(ShipType.BATTLESHIP, ShipAngle.VERTICAL, 0, 0, false);
        assertFalse(model.getGrid().addShip(ShipType.BATTLESHIP, ShipAngle.VERTICAL, 0, 0, false));
    }
}