
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by RdDvls on 12/13/16.
 */
public class SimpleServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Server is running and waiting for connection...");
        //start a server on a specific port (target port for client)
        ServerSocket serverListener = new ServerSocket(8005);
        //.accept() --> Blocking call; must get response from server to proceed
        Socket clientSocket = serverListener.accept();

        //display information about who just connected to our server
        System.out.println("Incoming connection from: " + clientSocket.getInetAddress().getHostAddress());

        //this is how we read from the client (BufferedReader with InputStreamReader)
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //this is how we write back to the client (PrintWriter with client output stream
        //PrintWriter class has auto-flush request as part of method signature (autoFlush: true); if not declared in method signature
        //then you must add "printWriterName".flush();
        PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);
        //read from the input until the client disconnects
        String inputFromClientLine;
        while ((inputFromClientLine = inputFromClient.readLine()) != null) {
            System.out.println("Received message: " + inputFromClientLine + " from: " + clientSocket.toString());
            outputToClient.println("Message Received");
        }
    }

}
