package mvc;

import game.ships.ShipAngle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameModelTest {
    @Test
    public void givenNewGameWhenAllShipsAreMarkedThenGameIsLost() throws Exception {
        GameModel model = new GameModel();

        model.addShip(0, 0, ShipAngle.HORIZONTAL, false);

        model.addShip(0, 2, ShipAngle.HORIZONTAL, false);

        model.addShip(0, 4, ShipAngle.HORIZONTAL, false);

        model.addShip(0, 6, ShipAngle.HORIZONTAL, false);

        model.addShip(0, 8, ShipAngle.HORIZONTAL, false);

        for (int i = 0; i < 5; ++i) {
            model.attackTile(i, 0);
        }

        for (int i = 0; i < 4; ++i) {
            model.attackTile(i, 2);
        }

        for (int i = 0; i < 3; ++i) {
            model.attackTile(i, 4);
        }

        for (int i = 0; i < 2; ++i) {
            model.attackTile(i, 6);
        }

        for (int i = 0; i < 2; ++i) {
            model.attackTile(i, 8);
        }

        assertEquals(model.getScore(), 0);
    }

}