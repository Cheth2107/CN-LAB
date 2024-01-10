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
