package game.graphics.ui;

import mvc.GameView;

import javax.swing.*;
import java.awt.*;

public class NetworkManagerGUI {
    private JFrame frame;
    private JTextField ipArea, portArea;
    private JTextArea textArea;
    private JCheckBox hostArea;
    private GameView gameView;
    private String ip;
    private int port;
    private boolean isServer;

    public NetworkManagerGUI(GameView view) {
        gameView = view;
        frame = new JFrame("Connection");
        frame.setSize(new Dimension(175, 135));

        frame.getContentPane().setLayout(new FlowLayout());
        ipArea = setupTextField("127.0.0.1");
        portArea = setupTextField("4444");
        setupPanel();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    private void setupPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));

        textArea = new JTextArea("");

        hostArea = setupCheckBox("Host", panel);
        setupConnectButton(panel);
        boxPanel.add(panel);
        boxPanel.add(textArea);
        frame.getContentPane().add(boxPanel);
    }

    private void setupConnectButton(JPanel panel) {
        JButton button = new JButton("Connect");

        button.addActionListener(e -> {
            System.out.println("Connect Button Pressed");

            button.setEnabled(false);

            textArea.setText("Connecting...");

            isServer = hostArea.isSelected();
            ip = ipArea.getText();
            port = Integer.valueOf(portArea.getText());

            System.out.println("isServer: " + isServer + "\nIP: " + ip + "\nPort: " + port);
            gameView.getGameController().createNetworkManager(port, ip, isServer);
            gameView.getGameController().connect();
        });

        panel.add(button);
    }

    public void setMessage(String message) {
        textArea.setText(message);
    }

    public void dispose() {
        frame.setVisible(false);
        frame.dispose();
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
