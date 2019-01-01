package benchmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamsBenchmarkRunner {

    private static final Logger logger = LoggerFactory.getLogger(StreamsBenchmarkRunner.class);

    private static final File OUTPUT_CSV = new File("./src/main/resources/benchmark/csv/streamsBenchmark.csv/");
    static {
        OUTPUT_CSV.delete();
        try {
            OUTPUT_CSV.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // N = 1mb  , 32mb 128mb 512mb
    // K = 1, 10, 20, 30
    // M =  4096 Bytes (size of the block), 1048576, 32*1048576, 128*1048576, 512*1048576,
    public static void main(String[] args) throws IOException {
        List<String[]> csv = new ArrayList<>();
        File input1Mb_1 = new File("./src/main/resources/benchmark/implementation_1_2/1Mb/1mb_262144_integers_1");
        int fileSize1Mb = 1024 * 1024 / 4;
        String fileSize1MbStr = String.valueOf(fileSize1Mb);

        int k = 1;
        String kString = String.valueOf(k);
        List<File> oneStream1mbFiles = getK1MbFiles(k, input1Mb_1);
        String[] record = new String[]{"1MB", fileSize1MbStr, kString, "1", null, null, null};
        appendToCsvFor1mbFiles(record, oneStream1mbFiles, k);
        csv.add(record);
        Util.write(OUTPUT_CSV, csv);

        k = 10;
        kString = String.valueOf(k);
        List<File> tenStream1mbFiles = getK1MbFiles(k, input1Mb_1);
        String[] record1 = new String[]{"1MB", fileSize1MbStr, kString, "1", null, null, null};
        appendToCsvFor1mbFiles(record1, tenStream1mbFiles, k);
        csv.add(record1);
        Util.write(OUTPUT_CSV, csv);

        k = 20;
        kString = String.valueOf(k);
        String[] record2 = new String[]{"1MB", fileSize1MbStr, kString, "1", null, null, null};
        List<File> twentyStream1mbFiles = getK1MbFiles(k, input1Mb_1);
        appendToCsvFor1mbFiles(record2, twentyStream1mbFiles, k);
        csv.add(record2);
        Util.write(OUTPUT_CSV, csv);


        System.out.println(Arrays.asList(csv));
    }


    private static List<File> getK1MbFiles(int k, File file) {
        List<File> result = new ArrayList<>();
        for(int i = 0 ; i < k ; i++) {
            result.add(file);
        }
        return result;
    }

    private static void appendToCsvFor1mbFiles(String[] record, List<File> files, int k) throws FileNotFoundException {
        for(int i = 0 ; i < 3; i++) {
            StreamsBenchmark readStreamOneMb = new StreamsBenchmark(files, null, 0, k, 0);
            long timeTaken = readStreamOneMb.getTimeTakenToExecuteKReadStreams();
            String timeTakenString = String.valueOf(timeTaken);
            if(i == 0) {
                record[3] = timeTakenString;
            }
            if( i ==1 ) {
                record[4] = timeTakenString;

            }
            if( i ==2 ) {
                record[5] = timeTakenString;
            }
        }
    }
}
