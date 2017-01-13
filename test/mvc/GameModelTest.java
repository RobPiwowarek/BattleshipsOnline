package mvc;

import game.GameState;
import game.ships.ShipAngle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameModelTest {
    GameModel model;

    @Test
    public void givenNewGridWithOneShipWhenTryingToAddShipOnAnotherShipThen0IsReturned() throws Exception {
        model = new GameModel();
        model.addShip(0, 0, ShipAngle.HORIZONTAL, false);
        assertEquals(model.addShip(0, 0, ShipAngle.HORIZONTAL, false), 0);
    }

    @Test
    public void givenGridWithOneShipOfLength5WhenAttackingTheShipThenShipGetsHit() throws Exception {
        model = new GameModel();
        model.addShip(0, 0, ShipAngle.HORIZONTAL, false);

        assertTrue(model.getGrid().attackTile(0, 0));
        assertTrue(model.getGrid().attackTile(1, 0));
        assertTrue(model.getGrid().attackTile(2, 0));
        assertTrue(model.getGrid().attackTile(3, 0));
        assertTrue(model.getGrid().attackTile(4, 0));
    }

    @Test
    public void givenGridWith5ShipsWhenAllAreDestroyedThenGameIsLost() throws Exception {
        model = new GameModel();
        model.addShip(0, 0, ShipAngle.HORIZONTAL, false);
        model.addShip(0, 2, ShipAngle.HORIZONTAL, false);
        model.addShip(0, 4, ShipAngle.HORIZONTAL, false);
        model.addShip(0, 6, ShipAngle.HORIZONTAL, false);
        model.addShip(0, 8, ShipAngle.HORIZONTAL, false);

        for (int i = 0; i < 5; ++i) {
            model.attackMyTile(i, 0);
        }

        for (int i = 0; i < 4; ++i) {
            model.attackMyTile(i, 2);
        }

        for (int i = 0; i < 3; ++i) {
            model.attackMyTile(i, 4);
        }

        for (int i = 0; i < 2; ++i) {
            model.attackMyTile(i, 6);
        }

        for (int i = 0; i < 2; ++i) {
            model.attackMyTile(i, 8);
        }

        assertEquals(model.getScore(), 0);
        assertEquals(model.getGameState(), GameState.END);
    }

    @Test
    public void givenNewGameModelWhenStartingGameThenGameStateIsStart() throws Exception {
        model = new GameModel();
        model.startGame();

        assertEquals(model.getGameState(), GameState.START);
    }

}