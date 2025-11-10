import java.io.BufferedReader;

import java.util.*;
import java.net.*;
import java.io.*;


public class Client {
    
    private String host;
    private int port;
    private Socket socket;
    BufferedReader in; 


    public Client(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        this.socket = new Socket(host, port);
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void handshake() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("12345");
    }

    public String request(String num)
    {
        return num; // for now 
    }

    public void disconnect() throws IOException {
        socket.close();
    }


}
