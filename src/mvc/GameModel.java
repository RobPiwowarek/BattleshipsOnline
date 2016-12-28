package mvc;

import game.GameState;

public class GameModel {
    private GameState gameState;

    private GameController gameController;
    private GameView gameView;

    private int score = 0;

    public GameModel() {
        gameController = new GameController(this);
        gameView = new GameView(gameController);

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

    public void startGame() {
        /* setup players, wait for connection etc
        */


        gameState = GameState.START;

        gameView.getBoard().show();
    }
}
