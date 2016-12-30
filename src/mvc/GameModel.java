package mvc;

import exception.GridOutOfBoundsException;
import exception.IncorrectShipTypeException;
import game.GameState;
import game.board.Grid;
import game.ships.ShipAngle;
import game.ships.ShipType;

public class GameModel {
    private GameState gameState;
    private GameController gameController;
    private GameView gameView;
    private Grid grid;
    private int shipToAdd = 5;
    private int score = 0;
    private boolean playerTurn = false;

    // TODO: gameView and Controller should be initialised outside GameModel
    public GameModel() {
        gameController = new GameController(this);
        gameView = new GameView(gameController);

        grid = new Grid(GameView.BOARD_SIDE_LENGTH, GameView.BOARD_SIDE_LENGTH, gameView);

        gameState = GameState.MENU;
    }

    public GameController getGameController() {
        return gameController;
    }

    public GameView getGameView() {
        return gameView;
    }

    public GameModel getGameModel() {
        return this;
    }

    public GameState getGameState() {
        return gameState;
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

    public boolean changeTurn(){
        playerTurn = !playerTurn;
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean addShip(int x, int y, ShipAngle angle, boolean isEnemy){
        try {
            boolean success = grid.addShip(chooseShip(), angle, x, y, isEnemy);
            if (success) --shipToAdd;

            return success;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    private ShipType chooseShip() throws IncorrectShipTypeException {
        switch(shipToAdd){
            case 5: return ShipType.CARRIER;
            case 4: return ShipType.BATTLESHIP;
            case 3: return ShipType.CRUISER;
            case 2:
            case 1: return ShipType.DESTROYER;
            default: throw new IncorrectShipTypeException("shipToAdd has different value than {5, 4, 3, 2, 1} ");
        }
    }

    public void startGame() {
        /* setup players, wait for connection etc
        */


        gameState = GameState.START;

        playerTurn = true;

        gameView.getBoard().show();
    }

}
