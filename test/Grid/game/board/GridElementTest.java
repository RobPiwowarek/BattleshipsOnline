package game.board;

import game.ships.BattleShip;
import game.ships.ShipAngle;
import game.ships.ShipType;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridElementTest {
    private GridElement element;

    @Test
    public void givenBattleShipInGridElementWhenCreatedShipThenIsNotMarked() throws Exception {
        element = new GridElement();
        BattleShip ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        element.setShip(ship);

        assertFalse(element.isMarked());
    }

    @Test
    public void givenBattleShipInGridElementWhenMarkingThenShipIsMarked() throws Exception {
        element = new GridElement();
        BattleShip ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        element.setShip(ship);

        element.mark();

        assertTrue(element.isMarked());
    }

    @Test
    public void givenGridElementWithShipWhenGettingShipThenShipIsReturned() throws Exception {
        element = new GridElement();
        BattleShip ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        element.setShip(ship);

        assertEquals(ship, element.getShip());
    }

}