package network;

import mvc.GameController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkManager {
    private static GameController gameController;
    boolean isConnected = false;
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
        try {
            MessageReceiver messageReceiver;
            if (isServer) {
                ServerSocket socket = new ServerSocket(port);
                Socket clientSocket = socket.accept();

                messageReceiver = new MessageReceiver(clientSocket);
                messageSender = new MessageSender(clientSocket);
                messageReceiver.start();
                socket.close();

            } else {
                Socket socket = new Socket(IP, port);

                messageReceiver = new MessageReceiver(socket);
                messageSender = new MessageSender(socket);
                messageReceiver.start();
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host");
            return false;
        } catch (IOException e) {
            System.err.println("Could not get I/O for the connection.");
            return false;
        }

        isConnected = true;
        return true;
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

    private static class MessageReceiver extends Thread {
        private Socket socket;
        private ObjectInputStream socketIn;

        public MessageReceiver(Socket socket) throws IOException {
            this.socket = socket;

            socketIn = new ObjectInputStream(socket.getInputStream());
        }

        public void run() {
            Message m = null;

            try {
                while (true) {
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

    private static class MessageSender {
        private Socket socket;
        private ObjectOutputStream socketOut;

        public MessageSender(Socket socket) throws IOException {
            this.socket = socket;

            socketOut = new ObjectOutputStream(socket.getOutputStream());
        }

        public void sendMessage(Message message) throws IOException {
            socketOut.writeObject(message);
        }

    }

}


