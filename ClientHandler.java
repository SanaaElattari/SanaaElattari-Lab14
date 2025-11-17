import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ClientHandler extends Thread{

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
