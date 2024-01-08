/*Write a program on datagram socket for client/server to display the 
messages on client side, typed at the server side.*/

import java.net.*;

public class UDPServer {
    public static void main(String args[]) throws Exception {
        // Creating a DatagramSocket to listen on port 9884
        DatagramSocket serverSocket = null;

        try {
            serverSocket = new DatagramSocket(9884);
            System.out.println("Server is ready for the client");

            // Buffer to hold incoming data
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            while (true) {
                // Receiving data from the client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                System.out.println("RECEIVED: " + sentence);

                // Extracting client's IP address and port number
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                // Converting the received data to uppercase
                String capitalizedSentence = sentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();

                // Sending the modified data back to the client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            // Handling exceptions and printing the stack trace
            e.printStackTrace();
        } finally {
            // Closing the server socket in the finally block
            serverSocket.close();
        }
    }
}


/*first run the server program and then the client program in another terminal*/
