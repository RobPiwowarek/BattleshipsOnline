package game.graphics.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameBoard {

    private JFrame boardFrame;
    private final int offset = 50;

    public GameBoard(int height, int width){
        boardFrame = new JFrame("Your Board");
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.addGridLayout();
        this.boardFrame.getContentPane().setLayout(new FlowLayout());


        setupPanels(height, width);

        boardFrame.pack();
        this.show();
    }

    private void addGridLayout(){
        boardFrame.getContentPane().setLayout(new GridLayout(1, 2));
    }

    private void setupPanels(int height, int width){
        JPanel myBoard = new JPanel(new GridLayout(height, width));
        JPanel opponentBoard = new JPanel(new GridLayout(height, width));

        boardFrame.getContentPane().add(myBoard);
        boardFrame.getContentPane().add(opponentBoard);

        setupBoardFields(myBoard, height, width);
        setupBoardFields(opponentBoard, height, width);
    }

    private void setupBoardFields(JPanel board, int height, int width){
        for (int i = 0; i < height; ++i){
            for (int j = 0; j < width; ++j){


                Button field = new Button();
                field.setPreferredSize(new Dimension(50, 50));
                field.setBackground(Color.lightGray);

                board.add(field);


            }
        }
    }

    public void show(){
        boardFrame.setVisible(true);
    }

    public void hide(){
        boardFrame.setVisible(false);
    }


}
