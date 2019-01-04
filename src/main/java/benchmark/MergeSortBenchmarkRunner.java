package benchmark;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MergeSortBenchmarkRunner {

    private static final File OUTPUT_CSV = new File("./src/main/resources/benchmark/csv/mergeSortBenchmark.csv/");
    private static final int MAX_K = 30;
    private static final List<Integer> Ks = Arrays.asList(1, 10, 20, 30);
    private static final List<Integer> BUFFER_SIZES = Arrays.asList(4096, 1048576, 33554432);
    private static final File ONE_MB = new File("/Users/ankushsharma/Desktop/code/dbsa/src/main/resources/benchmark/implementation_1_2/1Mb/1mb_262144_integers_1");
    private static final File FOUR_MB = new File("./src/main/resources/benchmark/4mb_integers.data");
    private static final File THIRTY_TWO_MB = new File("./src/main/resources/benchmark/32mb_32000000_integers.data");
    private static final File ONE_TWENTY_EIGHT_MB = new File("./src/main/resources/benchmark/128mb_32000000_integers.data");
    private static final File FIVE_HUNDRED_TWELVE_MB = new File("./src/main/resources/benchmark/512mb_32000000_integers.data");



    // N = 1mb
    // d = 1,2,3
    // M = 4096, 8192, 1024 * 1024


}
