package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkManager {
    private int port;
    boolean isConnected = false;
    private String IP;
    private boolean isServer;

    public NetworkManager(int port, String IP, boolean isServer) {
        this.port = port;
        this.IP = IP;
        this.isServer = isServer;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void connect() throws Exception {
        if (isServer){
            ServerSocket socket = new ServerSocket(port);
            new Thread(new MessageServer(socket.accept())).start();

        }else{
            Socket socket = new Socket(IP, port);
            new Thread(new MessageServer(socket)).start();
        }

    }

    private static class MessageServer implements Runnable{
        private Socket socket;

        public MessageServer(Socket socket){
            this.socket = socket;
        }



        @Override
        public void run() {
            try {
                // Decorate the streams so we can send characters
                // and not just bytes.  Ensure output is flushed
                // after every newline.
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Send a welcome message to the client.
                out.println("Connection Established");
                out.println("Enter a line with only a period to quit\n");

                // Get messages from the client, line by line; return them
                // capitalized
                while (true) {
                    String input = in.readLine();
                    if (input == null || input.equals(".")) {
                        break;
                    }
                    out.println(input.toUpperCase());
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

}