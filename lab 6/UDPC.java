/*UDP cleint program*/

import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        // Creating a BufferedReader to read input from the user
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // Creating a DatagramSocket for the client
        DatagramSocket clientSocket = new DatagramSocket();

        // Getting the IP address of the server (localhost in this case)
        InetAddress IPAddress = InetAddress.getByName("localhost");

        // Arrays to hold the data for sending and receiving
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        // Prompting the user to enter a string in lowercase
        System.out.println("Enter the string in lowercase so that you receive the message in Uppercase from the server");
        String sentence = inFromUser.readLine();

        // Converting the string to bytes and creating a DatagramPacket for sending to the server
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9884);

        // Sending the packet to the server
        clientSocket.send(sendPacket);

        // Creating a DatagramPacket for receiving data from the server
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        // Receiving the modified data from the server
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());

        // Printing the modified data received from the server
        System.out.println("FROM SERVER: " + modifiedSentence);

        // Closing the client socket
        clientSocket.close();
    }
}

/*output;-
  first run the server program in one terminal in another terminal run the client program
Server side
javac UDPServer.java
java UDPServer
Server is Ready for the client
RECEIVED: abcdef

Client Side
javac UDPClient.java
java UDPClient
Enter the string in lowercase so that you receive the message in Uppercase from the server
abcdef
FROM SERVER: ABCDEF
*/
