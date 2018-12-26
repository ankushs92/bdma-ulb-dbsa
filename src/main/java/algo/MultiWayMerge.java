package algo;

import java.io.File;
import java.util.PriorityQueue;

public class MultiWayMerge {

    private final File file;
    private final int bufferSize;
    private final int d;

    public MultiWayMerge(
            final File file,
            final int bufferSize,
            final int d
    )
    {
        this.bufferSize = bufferSize;
        this.d = d;
        this.file = file;
    }

    // d
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        queue.add(-1);
        queue.add(-3);
        queue.add(0);


        System.out.println(queue);


        System.out.println(queue);

        while(!queue.isEmpty()) {
            System.out.println(queue.poll());

        }
    }
}
