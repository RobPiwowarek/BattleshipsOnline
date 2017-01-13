package mvc;

import game.graphics.board.GameBoard;
import game.graphics.ui.MainMenu;
import game.graphics.ui.NetworkManagerGUI;

public class GameView {
    final static int BOARD_SIDE_LENGTH = 10;
    private NetworkManagerGUI netGUI;
    private MainMenu menu;
    private GameController gameController;
    private GameBoard board;

    public GameView(GameController g) {
        board = new GameBoard(BOARD_SIDE_LENGTH, BOARD_SIDE_LENGTH, this);
        netGUI = new NetworkManagerGUI(this);
        menu = new MainMenu(this);
        gameController = g;

        menu.show();
    }

    public void damageShip(int x, int y, boolean isEnemy) {
        board.hitTile(x, y, isEnemy);
    }

    void showShip(int x, int y, boolean isEnemy) {
        board.displayShip(x, y, isEnemy);
    }

    boolean isShown(int x, int y) {
        return board.isDisplayed(x, y);
    }

    public void hideShip(int x, int y, boolean isEnemy) {
        board.hideShip(x, y, isEnemy);
    }

    public GameController getGameController() {
        return gameController;
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    public NetworkManagerGUI getNetGUI() {
        return netGUI;
    }

    public void setNetGUI(NetworkManagerGUI netGUI) {
        this.netGUI = netGUI;
    }

    public MainMenu getMenu() {
        return menu;
    }

    public void setMenu(MainMenu menu) {
        this.menu = menu;
    }
}
