import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RdDvls on 12/13/16.
 */
public class SimpleClient {
    public static void main(String[] args) throws IOException {

        Scanner inputScanner = new Scanner(System.in);
        //Connect to the server on the target port
        Socket clientSocket = new Socket("localhost", 8005);
        System.out.println("Client is running and looking to connect...");
        //Once we connect to the server, we also have an input and output stream
        PrintWriter outputToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //Send the server an arbitrary message to establish connection and communication
//        outputToServer.println("Hello!");
        while (true) {
            System.out.println("\tEnter message to send to server: ");
            System.out.println("\t*Enter exit to end program");
            String clientMessageToServer = inputScanner.nextLine();
            if (!clientMessageToServer.isEmpty() && !clientMessageToServer.equalsIgnoreCase("exit")) {
                outputToServer.println(clientMessageToServer);
                //Read what the server returns
                String serverResponse = inputFromServer.readLine();
                System.out.println("\tFrom server: " + serverResponse);
            } else if (clientMessageToServer.equalsIgnoreCase("exit")) {
                //close the connection
                clientSocket.close();
                break;
            }
        }
    }
}
