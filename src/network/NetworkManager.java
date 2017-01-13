package network;

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
    private Socket cSocket = null;

    public NetworkManager(int port, String IP, boolean isServer, GameController controller) {
        this.port = port;
        this.IP = IP;
        this.isServer = isServer;
        this.gameController = controller;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void connect() {
        new GameServer().start();
    }

    public boolean sendMessage(Message message) {
        try {
            messageSender.sendMessage(message);
        } catch (IOException e) {
            System.err.println("Could not get I/O for the connection. Message not sent");
            return false;
        }

        return true;
    }

    public void closeSocket() {
        try {
            cSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class MessageSender {
        private ObjectOutputStream socketOut;

        MessageSender(Socket socket) throws IOException {
            socketOut = new ObjectOutputStream(socket.getOutputStream());
        }

        void sendMessage(Message message) throws IOException {
            socketOut.writeObject(message);
        }

    }

    private class MessageReceiver extends Thread {
        private ObjectInputStream socketIn;

        MessageReceiver(Socket socket) throws IOException {
            socketIn = new ObjectInputStream(socket.getInputStream());
        }

        public void run() {
            Message m;

            try {
                while (true) {
                    while ((m = (Message) socketIn.readObject()) != null) {
                        gameController.handleMessage(m);
                    }
                }
            } catch (IOException e) {
                System.err.println("Could not get I/O for connection.");
            } catch (ClassNotFoundException e) {
                System.err.println("Received class not found.");
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
                    socket.setSoTimeout(15000);
                    Socket clientSocket = socket.accept();
                    cSocket = clientSocket;

                    messageSender = new MessageSender(clientSocket);

                    messageReceiver = new MessageReceiver(clientSocket);
                    messageReceiver.start();

                    isConnected = true;

                    gameController.startGame();

                    socket.close();

                } else {
                    Socket clientSocket = new Socket(IP, port);

                    cSocket = clientSocket;

                    messageSender = new MessageSender(clientSocket);

                    messageReceiver = new MessageReceiver(clientSocket);
                    messageReceiver.start();

                    isConnected = true;

                    gameController.startGame();
                }
            } catch (UnknownHostException e) {
                System.err.println("Unknown host");
            } catch (IOException e) {
                gameController.displayMessage("Connection Timeout. ");
                gameController.forceRestart();

                System.err.println("Could not get I/O for the connection.");
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }
}


