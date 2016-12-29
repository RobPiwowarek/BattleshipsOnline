package game.graphics.board;

import mvc.GameView;

import javax.swing.*;
import java.awt.*;

public class GameBoard {

    private JFrame boardFrame;
    private Tile[][] tiles;
    private GameView gameView;

    public GameBoard(int height, int width, GameView view) {
        gameView = view;

        boardFrame = new JFrame("Your Board");
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupLayout();

        setupPanels(height, width);

        boardFrame.pack();
        boardFrame.setResizable(false);
    }

    private void setupLayout() {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 20, 20);

        this.boardFrame.getContentPane().setLayout(layout);
    }

    private void setupPanels(int height, int width) {
        JPanel myBoard = new JPanel(new GridLayout(height, width));
        JPanel opponentBoard = new JPanel(new GridLayout(height, width));

        boardFrame.getContentPane().add(myBoard);
        boardFrame.getContentPane().add(opponentBoard);

        setupBoardFields(myBoard, height, width);
        setupBoardFields(opponentBoard, height, width);
    }

    private void setupBoardFields(JPanel board, int height, int width) {
        tiles = new Tile[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {

                Tile field = new Tile(gameView, i, j);
                field.setPreferredSize(new Dimension(50, 50));
                field.setBackground(Color.lightGray);

                tiles[i][j] = field;
                board.add(field);
            }
        }
    }

    public void show() {
        boardFrame.setVisible(true);
    }

    public void hide() {
        boardFrame.setVisible(false);
    }

}
