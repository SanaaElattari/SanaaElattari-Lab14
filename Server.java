import java.net.Socket;

public class Server {
    private int port;
    private ServerSocket serverSocket;
  //private ArrayList<ClientHandler> clients;
    //private connectedTimes ArrayList<LocalDateTime>;

    public Server(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        //this.clients = new ArrayList<ClientHandler>();
        //this.connectedTimes = new ArrayList<LocalDateTime>();
    }

    public void serve (int numClients)
    {

    }

    public disconnect() throws IOException {
        serverSocket.close();
    }

    //client handler class

}
