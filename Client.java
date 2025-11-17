import java.util.*;
import java.net.*;
import java.io.*;


public class Client {
    
    private String host;
    private int port;
    private Socket socket;
    private BufferedReader in; 
    private PrintWriter out;


    public Client(String host, int port) throws IOException {

        try{
            this.host = host;
            this.port = port;
            this.socket = new Socket(host, port);

            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void handshake() throws IOException {
        try 
        {
            out.println("12345");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
    }

    public String request(String num) throws IOException
    {
        try
        {
            out.println(num);
            return in.readLine(); 
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void disconnect() {
       try
       {
        socket.close();
       }
         catch (Exception e){
            e.printStackTrace();
        }
        
    }

}
