package algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.read.MemMapReadStream;
import streams.write.MemoryMappedWriteStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
           is.open(file.getAbsolutePath());
           final int fileSizeInBytes = (int) is.getFileSize();
           final int fileSize = fileSizeInBytes / 4;


           final int sizeOfStreams = (int) Math.ceil((double) fileSize / (double) memory);
           logger.info("File size in bytes {}", fileSizeInBytes);
           logger.info("File Size is {} integers", fileSize);
           logger.info("Memory allocated : {} integers", memory);
           logger.info("Memory allocated : {} Bytes", memoryInBytes);
           logger.info("Size of Streams : {} integers", sizeOfStreams);
           logger.info("Number of Streams : {}", fileSize / sizeOfStreams);

           // If sizeOfStreams = 256
           //First byte read is 4, then8 , 12 and so on
           final Queue<String> streamsQueue = new PriorityQueue<>();
           List<Integer> integers = new ArrayList<>(sizeOfStreams);
           int intsRead = 0;
           int filePosition = 1;
           while(!is.endOfStream()) {
               final int value = is.readNext();
               intsRead++;
               integers.add(value);
               if(intsRead % sizeOfStreams == 0) {
                   Collections.sort(integers); //Merge sort
                   try(final MemoryMappedWriteStream os = new MemoryMappedWriteStream(memory)) {
                       final String streamFileLoc = Constants.SORTED_DIR + "1_" + String.valueOf(filePosition) + ".data";
                       streamsQueue.add(streamFileLoc);
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

           final MultiWayMerge multiWayMerge = new MultiWayMerge(streamsQueue, 1, d);
           multiWayMerge.merge();

       }
       catch (final Exception ex) {
           logger.error("", ex);
       }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./src/main/resources/inputToy4.data");
        MultiwayMergeSort m = new MultiwayMergeSort(file, 8, 3);

        m.sortAndMerge();
    }
}
