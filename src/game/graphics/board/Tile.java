package game.graphics.board;

import game.ships.ShipAngle;
import mvc.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Tile extends JButton implements MouseListener {

    private int x, y;
    private boolean isEnemyTile;
    private boolean isDisplayed;
    private ImageIcon currentIcon;

    private GameView gameView;

    Tile(GameView view, int x, int y, boolean isEnemyTile) {
        super();

        addMouseListener(this);

        this.x = x;
        this.y = y;

        this.isEnemyTile = isEnemyTile;

        gameView = view;
    }

    boolean isDisplayed() {
        return isDisplayed;
    }

    void showShip() {
        this.setBackground(Color.black);
        isDisplayed = true;
    }

    void hideShip() {
        this.setBackground(null);
    }

    void hitTile() {
        this.setBackground(Color.white);
        isDisplayed = true;
    }

    void hitShip() {
        this.setBackground(Color.red);
        isDisplayed = true;
    }

    // Alternatively can be done with icons

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

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("CLICKED");

        if (gameView.getGameController().isPlayerTurn()) {
            switch (gameView.getGameController().getCurrentState()) {
                case START:
                    if (!isEnemyTile)
                        addShip(e);
                    break;
                case MATCH: {
                    if (isEnemyTile) {
                        gameView.getGameController().attackEnemyTile(x, y);
                    }

                    break;
                }
            }
        }
    }

    private void addShip(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            gameView.getGameController().addShip(x, y, ShipAngle.HORIZONTAL, false);
        } else {
            gameView.getGameController().addShip(x, y, ShipAngle.VERTICAL, false);
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