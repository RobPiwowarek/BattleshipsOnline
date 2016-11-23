package game.graphics;

import game.graphics.board.GameBoard;
import game.graphics.ui.MainMenu;
import game.graphics.ui.NetworkManagerGUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class GraphicsManager implements MouseMotionListener {

    NetworkManagerGUI netGUI;
    MainMenu menu;
    GameBoard board;

    // TODO: some ways to represent different ships.
    // then a method to draw them, maybe some switch

    public void drawImage(Graphics g, Point position, Image img) {
       g.drawImage(img, position.x, position.y, null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
