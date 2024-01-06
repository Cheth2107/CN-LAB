import java.util.Scanner;

public class CRC1 {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.println("At Sender Side: ");
        // Input Data Stream
        System.out.print("Enter message bits: ");
        String message = sc.nextLine();
        System.out.print("Enter generator: ");
        String generator = sc.nextLine();

        int data[] = new int[message.length() + generator.length() - 1];
        int divisor[] = new int[generator.length()];

        for (int i = 0; i < message.length(); i++)
            data[i] = Integer.parseInt(message.charAt(i) + "");

        for (int i = 0; i < generator.length(); i++)
            divisor[i] = Integer.parseInt(generator.charAt(i) + "");

        // Calculation of CRC
        for (int i = 0; i < message.length(); i++) {
            if (data[i] == 1)
                for (int j = 0; j < divisor.length; j++)
                    data[i + j] ^= divisor[j];
        }

        // Display CRC
        System.out.print("The checksum code is: ");
        for (int i = 0; i < message.length(); i++)
            System.out.print(data[i]);
        System.out.println();

        System.out.println("At Receiver Side: ");
        // Check for input CRC code
        System.out.print("Enter checksum code: ");
        message = sc.nextLine();
        System.out.print("Enter generator: ");
        generator = sc.nextLine();

        data = new int[message.length() + generator.length() - 1];
        divisor = new int[generator.length()];

        for (int i = 0; i < message.length(); i++)
            data[i] = Integer.parseInt(message.charAt(i) + "");

        for (int i = 0; i < generator.length(); i++)
            divisor[i] = Integer.parseInt(generator.charAt(i) + "");

        // Calculation of remainder
        for (int i = 0; i < message.length(); i++) {
            if (data[i] == 1)
                for (int j = 0; j < divisor.length; j++)
                    data[i + j] ^= divisor[j];
        }

        // Display validity of data
        boolean valid = true;
        for (int i = 0; i < data.length; i++)
            if (data[i] == 1) {
                valid = false;
                break;
            }

        if (valid)
            System.out.println("Data stream is valid");
        else
            System.out.println("Data stream is invalid. CRC error occurred.");
    }
}

import java.util.Scanner;

public class CRC {

    public static String crc(String data, String poly, boolean errChk) {
        StringBuilder rem = new StringBuilder(data);

        if (!errChk) {
            for (int i = 0; i < poly.length() - 1; i++)
                rem.append("0");
        }

        for (int i = 0; i < rem.length() - poly.length() + 1; i++) {
            if (rem.charAt(i) == '1') {
                for (int j = 0; j < poly.length(); j++) {
                    rem.setCharAt(i + j, (rem.charAt(i + j) == poly.charAt(j)) ? '0' : '1');
                }
            }
        }

        return rem.substring(rem.length() - poly.length() + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String poly = "10000100010001010";

        System.out.print("Enter Data to be sent: ");
        String data = sc.next();

        String rem = crc(data, poly, false);
        String codeword = data + rem;

        System.out.println("Remainder: " + rem);
        System.out.println("Codeword: " + codeword);

        // Checking error
        System.out.print("Enter received codeword: ");
        String recvCodeword = sc.next();

        String recvRem = crc(recvCodeword, poly, true);

        if (Integer.parseInt(recvRem) == 0) {
            System.out.println("No Error");
        } else {
            System.out.println("Error Detected");
        }

        sc.close();
    }
}

/* the generator bit shoulb be same for both sender and receiver side to check the correctness of the code*/
/*
output:-
At Sender Side: 
Enter message bits: 11011101
Enter generator: 01101
The checksum code is: 10101001
At Receiver Side: 
Enter checksum code: 10101110
Enter generator: 01101
Data stream is invalid. CRC error occurred.
*/
