package mvc;

import exception.IncorrectGridSizeException;
import exception.IncorrectShipTypeException;
import game.GameState;
import game.board.Grid;
import game.ships.ShipAngle;
import game.ships.ShipType;
import network.Message;

public class GameModel {
    private GameState gameState;
    private GameController gameController;
    private Grid grid;
    private int shipToAdd = 5;
    private int score = 16;
    private int enemyScore = 16;
    private boolean playerTurn = false;
    private boolean enemyReady = false;

    public GameModel() throws IncorrectGridSizeException {
        grid = new Grid(GameView.BOARD_SIDE_LENGTH, GameView.BOARD_SIDE_LENGTH);

        gameState = GameState.MENU;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public GameModel getGameModel() {
        return this;
    }

    GameState getGameState() {
        return gameState;
    }

    void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    boolean isPlayerTurn() {
        return playerTurn;
    }

    void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    void lowerEnemyScore() {
        if (--enemyScore == 0) {
            gameController.sendMessage(Message.getVictoryMessage());
            gameState = GameState.END;

            gameController.displayMessage("VICTORY");
        }

    }

    void setEnemyReady(boolean enemyReady) {
        this.enemyReady = enemyReady;
    }

    void attackEnemyTile(int x, int y) {
        gameController.sendMessage(Message.getAttackMessage(x, y));
        gameState = GameState.WAITING;
    }

    // return true if ship was hit
    boolean attackTile(int x, int y) {
        if (grid.attackTile(x, y)) {
            --score;

            gameController.sendMessage(Message.getHitMessage(x, y));

            return true;
        } else return false;
    }

    int addShip(int x, int y, ShipAngle angle, boolean isEnemy) {
        try {
            ShipType shipType = chooseShip();
            boolean success = grid.addShip(shipType, angle, x, y, isEnemy);
            if (success) {
                if (--shipToAdd == 0) {
                    gameState = GameState.WAITING;

                    if (enemyReady) {
                        gameState = GameState.MATCH;
                        playerTurn = false;
                    } else
                        playerTurn = true;

                    gameController.sendMessage(Message.getReadyMessage());
                }

                return shipType.getLength();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private ShipType chooseShip() throws IncorrectShipTypeException {
        switch (shipToAdd) {
            case 5:
                return ShipType.CARRIER;
            case 4:
                return ShipType.BATTLESHIP;
            case 3:
                return ShipType.CRUISER;
            case 2:
            case 1:
                return ShipType.DESTROYER;
            default:
                throw new IncorrectShipTypeException("shipToAdd has different value than {5, 4, 3, 2, 1} ");
        }
    }

    void startGame() {
        gameState = GameState.START;

        gameController.displayMessage("You can now place your battleships\nLeft click - vertically\nRight click - horizontally");

        playerTurn = true;
    }

}
