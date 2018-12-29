package algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

public class MultiwayMerge1 {

    private static final Logger logger = LoggerFactory.getLogger(MultiwayMerge1.class);

    private final Queue<String> streamsLocations;
    private final int bufferSize;
    private final int d;

    public MultiwayMerge1(
            final Queue<String> streamsLocations,
            final int bufferSize,
            final int d
    )
    {
        this.streamsLocations = streamsLocations;
        this.bufferSize = bufferSize;
        this.d = d;
    }

    public void merge() {
        logger.info("Attempting to merge");


    }


}
