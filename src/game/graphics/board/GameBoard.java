package game.graphics.board;

import mvc.GameView;

import javax.swing.*;
import java.awt.*;

public class GameBoard {

    private JFrame boardFrame;
    private Tile[][] myTiles;
    private Tile[][] enemyTiles;
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

        setupBoardFields(myBoard, height, width, false);
        setupBoardFields(opponentBoard, height, width, true);
    }

    private void setupBoardFields(JPanel board, int height, int width, boolean isEnemy) {
        if (isEnemy)
            enemyTiles = new Tile[height][width];
        else
            myTiles = new Tile[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {

                Tile field = new Tile(gameView, j, i);
                field.setPreferredSize(new Dimension(50, 50));
                field.setBackground(Color.lightGray);

                if (isEnemy)
                    enemyTiles[i][j] = field;
                else
                    myTiles[i][j] = field;

                board.add(field);
            }
        }
    }

    public void displayShip(int x, int y, boolean isEnemy) {
        if (isEnemy)
            enemyTiles[y][x].showShip();
        else
            myTiles[y][x].showShip();
    }

    public void hideShip(int x, int y, boolean isEnemy) {
        if (isEnemy)
            enemyTiles[y][x].hideShip();
        else
            myTiles[y][x].hideShip();
    }

    public void damageShip(int x, int y, boolean isEnemy) {
        if (isEnemy)
            enemyTiles[y][x].destroyShip();
        else
            myTiles[y][x].destroyShip();
    }


    public void show() {
        boardFrame.setVisible(true);
    }

    public void hide() {
        boardFrame.setVisible(false);
    }

}
