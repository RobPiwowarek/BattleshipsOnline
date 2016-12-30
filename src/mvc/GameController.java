package mvc;

import game.GameState;
import game.ships.ShipAngle;

public class GameController {
    GameModel gameModel;

    public void startGame() {
        gameModel.startGame();
    }

    public GameState getCurrentState(){
        return gameModel.getGameState();
    }

    public boolean isPlayerTurn(){
        return gameModel.isPlayerTurn();
    }

    public void addShip(int x, int y, ShipAngle angle, boolean isEnemy){
        gameModel.addShip(x, y, angle, isEnemy);
    }

    public GameController(GameModel g) {
        gameModel = g;
    }
}
