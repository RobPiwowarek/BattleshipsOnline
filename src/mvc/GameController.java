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

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public void createNetworkManager(int port, String ip, boolean isHost) {
        networkManager = new NetworkManager(port, ip, isHost, this);
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void startGame() {
        displayPopUpMessage("Connected.");
        gameModel.startGame();
        displayMessage("You can now place your battleships\nLeft click - vertically\nRight click - horizontally\nThe next ship you place will be of length " + gameModel.getLengthOfShipToAdd());
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
        if (!message.isDefeat()) {
            displayPopUpMessage("DEFEAT");
        }

        forceRestart();
    }

    public void displayPopUpMessage(String message) {
        gameView.getBoard().showPopUpMessage(message);
    }

    private void displayMessage(String message) {
        gameView.getBoard().showMessage(message);
    }

    public GameState getGameState() {
        return gameModel.getGameState();
    }

    private void tryToBeginMatch() {
        displayMessage("Opponent is ready");

        if (gameModel.getGameState() == GameState.WAITING) {
            gameModel.setGameState(GameState.MATCH);
        } else {
            gameModel.setEnemyReady(true);
        }
    }

    public void displayNetworkManagerMessage(String message) {
        gameView.getNetGUI().setMessage(message);
    }

    public void closeNetworkManagerGUI() {
        gameView.getNetGUI().dispose();
    }

    public void connect() {
        networkManager.connect();
    }

    public void forceRestart() {
        Main.restart();
    }

    private boolean sendMessage(Message message) {
        return networkManager.sendMessage(message);
    }

    private void attackMyTile(int x, int y) {
        if (gameModel.attackMyTile(x, y)) {
            sendMessage(Message.getHitMessage(x, y));
            gameView.getBoard().hitShip(x, y, false);
        } else {
            sendMessage(Message.getMissMessage(x, y));
            gameView.getBoard().hitTile(x, y, false);
            gameModel.setPlayerTurn(true);
            gameModel.setGameState(GameState.MATCH);
            displayMessage("Your turn");
        }
    }

    // no idea for better name+
    private void hitMessageHandler(Message message) {
        if (message.isHit()) {
            if (gameModel.lowerEnemyScore() == 0) {
                sendMessage(Message.getVictoryMessage());
                gameModel.setGameState(GameState.END);
                displayPopUpMessage("VICTORY");
                Main.restart();
            } else {
                displayMessage("You hit your opponent's ship. You can shoot again. ");
                gameModel.setGameState(GameState.MATCH);
                gameView.getBoard().hitShip(message.getX(), message.getY(), true);
            }
        } else {
            displayMessage("Opponent's turn.");
            gameView.getBoard().hitTile(message.getX(), message.getY(), true);
        }
    }

    public void attackEnemyTile(int x, int y) {
        if (gameView.isShown(x, y)) return;
        else {
            gameModel.attackEnemyTile();

            sendMessage(Message.getAttackMessage(x, y));

            gameView.getBoard().hitTile(x, y, true);
        }
    }

    public GameState getCurrentState() {
        return gameModel.getGameState();
    }

    public boolean isPlayerTurn() {
        return gameModel.isPlayerTurn();
    }

    public void addShip(int x, int y, ShipAngle angle, boolean isEnemy) {
        int length = gameModel.addShip(x, y, angle, isEnemy);

        if (gameModel.getLengthOfShipToAdd() >= 2)
            displayMessage("The next ship to place has length of " + gameModel.getLengthOfShipToAdd());
        else {
            displayMessage("You have placed all ships. ");
        }

        if (length > 0) {

            if (gameModel.isLastShipBeingAdded())
                sendMessage(Message.getReadyMessage());

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
