package algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.read.MemMapReadStream;
import streams.write.MemoryMappedWriteStream;
import util.Assert;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

import static algo.Constants.SORTED_DIR;
import static algo.Constants.SORTED_EXT;

public class MultiWayMerge {

    private static final Logger logger = LoggerFactory.getLogger(MultiWayMerge.class);
    private final Queue<String> streamsLocations;
    private final int memory;
    private final int d;
    private final Queue<StreamsPriorityQueue> removeStreams;

    public MultiWayMerge(
            final Queue<String> streamsLocations,
            final int memory,
            final int d
    )
    {
        Assert.notNull(streamsLocations, "streamsLocations cannot be null");
        Assert.isTrue(memory > 0, "memory has to be greater than 0");
        Assert.notNull(d > 0, "d has to be greater than 0");

        this.streamsLocations = streamsLocations;
        this.memory = memory;
        this.d = d;
        this.removeStreams = new PriorityQueue<>();
    }

    public void merge() {
        while(!streamsLocations.isEmpty() && streamsLocations.size() > 1) {
            final Queue<StreamsPriorityQueue> streamsQueue = new PriorityQueue<>();
            String mergedFileKey = "";
            final int bufferSize = (int) Math.ceil((double) memory / d );
            for (int i = 0; i < d && i <= streamsLocations.size(); i++) {
                final MemMapReadStream readStream = new MemMapReadStream(bufferSize);
                final StreamsPriorityQueue streamManager = new StreamsPriorityQueue(0, readStream);
                try {
                    final String fileKey = streamsLocations.remove();
                    streamManager.getStream().open(SORTED_DIR + fileKey + SORTED_EXT);
                    streamManager.readNext();
                    streamsQueue.add(streamManager);
                    mergedFileKey += fileKey + "_";

                } catch (final IOException e) {
                    logger.error("", e);
                }
            }
            //We use the file key as name but in the last one we call it finalMergedFile
            if(streamsQueue.size() > 2) {
                mergedFileKey = mergedFileKey.substring(0, mergedFileKey.length() - 1);
            }
            else {
                mergedFileKey = "finalMergedFile";
            }

            mergeSort(streamsQueue, SORTED_DIR + mergedFileKey + SORTED_EXT);
            streamsLocations.add(mergedFileKey);
        }
    }

    public void mergeSort(Queue<StreamsPriorityQueue> kStreams, final String fileName) {
        //Create the ouput stream. We consider size 1 for output
        final MemoryMappedWriteStream writeStream = new MemoryMappedWriteStream(memory);
        writeStream.create(fileName);
        while(!kStreams.isEmpty()) {
            final StreamsPriorityQueue streamMinValue = kStreams.remove();
            final int minValue = streamMinValue.getIntValue();
            writeStream.write(minValue);
            if(!streamMinValue.getStream().endOfStream()) {
                streamMinValue.readNext();
                kStreams.add(streamMinValue);
            }
            else {
                //add the stream to be deleted with its file
                removeStreams.add(streamMinValue);
            }
        }
        //We finish the merge, close the output.
        writeStream.close();
    }
    public void removeTemporaryFiles(){
        //We merged the files, we can remove them.
        while(!removeStreams.isEmpty()) {
            final StreamsPriorityQueue streamToRemove = removeStreams.remove();
            streamToRemove.deleteFile();
        }
    }
}

