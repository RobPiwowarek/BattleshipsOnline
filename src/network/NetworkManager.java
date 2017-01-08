package network;

import game.GameState;
import mvc.GameController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkManager {
    private GameController gameController;
    private boolean isConnected = false;
    private int port;
    private String IP;
    private boolean isServer;
    private MessageSender messageSender;

    public NetworkManager(int port, String IP, boolean isServer, GameController controller) {
        this.port = port;
        this.IP = IP;
        this.isServer = isServer;
        this.gameController = controller;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public boolean connect() {
        new GameServer().start();

        isConnected = true;
        return true;
    }

    public boolean sendMessage(Message message) {
        try {
            if (messageSender == null) System.out.println("DUPA");

            messageSender.sendMessage(message);
        } catch (IOException e) {
            System.err.println("Could not get I/O for the connection. Message not sent");
            return false;
        }

        return true;
    }

    private static class MessageSender {
        private Socket socket;
        private ObjectOutputStream socketOut;

        MessageSender(Socket socket) throws IOException {
            this.socket = socket;

            socketOut = new ObjectOutputStream(socket.getOutputStream());
        }

        void sendMessage(Message message) throws IOException {
            socketOut.writeObject(message);
        }

    }

    private class MessageReceiver extends Thread {
        private Socket socket;
        private ObjectInputStream socketIn;

        MessageReceiver(Socket socket) throws IOException {
            this.socket = socket;

            socketIn = new ObjectInputStream(socket.getInputStream());
        }

        public void run() {
            Message m = null;

            try {
                while (true) {
                    if (gameController.getGameState() == GameState.END) {
                        socket.close();
                        return;
                    }

                    while ((m = (Message) socketIn.readObject()) != null) {
                        gameController.handleMessage(m);
                    }
                }
            } catch (IOException e) {
                System.err.println("Could not get I/O for connection.");
                System.exit(1);
            } catch (ClassNotFoundException e) {
                System.err.println("Received class not found.");
                System.exit(2);
            }
        }
    }

    private class GameServer extends Thread {
        private ServerSocket socket;

        public void run() {
            try {
                MessageReceiver messageReceiver;
                if (isServer) {
                    socket = new ServerSocket(port);
                    Socket clientSocket = socket.accept();

                    messageSender = new MessageSender(clientSocket);

                    messageReceiver = new MessageReceiver(clientSocket);
                    messageReceiver.start();

                    socket.close();

                } else {
                    Socket clientSocket = new Socket(IP, port);

                    messageSender = new MessageSender(clientSocket);

                    messageReceiver = new MessageReceiver(clientSocket);
                    messageReceiver.start();
                }
            } catch (UnknownHostException e) {
                System.err.println("Unknown host");
            } catch (IOException e) {
                System.err.println("Could not get I/O for the connection.");
            }
        }

    }

}


