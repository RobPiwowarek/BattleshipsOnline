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

        gameModel.setGameController(gameController);
        gameController.setGameView(gameView);
    }

    public static void restart() throws IncorrectGridSizeException {
        gameView.getBoard().dispose();
        gameView.getMenu().dispose();

        gameModel = new GameModel();
        gameController = new GameController(gameModel);
        gameView = new GameView(gameController);

        gameModel.setGameController(gameController);
        gameController.setGameView(gameView);
    }
}
