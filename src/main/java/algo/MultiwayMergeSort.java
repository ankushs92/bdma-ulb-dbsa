package algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.read.MemMapReadStream;
import streams.write.MemoryMappedWriteStream;
import util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static algo.Constants.SORTED_DIR;
import static algo.Constants.SORTED_EXT;

public class MultiwayMergeSort {

    private static final Logger logger = LoggerFactory.getLogger(MultiwayMergeSort.class);
    private final File file;
    private final int memory; //M
    private final int d; //d

    public MultiwayMergeSort(
            final File file,
            final int memory,
            final int d
    )
    {
        Assert.notNull(file, "file cannot be null");
        Assert.isTrue(memory > 0, "memory has to be greater than 0");
        Assert.notNull(d > 1, "d has to be greater than 1");

        this.file = file;
        this.memory = memory;
        this.d = d;
    }

    public void sortAndMerge() throws IOException {
       // We allocate buffer size equal to the size of the block on disk
       final int inputBufferSize = memory * 4;
       final int outputBufferSize = memory * 4;

       logger.info("Input Buffer Size = {} integers", inputBufferSize);
       logger.info("Output Buffer Size = {} integers ", outputBufferSize);

       try(final MemMapReadStream is = new MemMapReadStream(inputBufferSize)) {
           is.open(file.getPath());
           final int fileSizeInBytes = (int) is.getFileSize();
           final int fileSize = fileSizeInBytes / 4;

           final int numberOfStreams  = (int) Math.ceil((double) fileSize / (double) memory);
           final int sizeOfStreams  = fileSize / numberOfStreams; // Actually it's just M

           logger.info("File size in bytes {}", fileSizeInBytes);
           logger.info("File Size is {} integers", fileSize);
           logger.info("Memory allocated : {} integers", memory);
           logger.info("Size of Streams : {} integers", sizeOfStreams);
           logger.info("Number of Streams : {}", numberOfStreams);

           final Queue<String> streamsQueue = new LinkedList<>();
           List<Integer> integers = new ArrayList<>(sizeOfStreams);
           int intsRead = 0;
           int filePosition = 1;
           while(!is.endOfStream()) {
               final int value = is.readNext();
               intsRead++;
               integers.add(value);
               if(intsRead % sizeOfStreams == 0) {
                   Collections.sort(integers); //Merge sort
                   try(final MemoryMappedWriteStream os = new MemoryMappedWriteStream(outputBufferSize)) {
                       final String filePos = String.valueOf(filePosition);
                       final String streamFileLoc = SORTED_DIR + filePos + SORTED_EXT;
                       streamsQueue.add(filePos);
                       os.create(streamFileLoc);
                       filePosition++;
                       for(final Integer intValue : integers) {
                           os.write(intValue);
                       }
                   }
                   catch (final Exception ex) {
                       logger.error("", ex);
                   }
                   integers = new ArrayList<>(sizeOfStreams);
               }
           }

           logger.info("Streams Queue References {}", streamsQueue);
           final MultiWayMerge multiWayMerge = new MultiWayMerge(streamsQueue, memory, d);
           multiWayMerge.merge();
           logger.error("Deleting temporary files");
           multiWayMerge.removeTemporaryFiles();
       }
       catch (final Exception ex) {
           logger.error("", ex);
       }
    }

    public static void main(String[] args) throws IOException {
        File fourMb = new File("/Users/ankushsharma/Desktop/code/dbsa/src/main/resources/benchmark/4mb_integers.data");
        final int memory = 4000000;
        MultiwayMergeSort m = new MultiwayMergeSort(fourMb, memory, 1);
        m.sortAndMerge();
//        final File file = new File("./src/main/resources/benchmark/implementation_1_2/1Mb/1mb_262144_integers_1");
//        long start = System.currentTimeMillis();
//        final MultiwayMergeSort m = new MultiwayMergeSort(file,8200, 3);
//
//        m.sortAndMerge();
//        long stop = System.currentTimeMillis();
//        System.out.println(stop - start);
//
//        //When m = 8192,  time = 11639, 9146, 9829
//        //when m = 8200, time = 11397, 9451, 10095
//
//
//        MemMapReadStream r = new MemMapReadStream(10000);
//       r.open("./src/main/resources/sorted/22_23_24_25_26_27_28_29_30_31_32_1_2_3_4_5_6_7_8_9_10_11_12_13_14_15_16_17_18_19_20_21.data");
//        List<Integer> ints = new ArrayList<>();
//        while(!r.endOfStream()) {
//            ints.add(r.readNext());
//        }
//        for(int i = 0 ; i < ints.size() -1 ; i++) {
//            if(i > 0 ) {
//                int current = ints.get(i);
//                int previous = ints.get(i - 1);
//                if(previous > current) {
//                    System.out.println("Previous " + previous);
//                    System.out.println("Current " + current);
//
//                }
//            }
//        }
//        System.out.println(ints);
            byte[] arr = new byte[Integer.MAX_VALUE - 2^5];
    }
}
