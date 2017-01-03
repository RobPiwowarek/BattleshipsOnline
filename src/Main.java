import exception.IncorrectGridSizeException;
import mvc.GameController;
import mvc.GameModel;
import mvc.GameView;

public class Main {

    public static void main(String[] args) throws IncorrectGridSizeException {
        GameModel gameModel = new GameModel();
        GameController gameController = new GameController(gameModel);
        GameView gameView = new GameView(gameController);

        gameModel.setGameController(gameController);
        gameController.setGameView(gameView);
    }
}
