package game.graphics.ui;

import mvc.GameView;

import javax.swing.*;
import java.awt.*;

public class MainMenu {

    private final JFrame menuFrame;
    private GameView gameView;

    public MainMenu(GameView view) {
        gameView = view;

        menuFrame = new JFrame("Main menu");

        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupLayoutManager();

        menuFrame.pack();

        menuFrame.setResizable(false);
    }

    private void setupLayoutManager() {
        FlowLayout layout = new FlowLayout();
        layout.setVgap(10);
        layout.setHgap(10);

        menuFrame.getContentPane().setLayout(layout);

        menuFrame.getContentPane().add(setupLogoPanel());
    }

    public void show() {
        menuFrame.setVisible(true);
    }

    public void hide() {
        menuFrame.setVisible(false);
    }

    private JPanel setupLogoPanel() {
        GridLayout layout = new GridLayout(2, 1);

        JPanel panel = new JPanel(layout);

        setupLogo(panel);

        setupBorders(panel);

        return panel;
    }

    private void setupBorders(JPanel panel) {
        FlowLayout layout = new FlowLayout();

        JPanel flowPanel = new JPanel(layout);

        flowPanel.add(setupButtonPanel());

        panel.add(flowPanel);
    }

    private JPanel setupButtonPanel() {
        GridLayout layout = new GridLayout(3, 1);

        layout.setVgap(10);

        JPanel panel = new JPanel(layout);

        setupButtons(panel);

        return panel;
    }

    private void setupButtons(JPanel panel) {
        JButton startButton = new JButton("Start");
        JButton authorButton = new JButton("Author");
        JButton exitButton = new JButton("Exit");

        startButton.setPreferredSize(new Dimension(500, 50));

        setupStartButton(startButton);

        panel.add(startButton);
        panel.add(authorButton);
        panel.add(exitButton);
    }

    private void setupLogo(JPanel panel) {
        panel.add(new JLabel(new ImageIcon(new ImageIcon("src/resources/images/logo.png").getImage().getScaledInstance(800, 120, Image.SCALE_SMOOTH))));
    }

    private void setupStartButton(JButton start) {
        start.addActionListener(e -> {
            System.out.println("Start FileMenu Item pressed");

            gameView.getNetGUI().show();
            menuFrame.setVisible(false);
        });
    }

}
