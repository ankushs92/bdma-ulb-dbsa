package algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.read.BufferedReadStream;
import streams.write.BufferedWriteStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiwayMergeSort {

    private static final Logger logger = LoggerFactory.getLogger(MultiwayMergeSort.class);
    private final File file;
    private final int memory;
    private final int d;

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

    private static final int size = 256;

    public void sortAndMerge() throws FileNotFoundException {
       final int fileSize = size;
       try(final BufferedReadStream is = new BufferedReadStream(memory)) {
           is.open(file.getAbsolutePath());

           final int sizeOfStreams = (int) Math.ceil((double) fileSize / (double) memory);
           logger.info("File Size is {} bytes", fileSize);
           logger.info("Memory allocated : {}", memory);
           logger.info("Number of Streams : {}", sizeOfStreams);
           // If sizeOfStreams = 256
           //First byte read is 4, then8 , 12 and so on adn

           List<Integer> integers = new ArrayList<>(sizeOfStreams);
           int intsRead = 0;
           int filePosition = 1;
           while(!is.endOfStream()) {
               final int value = is.readNext();
               intsRead++;
               integers.add(value);
               if(sizeOfStreams % intsRead == 0) {
                   Collections.sort(integers);
                   try(final BufferedWriteStream os = new BufferedWriteStream(memory)) {
                       os.create(Constants.SORTED_DIR + "1_" + String.valueOf(filePosition));
                       filePosition++;
                       System.out.println(integers);
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
//        final BufferedWriteStream os = new BufferedWriteStream(memory);
//        os.create(Constants.SORTED_DIR + String.valueOf(position));
//        os.write(os.write(value));
//        position++;
//
           System.out.println(integers);
           Collections.sort(integers); // Used Merge sort, guarantees to give O(nlogn) performance

           System.out.println(integers);

       }
       catch (final Exception ex) {
           logger.error("", ex);
       }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./src/main/resources/inputToy.data");
        MultiwayMergeSort m = new MultiwayMergeSort(file, 8, 0);

        m.sortAndMerge();
    }
}
