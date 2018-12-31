package benchmark;

import algo.MultiwayMergeSort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class MergeSortBenchmark {

    private static final Logger logger = LoggerFactory.getLogger(MergeSortBenchmark.class);

    private final int d;
    private final File inputFile;
    private final int memory;

    MergeSortBenchmark(
            final File inputFile,
            final int memory,
            final int d
    )
    {
        this.inputFile = inputFile;
        this.memory = memory;
        this.d = d;
    }

    public long getTimeTakenToExecuteMergeSort() throws FileNotFoundException {
        final long start = System.currentTimeMillis();
        final MultiwayMergeSort multiwayMergeSort = new MultiwayMergeSort(inputFile, memory, d);
        multiwayMergeSort.sortAndMerge();
        final long stop = System.currentTimeMillis();
        final long timeTaken = stop - start;
        logger.info("Time taken to execute Merge Sort is {} ms ", timeTaken);
        return timeTaken;
    }


    public static void main(String[] args) throws FileNotFoundException {
        File inputFile1 = new File("Some file 1");
        int d = 3;
        int memory = 1024;
        MergeSortBenchmark firstBenchmark = new MergeSortBenchmark(inputFile1, memory, d);
        System.out.println("First benchmark " + firstBenchmark.getTimeTakenToExecuteMergeSort());

        File inputFile2 = new File("");
//        int d = 5;




    }
}
