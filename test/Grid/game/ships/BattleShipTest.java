package game.ships;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BattleShipTest {
    BattleShip ship;

    @Test
    public void givenBattleShipWhenTakingDamageThenHealthDecreases() throws Exception {
        ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        for (int i = 0; i < ship.getType().getLength(); ++i) {
            assertEquals(ship.getHealth(), ship.getType().getLength() - i);
            ship.takeDamage();
        }

        ship.takeDamage();
        assertEquals(ship.getHealth(), 0);
    }

    @Test
    public void givenBattleShipCreatedWithAngleWhenGettingAngleThenCorrectAngleIsReturned() throws Exception {
        ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        assertEquals(ship.getAngle(), ShipAngle.HORIZONTAL);

        ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.VERTICAL);

        assertEquals(ship.getAngle(), ShipAngle.VERTICAL);
    }

    @Test
    public void givenBattleShipCreatedWithTypeWhenGettingTypeThenCorrectTypeIsReturned() throws Exception {
        ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        assertEquals(ship.getType(), ShipType.BATTLESHIP);

        ship = new BattleShip(ShipType.CRUISER, ShipAngle.VERTICAL);

        assertEquals(ship.getType(), ShipType.CRUISER);
    }

    @Test
    public void givenBattleShipWithFullHealthWhenGettingHealthThenCorrectLengthIsReturned() throws Exception {
        ship = new BattleShip(ShipType.BATTLESHIP, ShipAngle.HORIZONTAL);

        assertEquals(ship.getHealth(), ShipType.BATTLESHIP.getLength());
    }

}