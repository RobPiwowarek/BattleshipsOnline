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
    private MessageServer messageServer;

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
            if (isServer) {
                ServerSocket socket = new ServerSocket(port);

                messageServer = new MessageServer(socket.accept());
                socket.close();

            } else {
                Socket socket = new Socket(IP, port);

                messageServer = new MessageServer(socket);
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
        return messageServer.sendMessage(message);
    }

    private static class MessageServer extends Thread {
        private Socket socket;
        private ObjectOutputStream socketOut;
        private ObjectInputStream socketIn;

        public MessageServer(Socket socket) throws IOException {
            this.socket = socket;

            socketOut = new ObjectOutputStream(socket.getOutputStream());
            socketIn = new ObjectInputStream(socket.getInputStream());
        }

        public void run() {
            Message m = null;

            try {
                while ((m = (Message) socketIn.readObject()) != null) {
                    gameController.handleMessage(m);
                }
            } catch (IOException e) {
                System.err.println("Could not get I/O for connection.");
                System.exit(1);
            } catch (ClassNotFoundException e) {
                System.err.println("Received class not found.");
                System.exit(2);
            }
        }

        public boolean sendMessage(Message message) {
            try {
                socketOut.writeObject(message);
                return true;

            } catch (IOException e) {
                System.err.println("Failed to send message over socket");
                return false;
            }
        }

    }

}


