/* Using TCP/IP sockets, write a client – server program to make the 

client send the file name and to make the server send back the contents 

of the requested file if present*/


/*SERVER program*/

import java.net.*;
import java.io.*;

public class TCPS {
    public static void main(String[] args) throws Exception {
        // Creating a server socket on port 4000
        ServerSocket sersock = new ServerSocket(4000);
        System.out.println("Server ready for connection");
        
        // Waiting for a client to connect
        Socket sock = sersock.accept();
        System.out.println("Connection is successful and waiting for the client request");
        
        // Getting the input stream from the client to read the filename
        InputStream istream = sock.getInputStream();
        BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
        String fname = fileRead.readLine();
        
        // Reading the content of the file and sending it to the client
        BufferedReader contentRead = new BufferedReader(new FileReader(fname));
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        String content;
        while ((content = contentRead.readLine()) != null) {
            pwrite.println(content);
        }
        
        // Closing resources
        sock.close();
        sersock.close();
        pwrite.close();
        fileRead.close();
        contentRead.close();
    }
}


/*first the server port should be established before it accepts any request from the client*/  
