package mvc;

import exception.GridOutOfBoundsException;
import exception.IncorrectGridSizeException;
import exception.IncorrectShipTypeException;
import game.GameState;
import game.board.Grid;
import game.ships.ShipAngle;
import game.ships.ShipType;

public class GameModel {
    private GameState gameState;
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

    int getLengthOfShipToAdd() {
        if (shipToAdd >= 2) {
            return shipToAdd;
        } else if (shipToAdd == 1) return 2;
        else
            return 0;
    }

    public Grid getGrid() {
        return grid;
    }

    GameState getGameState() {
        return gameState;
    }

    void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    int getScore() {
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

    int lowerEnemyScore() {
        return --enemyScore;
    }

    void setEnemyReady(boolean enemyReady) {
        this.enemyReady = enemyReady;
    }

    void attackEnemyTile() {
        gameState = GameState.WAITING;
    }

    // return true if ship was hit
    boolean attackMyTile(int x, int y) {
        if (grid.attackTile(x, y)) {
            --score;

            if (score == 0)
                gameState = GameState.END;

            return true;
        } else return false;
    }

    int addShip(int x, int y, ShipAngle angle, boolean isEnemy) throws GridOutOfBoundsException, IncorrectShipTypeException {
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
            }
            return shipType.getLength();
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

    boolean isLastShipBeingAdded() {
        return shipToAdd == 0;
    }

    void startGame() {
        gameState = GameState.START;

        playerTurn = true;
    }

}
