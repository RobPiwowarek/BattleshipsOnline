import game.graphics.ui.GameBoard;
import game.graphics.ui.MainMenu;
import network.NetworkManager;

public class Main {
    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        NetworkManager manager = new NetworkManager();
        GameBoard board = new GameBoard(8, 8);
    }
}
