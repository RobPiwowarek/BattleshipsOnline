package game.graphics.board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard {

    private JFrame boardFrame;
    private JButton[][] buttons;
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
        buttons = new JButton[height][width];

        for (int i = 0; i < height; ++i){
            for (int j = 0; j < width; ++j){

                final JButton field = new JButton();
                field.setPreferredSize(new Dimension(50, 50));
                field.setBackground(Color.lightGray);

                /*
                field.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (field.getIcon() == null){
                            ImageIcon icon = new ImageIcon("ship_element_placeholder.png");
                            field.setIcon(icon);
                        }
                        else {
                            field.setIcon(null);
                        }
                    }
                });
                */

                buttons[i][j] = field;
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
