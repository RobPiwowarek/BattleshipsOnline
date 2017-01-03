package mvc;

import game.GameState;
import game.ships.ShipAngle;
import network.NetworkManager;

public class GameController {
    GameModel gameModel;
    NetworkManager networkManager;
    GameView gameView;

    public void createNetworkManager(int port, String ip, boolean isHost){
        networkManager = new NetworkManager(port, ip, isHost);
    }

    public GameController(GameModel g) {
        gameModel = g;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void startGame() {
        gameModel.startGame();
        gameView.getBoard().show();
    }

    public GameState getCurrentState() {
        return gameModel.getGameState();
    }

    public boolean isPlayerTurn() {
        return gameModel.isPlayerTurn();
    }

    public void addShip(int x, int y, ShipAngle angle, boolean isEnemy) {
        int length = gameModel.addShip(x, y, angle, isEnemy);

        if (length != 0){
            switch(angle){
                case HORIZONTAL:
                    for (int i = 0; i < length; ++i) {
                        gameView.showShip(x + i, y, isEnemy);
                    }
                    break;
                case VERTICAL:
                    for (int i = 0; i < length; ++i) {
                        gameView.showShip(x , y + i, isEnemy);
                    }
                    break;

            }
        }

    }
}
