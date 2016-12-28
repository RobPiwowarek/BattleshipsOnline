package mvc;

public class GameController {
    GameModel gameModel;

    public void startGame(){
        gameModel.startGame();
    }

    public GameController(GameModel g) {
        gameModel = g;
    }
}
