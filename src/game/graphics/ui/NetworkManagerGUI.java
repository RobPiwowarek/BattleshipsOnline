package game.graphics.ui;

import mvc.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NetworkManagerGUI {
    private JFrame frame;
    private GameView gameView;

    public NetworkManagerGUI(GameView view) {
        gameView = view;
        frame = new JFrame("Connection");
        frame.setSize(new Dimension(175, 125));

        frame.getContentPane().setLayout(new FlowLayout());
        setupTextField("IP");
        setupTextField("Port");
        setupPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    private void setupPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        setupCheckBox("Server", panel);
        setupConnectButton(panel);
        this.frame.add(panel);
    }

    private void setupConnectButton(JPanel panel) {
        JButton button = new JButton("Connect");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Connect Button Pressed");
            }
        });

        panel.add(button);
    }

    private void setupLayoutManager(GridLayout manager) {
        manager.setVgap(5);

        this.frame.setLayout(manager);
    }

    private void setupTextField(String text) {
        JTextField area = new JTextField(text);
        area.setPreferredSize(new Dimension(100, 20));

        this.frame.getContentPane().add(area);
    }

    private void setupCheckBox(String text, JPanel panel) {
        JCheckBox checkBox = new JCheckBox(text);

        panel.add(checkBox);
    }

    public void hide() {
        this.frame.setVisible(false);
    }

    public void show() {
        this.frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
