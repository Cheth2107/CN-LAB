import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class sort1 {
    public static void main(String[] args) {
        List<int[]> frame = new ArrayList<>();
        System.out.println("Enter no. of frames:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            Random random = new Random();
            int seqNum = random.nextInt(1000) + 1;
            System.out.printf("Enter data for %dth frame>>", i + 1);
            int data = sc.nextInt();
            frame.add(new int[]{seqNum, data});
        }

        System.out.println("\n\nBefore Sorting>>");
        for (int[] i : frame) {
            System.out.printf("seqNum->%d, Data->%d\n", i[0], i[1]);
        }

        frame = sortFrame(frame);

        System.out.println("\n\n After sorting>>");
        for (int[] i : frame) {
            System.out.printf("seqNum->%d, Data->%d\n", i[0], i[1]);
        }
    }

    public static List<int[]> sortFrame(List<int[]> frame) {
        Collections.sort(frame, (a, b) -> Integer.compare(a[0], b[0]));
        return frame;
    }
}



import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class FrameSorter {
    public static void main(String[] args) {
        List<Frame> frames = Arrays.asList(
            new Frame(385, 213),
            new Frame(417, 211),
            new Frame(265, 567),
            new Frame(109, 290)
        );
        sortFrames(frames);
        System.out.println("Before Sorting>>");
        for (Frame frame : frames) {
            System.out.println(frame);
        }
        sortFrames(frames);
        System.out.println("\nAfter sorting>>");
        for (Frame frame : frames) {
            System.out.println(frame);
        }
    }

    public static void sortFrames(List<Frame> frames) {
        // This code assumes the frames are sorted in descending order
        Collections.sort(frames, Collections.reverseOrder());

        // However, you can modify the sorting order as per your requirements
        // Collections.sort(frames);
    }
}

class Frame implements Comparable<Frame> {
    private int seqNum;
    private int data;

    public Frame(int seqNum, int data) {
        this.seqNum = seqNum;
        this.data = data;
    }

    public int compareTo(Frame other) {
        return this.seqNum - other.seqNum;
    }

    public String toString() {
        return "seqNum->" + seqNum + ", Data->" + data;
    }
}
/* output:-
Enter no. of frames:
4
Enter data for 1th frame>>213
Enter data for 2th frame>>211
Enter data for 3th frame>>567
Enter data for 4th frame>>290

Before Sorting>>
seqNum->385, Data->213
seqNum->417, Data->211
seqNum->265, Data->567
seqNum->109, Data->290


 After sorting>>
seqNum->109, Data->290
seqNum->265, Data->567
seqNum->385, Data->213
seqNum->417, Data->211
*/
