package mvc;

import game.Game;
import game.graphics.board.GameBoard;
import game.graphics.ui.MainMenu;
import game.graphics.ui.NetworkManagerGUI;

public class GameView {
    public final static int BOARD_SIDE_LENGTH = 8;
    NetworkManagerGUI netGUI;
    private GameBoard board;
    MainMenu menu;
    Game game;

    // TODO: need to change default exit operation for UI ELEMENTS
    // so that they only hide and don't spawn additional windows.

    public GameView(Game g) {
        board = new GameBoard(BOARD_SIDE_LENGTH, BOARD_SIDE_LENGTH);
        netGUI = new NetworkManagerGUI(this);
        menu = new MainMenu(this);
        game = g;

        menu.show();
    }

    public Game getGame() {
        return game;
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
