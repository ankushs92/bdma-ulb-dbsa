package algo;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streams.read.MemMapReadStream;
import streams.write.FWriteStream;
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
        final int inputBufferSize = 1024 * 1024;
        while(!streamsLocations.isEmpty() && streamsLocations.size() > 1) {
            Queue<StreamsPriorityQueue> streamsQueue = new PriorityQueue<>();
            String mergedFileKey;
//            System.out.println("Memory = " + memory + " , d = " + d + ", bufferSize = " + (int) Math.ceil((double) memory / d ));

            for (int i = 0; i < d && i <= streamsLocations.size(); i++) {
                final MemMapReadStream readStream = new MemMapReadStream(inputBufferSize);
                final StreamsPriorityQueue streamManager = new StreamsPriorityQueue(0, readStream);
                try {
                    final String fileKey = streamsLocations.remove();
                    streamManager.getStream().open(SORTED_DIR + fileKey + SORTED_EXT);
                    streamManager.readNext();
                    streamsQueue.add(streamManager);

                } catch (final IOException e) {
                    logger.error("", e);
                }
            }
            mergedFileKey = RandomStringUtils.randomAlphanumeric(32);

            mergeSort(streamsQueue, SORTED_DIR + mergedFileKey + SORTED_EXT);
            streamsLocations.add(mergedFileKey);
            if(streamsLocations.size()<=1) {
                logger.info("Final merge result in " + SORTED_DIR + mergedFileKey + SORTED_EXT);
                //rename(SORTED_DIR + mergedFileKey + SORTED_EXT, "finalMerged" + pass + SORTED_EXT);
            }
        }
    }

    public void mergeSort(Queue<StreamsPriorityQueue> kStreams, final String fileName) {
        //Create the ouput stream. We consider size 1 for output
        final FWriteStream fWriteStream = new FWriteStream();
        fWriteStream.create(fileName);
        while(!kStreams.isEmpty()) {
            final StreamsPriorityQueue streamMinValue = kStreams.remove();
            final int minValue = streamMinValue.getIntValue();
            fWriteStream.write(minValue);
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
        fWriteStream.close();
    }
    public void removeTemporaryFiles() {
        //We merged the files, we can remove them.
        while(!removeStreams.isEmpty()) {
            final StreamsPriorityQueue streamToRemove = removeStreams.remove();
            streamToRemove.deleteFile();
        }
    }
}
