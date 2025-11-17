import java.util.*;
import java.net.*;
import java.time.LocalDateTime;
import java.io.*;

public class Server {

    private int port;
    private ServerSocket serverSocket;
    private ArrayList<LocalDateTime> connectedTimes = new ArrayList<>();

    public Server(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    public void serve(int num)
    {
        for(int i = 0; i < num; i++)
        {
            try
            {
                Socket client = serverSocket.accept();
                ClientHandler handler = new ClientHandler(client, connectedTimes);
                handler.start(); // thread starts 
            }
                catch (Exception e){
                    e.printStackTrace();
            }
        
        }
    }

    public ArrayList<LocalDateTime> getConnectedTimes() {
        synchronized (connectedTimes) {
            Collections.sort(connectedTimes);
            return new ArrayList<>(connectedTimes);
        }
    }

    public void disconnect() {
        try{
            serverSocket.close();
        }
        catch (Exception e){
            e.printStackTrace();

        }
    }
}
