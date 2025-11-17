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

    private class ClientHandler extends Thread{

        private Socket client;
        private ArrayList<LocalDateTime> connectedTimes;
    
        public ClientHandler(Socket client, ArrayList<LocalDateTime> connectedTimes) {
            this.client = client;
            this.connectedTimes = connectedTimes;
        }
    
        @Override
        public void run()
        {
            try
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
    
                // Do the Handshake 
                String handshake = in.readLine();
                if(!"12345".equals(handshake))
                {
                    out.println("couldn't handshake");
                    client.close();
                    return;
                }
                synchronized(connectedTimes)
                {
                    connectedTimes.add(LocalDateTime.now());
                }
    
                String request = in.readLine();
                if(request == null)
                {
                    client.close();
                    return;
                }
                int num;
                try
                {
                    num = Integer.parseInt(request);
                    int factors = factor(num);
                    //client.close();
                }
                catch (Exception e )
                {
                    out.println("There was an exception on the server");
                    client.close();
                    return;
                }
    
                int count = factor(num);
                out.println("The number " + num + " has " + count + " factors");
                client.close();
                
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    
        private int factor(int n)
        {
            int count = 0;
            for(int i = 1; i <= n; i++)
            {
                if(n % i == 0)
                {
                    count++;
                }
            }
            return count;
        }
        
    }
    

}
