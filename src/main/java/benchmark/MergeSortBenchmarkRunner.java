package benchmark;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Util;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

public class MergeSortBenchmarkRunner {

    private static final Logger logger = LoggerFactory.getLogger(MergeSortBenchmarkRunner.class);
    private static final File OUTPUT_CSV = new File("./src/main/resources/benchmark/csv/mergeSortBenchmark.csv/");
    private static final int MAX_K = 30;

    private static final File ONE_MB = new File("/Users/ankushsharma/Desktop/code/dbsa/src/main/resources/benchmark/1mb_integers.data");
    private static final File FOUR_MB = new File("./src/main/resources/benchmark/4mb_integers.data");
    private static final File THIRTY_TWO_MB = new File("./src/main/resources/benchmark/32mb_32000000_integers.data");
    private static final File ONE_TWENTY_EIGHT_MB = new File("./src/main/resources/benchmark/128mb_32000000_integers.data");


    public static void main(String[] args) throws IOException {
        // N = 250000, 1000000, 8000000, 32000000
        final List<File> files = Arrays.asList(ONE_MB, FOUR_MB, THIRTY_TWO_MB, ONE_TWENTY_EIGHT_MB);
        int index = 0;
        for(final File file : files) {
            final FileSize fileSize = getFileSize(file);
            final int N = getN(fileSize);
            logger.info("Reading File of size {}", fileSize);
            logger.info("Size of file in number of integers = {}", N);
            final List<Integer> memoryList = Arrays.asList(
                    (int)Math.ceil((double) N / 1000),
                    (int)Math.ceil((double) N / 100),
                    (int)Math.ceil((double) N / 10),
                    (int)Math.ceil((double) N / 2),
                    N
            );
            System.out.println(memoryList);
            //We consider possibe values of d as : 2, 1/3rd of M , 1/2 of M, and M itself
            for(final int m : memoryList) {
                final int noOfSublists = (int) Math.ceil(N / m);
                final List<Integer> Ds = Arrays.asList(
                        2,
                        30,
                        (int) Math.ceil((double) (noOfSublists / 3) ),
                        (int) Math.ceil((double) (noOfSublists / 2) ),
                        noOfSublists
                ).stream()
                 .distinct().filter( d -> !d.equals(1) && !d.equals(0))
                 .collect(Collectors.toList());

                Map<String, Integer> map = new HashMap<>();
                map.put("2", 2);
                map.put("30", 30);
                map.put("N/3M", (int) Math.ceil((double) (noOfSublists / 3) ));
                map.put("N/2M", (int) Math.ceil((double) (noOfSublists / 2) ));
                map.put("N/M", noOfSublists);

                map = map.entrySet().stream().filter(d -> !d.getValue().equals(1) && !d.getValue().equals(0)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                logger.info(" Ds {}", Ds);
                for(Map.Entry<String, Integer> entry : map.entrySet()) {
                    final String category = entry.getKey();
                    final int d = entry.getValue();
                    logger.info("m = {}, d = {}", m, d);
                    final ExternalMergeSortBenchmark benchmark = new ExternalMergeSortBenchmark(file, m, d);
                    //"index","N","Size","m","d","t"
                    final long timeTaken = benchmark.getTimeTakenToExecuteMergeSort();
                    final String[] record = new String[]{valueOf(index), fileSize.name(), valueOf(N), valueOf(m), valueOf(d), category, valueOf(timeTaken)};
                    index++;
                    Util.write(OUTPUT_CSV, Collections.singletonList(record));
                }
            }
        };
    }



    private static FileSize getFileSize(final File file) {
        FileSize result = null;
        if(file.equals(ONE_MB)) {
            result = FileSize.ONE_MB;
        }
        else if(file.equals(FOUR_MB)) {
            result = FileSize.FOUR_MB;
        }
        else if(file.equals(THIRTY_TWO_MB)) {
            result = FileSize.THIRTY_TWO_MB;
        }
        else {
            result = FileSize.ONE_TWENTY_EIGHT_MB;
        }
        return result;
    }
    private static int getN(final FileSize fileSize) {
        int N = 0;
        switch (fileSize) {
            case ONE_MB : {
                N = 1000 * 1000 / 4;
                break;
            }
            case FOUR_MB : {
                N = 4 * 1000 * 1000 / 4;
                break;
            }
            case ONE_TWENTY_EIGHT_MB : {
                N = 128 * 1000 * 1000 / 4;
                break;
            }
            case THIRTY_TWO_MB : {
                N = 32 * 1000 * 1000 / 4;
                break;
            }
        }
        return N;
    }




}
