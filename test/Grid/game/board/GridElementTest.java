package game.board;

import exception.AlreadyMarkedException;
import game.ships.BattleShip;
import game.ships.ShipAngle;
import game.ships.ShipType;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridElementTest {
    private GridElement element;

    @Test(expected = AlreadyMarkedException.class)
    public void givenMarkedBattleShipInGridElementWhenMarkingThenExceptionIsThrown() throws AlreadyMarkedException {
        element = new GridElement();
        BattleShip ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        element.setShip(ship);

        element.mark();
        element.mark();
    }

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