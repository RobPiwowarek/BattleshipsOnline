package game.graphics.board;

import game.GameState;
import game.ships.ShipAngle;
import mvc.GameView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Tile extends JButton implements MouseListener {

    int x, y;

    private ImageIcon currentIcon;

    private GameView gameView;

    Tile(GameView view, int x, int y) {
        super();

        this.x = x;
        this.y = y;

        gameView = view;
    }

    public ImageIcon getCurrentIcon() {
        return currentIcon;
    }

    public void setCurrentIcon(ImageIcon currentIcon) {
        this.currentIcon = currentIcon;
    }

    public void displayCurrentIcon() {
        this.setIcon(currentIcon);
    }

    public void hideCurrentIcon() {
        this.setIcon(null);
    }

    // TODO: block if not your turn or something.
    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameView.getGameController().isPlayerTurn()) {
            switch (gameView.getGameController().getCurrentState()) {
                case START:
                    if (SwingUtilities.isRightMouseButton(e)) {
                        gameView.getGameController().addShip(x, y, ShipAngle.HORIZONTAL);
                    } else {
                        gameView.getGameController().addShip(x, y, ShipAngle.VERTICAL);
                    }
                    break;

                case MATCH: {
                    // TODO:

                    break;
                }
                default:
            }
        }
}

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
