package game.graphics.board;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JButton implements MouseListener {

    private ImageIcon currentIcon;

    public ImageIcon getCurrentIcon() {
        return currentIcon;
    }

    public void setCurrentIcon(ImageIcon currentIcon) {
        this.currentIcon = currentIcon;
    }

    public void displayCurrentIcon(){
        this.setIcon(currentIcon);
    }

    public void hideCurrentIcon(){
        this.setIcon(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
