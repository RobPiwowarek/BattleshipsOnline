package game;

public class Game {
    private GameState gameState;


    Game(){
        gameState = GameState.MENU;
    }

    void setGameState(GameState state){
        gameState = state;
    }

    public GameState getGameState() {
        return gameState;
    }
}
