package mvc;

import exception.IncorrectShipTypeException;
import game.GameState;
import game.board.Grid;
import game.ships.ShipAngle;
import game.ships.ShipType;

public class GameModel {
    private GameState gameState;
    private GameController gameController;
    private Grid grid;
    private int shipToAdd = 5;
    private int score = 0;
    private boolean playerTurn = false;

    // TODO: gameView and Controller should be initialised outside GameModel
    public GameModel() {
        grid = new Grid(GameView.BOARD_SIDE_LENGTH, GameView.BOARD_SIDE_LENGTH);

        gameState = GameState.MENU;
    }

    public GameController getGameController() {
        return gameController;
    }

    public GameModel getGameModel() {
        return this;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean changeTurn() {
        playerTurn = !playerTurn;
        return playerTurn;
    }

    public int addShip(int x, int y, ShipAngle angle, boolean isEnemy) {
        try {
            ShipType shipType = chooseShip();
            boolean success = grid.addShip(shipType, angle, x, y, isEnemy);
            if (success) {
                --shipToAdd;
                if(shipToAdd == 0) gameState = GameState.WAITING;
                // TODO: powiadom drugiego gracza ze pierwszy jest gotowy

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

    public void startGame() {
        /* setup players, wait for connection etc
        */


        gameState = GameState.START;

        playerTurn = true;
    }

}
