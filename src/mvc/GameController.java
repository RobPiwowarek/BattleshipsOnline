package mvc;

import game.GameState;
import game.Main;
import game.ships.ShipAngle;
import network.Message;
import network.NetworkManager;

public class GameController {
    private GameModel gameModel;
    private NetworkManager networkManager;
    private GameView gameView;

    public GameController(GameModel g) {
        gameModel = g;
    }

    public void createNetworkManager(int port, String ip, boolean isHost) {
        networkManager = new NetworkManager(port, ip, isHost, this);
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void startGame() {
        displayMessage("Connected.");
        gameModel.startGame();
        gameView.getBoard().show();
    }

    public void handleMessage(Message message) {
        switch (message.getType()) {
            case GAME_END:
                endGame(message);
                break;
            case ATTACK:
                attackMyTile(message.getX(), message.getY());
                break;
            case HIT:
                hitMessageHandler(message);
                break;
            case READY:
                tryToBeginMatch();
                break;
            case TEXT:
                break;
        }
    }

    private void endGame(Message message) {
        if (message.isDefeat()) {
            displayMessage("VICTORY");
        } else {
            displayMessage("DEFEAT");
        }

        Main.restart();
    }

    public void displayMessage(String message) {
        gameView.getBoard().showMessage(message);
    }

    public GameState getGameState() {
        return gameModel.getGameState();
    }

    private void tryToBeginMatch() {
        if (gameModel.getGameState() == GameState.WAITING) {
            gameModel.setGameState(GameState.MATCH);
        } else {
            gameModel.setEnemyReady(true);
        }
    }

    public void connect() {
        displayMessage("Connecting...");
        networkManager.connect();
    }

    public void forceRestart() {
        Main.restart();
    }

    boolean sendMessage(Message message) {
        return networkManager.sendMessage(message);
    }

    private void attackMyTile(int x, int y) {
        if (gameModel.attackTile(x, y)) {
            gameView.getBoard().hitShip(x, y, false);
        } else {
            gameView.getBoard().hitTile(x, y, false);
            gameModel.setPlayerTurn(true);
            gameModel.setGameState(GameState.MATCH);
            displayMessage("Your turn");
        }
    }

    // no idea for better name+
    private void hitMessageHandler(Message message) {
        if (message.isHit()) {
            gameModel.lowerEnemyScore();
            gameModel.setGameState(GameState.MATCH);
            gameView.getBoard().hitShip(message.getX(), message.getY(), true);
        } else {
            gameView.getBoard().hitTile(message.getX(), message.getY(), true);
        }
    }

    public void attackEnemyTile(int x, int y) {
        gameModel.attackEnemyTile(x, y);
        gameView.getBoard().hitTile(x, y, true);
    }

    public GameState getCurrentState() {
        return gameModel.getGameState();
    }

    public boolean isPlayerTurn() {
        return gameModel.isPlayerTurn();
    }

    public void addShip(int x, int y, ShipAngle angle, boolean isEnemy) {
        int length = gameModel.addShip(x, y, angle, isEnemy);

        if (length != 0) {
            switch (angle) {
                case HORIZONTAL:
                    for (int i = 0; i < length; ++i) {
                        gameView.showShip(x + i, y, isEnemy);
                    }
                    break;
                case VERTICAL:
                    for (int i = 0; i < length; ++i) {
                        gameView.showShip(x, y + i, isEnemy);
                    }
                    break;

            }
        }

    }
}
