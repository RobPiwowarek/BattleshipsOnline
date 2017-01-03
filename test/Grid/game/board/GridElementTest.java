package game.board;

import game.ships.BattleShip;
import game.ships.ShipAngle;
import game.ships.ShipType;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridElementTest {
    private GridElement element;

    @Test
    public void mark() throws Exception {
        element = new GridElement();
        BattleShip ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        element.setShip(ship);

        assertFalse(element.isMarked());

        element.mark();

        assertTrue(element.isMarked());
    }

    @Test
    public void getShip() throws Exception {
        element = new GridElement();
        BattleShip ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        element.setShip(ship);

        assertEquals(ship, element.getShip());
    }

}