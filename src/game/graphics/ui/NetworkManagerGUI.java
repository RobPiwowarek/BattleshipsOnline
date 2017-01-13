package game.graphics.ui;

import mvc.GameView;

import javax.swing.*;
import java.awt.*;

public class NetworkManagerGUI {
    private JFrame frame;
    private JTextField ipArea, portArea;
    private JCheckBox hostArea;
    private GameView gameView;
    private String ip;
    private int port;
    private boolean isServer;

    public NetworkManagerGUI(GameView view) {
        gameView = view;
        frame = new JFrame("Connection");
        frame.setSize(new Dimension(175, 125));

        frame.getContentPane().setLayout(new FlowLayout());
        ipArea = setupTextField("127.0.0.1");
        portArea = setupTextField("4444");
        setupPanel();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    private void setupPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        hostArea = setupCheckBox("Host", panel);
        setupConnectButton(panel);
        this.frame.add(panel);
    }

    private void setupConnectButton(JPanel panel) {
        JButton button = new JButton("Connect");

        button.addActionListener(e -> {
            System.out.println("Connect Button Pressed");

            isServer = hostArea.isSelected();
            ip = ipArea.getText();
            port = Integer.valueOf(portArea.getText());

            System.out.println("isServer: " + isServer + "\nIP: " + ip + "\nPort: " + port);
            gameView.getGameController().createNetworkManager(port, ip, isServer);
            frame.setVisible(false);
            gameView.getGameController().connect();
            frame.dispose();
        });

        panel.add(button);
    }

    private void setupLayoutManager(GridLayout manager) {
        manager.setVgap(5);

        this.frame.setLayout(manager);
    }

    private JTextField setupTextField(String text) {
        JTextField area = new JTextField(text);
        area.setPreferredSize(new Dimension(100, 20));

        this.frame.getContentPane().add(area);

        return area;
    }

    private JCheckBox setupCheckBox(String text, JPanel panel) {
        JCheckBox checkBox = new JCheckBox(text);

        panel.add(checkBox);

        return checkBox;
    }

    public void hide() {
        this.frame.setVisible(false);
    }

    void show() {
        this.frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
