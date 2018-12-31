package benchmark;

import streams.read.MemMapReadStream;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamsBenchmarkRunner {
    // N = 1mb  , 32mb 128mb 512mb
    // K = 1, 10, 20, 30
    // M =  4096 Bytes (size of the block), 1048576, 32*1048576, 128*1048576, 512*1048576,
    public static void main(String[] args) {
        File input1Mb_1 = new File("./src/main/resources/1mb_262144_integers_1");
        File input1Mb_2 = new File("./src/main/resources/1mb_262144_integers_2");
        File input1Mb_3 = new File("./src/main/resources/1mb_262144_integers_3");
        File input1Mb_4 = new File("./src/main/resources/1mb_262144_integers_4");
        File input1Mb_5 = new File("./src/main/resources/1mb_262144_integers_5");
        File input1Mb_6 = new File("./src/main/resources/1mb_262144_integers_6");
        File input1Mb_7 = new File("./src/main/resources/1mb_262144_integers_7");
        File input1Mb_8 = new File("./src/main/resources/1mb_262144_integers_8");
        int fileSize1Mb = 1024 * 1024 / 4;
        int memory1Mb = 1024 * 1024 / 4;

        List<String[]> records1Mb = new ArrayList<>();
//        final List<File> inputFiles,
//        final List<File> outputFiles,
//        final int outputFileSize,
//        final int k,
//        final int bufferSize

//        StreamsBenchmark readStreamOneMb = new StreamsBenchmark();
    }
}
