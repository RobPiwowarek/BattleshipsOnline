package game;

import mvc.GameController;
import mvc.GameModel;
import mvc.GameView;

public class Game {
    private GameState gameState;

    private GameController gameController;
    private GameView gameView;
    private GameModel gameModel;

    private int score = 0;

    public Game() {
        gameModel = new GameModel(this);
        gameController = new GameController(this);
        gameView = new GameView(this);

        gameState = GameState.MENU;
    }

    public GameController getGameController() {
        return gameController;
    }

    public GameView getGameView() {
        return gameView;
    }

    public GameModel getGameModel() {
        return gameModel;
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

    public void startGame() {
        /* setup players, wait for connection etc
        */


        gameState = GameState.START;

        gameView.getBoard().show();
    }
}
