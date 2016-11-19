package game.graphics.ui;

import javax.swing.*;
import java.awt.*;

public class GameBoard {

    private JFrame boardFrame;
    private final int offset = 50;

    public GameBoard(int height, int width){
        boardFrame = new JFrame("Your Board");
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupLayout();


        setupPanels(height, width);

        boardFrame.pack();
        boardFrame.setResizable(false);
        this.show();
    }

    private void setupLayout() {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 20, 20);

        this.boardFrame.getContentPane().setLayout(layout);
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


                JButton field = new JButton();
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
