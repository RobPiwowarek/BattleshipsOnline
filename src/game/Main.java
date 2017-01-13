package game;

import exception.IncorrectGridSizeException;
import mvc.GameController;
import mvc.GameModel;
import mvc.GameView;

public class Main {
    private static GameModel gameModel;
    private static GameController gameController;
    private static GameView gameView;

    public static void main(String[] args) throws IncorrectGridSizeException {
        gameModel = new GameModel();
        gameController = new GameController(gameModel);
        gameView = new GameView(gameController);

        gameController.setGameView(gameView);
    }

    public static void restart() {
        gameView.getBoard().hide();
        gameView.getMenu().hide();
        gameView.getBoard().dispose();
        gameView.getMenu().dispose();

        if (gameController.getNetworkManager().isConnected())
            gameController.getNetworkManager().closeSocket();

        try {
            gameModel = new GameModel();
        } catch (IncorrectGridSizeException e) {
            e.printStackTrace();
            System.exit(5);
        }
        gameController = new GameController(gameModel);
        gameView = new GameView(gameController);

        gameController.setGameView(gameView);
    }
}
