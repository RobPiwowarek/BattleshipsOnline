package network;

import game.graphics.ui.NetworkManagerGUI;

public class NetworkManager {
    private int port;
    private String IP;
    private boolean isServer;
    private NetworkManagerGUI UI;


    public NetworkManager(int port, String IP, boolean isServer){
        this.port = port;
        this.IP = IP;
        this.isServer = isServer;
    }

    public NetworkManager(){
        this.UI = new NetworkManagerGUI();
        this.UI.show();
    }



}