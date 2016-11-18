package game.graphics.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    private final JFrame menuFrame;
    private static Dimension MENU_FRAME_OUTER_DIMENSION = new Dimension(600, 600);

    public MainMenu(){
        this.menuFrame = new JFrame("Main menu");
        menuFrame.setSize(this.MENU_FRAME_OUTER_DIMENSION.width, this.MENU_FRAME_OUTER_DIMENSION.height);
        final JMenuBar menuBar = new JMenuBar();
        this.setupMenuBar(menuBar);
        this.menuFrame.setJMenuBar(menuBar);
        this.menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    }


    private void setupMenuBar(final JMenuBar menuBar){
        menuBar.add(createFileMenu());
    }

    private JMenu createFileMenu(){
        final JMenu fileMenu = new JMenu("File");

        final JMenuItem start = new JMenuItem("Start");

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start pressed");

            }
        });


        fileMenu.add(start);

        return fileMenu;
    }
}
