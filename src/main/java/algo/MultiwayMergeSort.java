package algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.read.MemMapReadStream;
import streams.write.MemoryMappedWriteStream;

import java.io.File;
import java.io.FileNotFoundException;
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
        this.file = file;
        this.memory = memory;
        this.d = d;
    }

    public void sortAndMerge() throws FileNotFoundException {
       final int memoryInBytes = memory * 4;
       try(final MemMapReadStream is = new MemMapReadStream(memoryInBytes)) {
           is.open(file.getPath());
           final int fileSizeInBytes = (int) is.getFileSize();
           final int fileSize = fileSizeInBytes / 4;

           final int numberOfStreams  = (int) Math.ceil((double) fileSize / (double) memory);
           final int sizeOfStreams  = fileSize / numberOfStreams;

           logger.info("File size in bytes {}", fileSizeInBytes);
           logger.info("File Size is {} integers", fileSize);
           logger.info("Memory allocated : {} integers", memory);
           logger.info("Memory allocated : {} Bytes", memoryInBytes);
           logger.info("Size of Streams : {} integers", sizeOfStreams);
           logger.info("Number of Streams : {}", numberOfStreams);

           //If sizeOfStreams = 256
           //First byte read is 4, then8 , 12 and so on
           final Queue<String> streamsQueue = new LinkedList<>();
           List<Integer> integers = new ArrayList<>(sizeOfStreams);
           int intsRead = 0;
           int filePosition = 1;
           while(!is.endOfStream()) {
               final int value = is.readNext();
               intsRead++;
               integers.add(value);
               if(intsRead % sizeOfStreams == 0) {
                   System.out.println("length " + integers.size());
                   Collections.sort(integers); //Merge sort
                   try(final MemoryMappedWriteStream os = new MemoryMappedWriteStream(memory)) {
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
       }
       catch (final Exception ex) {
           logger.error("", ex);
       }
    }

    public static void main(String[] args) throws IOException {
        final File file = new File("./src/main/resources/1mb_262144_integers_1.data");
        final MultiwayMergeSort m = new MultiwayMergeSort(file,8192, 3);
        m.sortAndMerge();



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

    }
}
